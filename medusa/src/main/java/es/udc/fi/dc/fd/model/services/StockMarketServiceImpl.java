package es.udc.fi.dc.fd.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.dc.fd.model.common.exceptions.DuplicateInstanceException;
import es.udc.fi.dc.fd.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.dc.fd.model.common.exceptions.InvalidOperationException;
import es.udc.fi.dc.fd.model.common.exceptions.NotEnoughBalanceException;
import es.udc.fi.dc.fd.model.common.exceptions.NotOwnedException;
import es.udc.fi.dc.fd.model.common.exceptions.NumberException;
import es.udc.fi.dc.fd.model.entities.AnnualBenefits;
import es.udc.fi.dc.fd.model.entities.AnnualBenefitsDao;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.entities.EnterpriseDao;
import es.udc.fi.dc.fd.model.entities.OrderLine;
import es.udc.fi.dc.fd.model.entities.OrderLine.OrderType;
import es.udc.fi.dc.fd.model.entities.OrderLineDao;
import es.udc.fi.dc.fd.model.entities.User;
import es.udc.fi.dc.fd.model.entities.User.RoleType;
import es.udc.fi.dc.fd.model.entities.UserDao;
import es.udc.fi.dc.fd.model.services.exceptions.PermissionException;
import es.udc.fi.dc.fd.rest.dtos.AnnualBenefitsListDto;
import es.udc.fi.dc.fd.rest.dtos.AnnualBenefitsParamsDto;

@Service
@Transactional
public class StockMarketServiceImpl implements StockMarketService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private EnterpriseDao enterpriseDao;

	@Autowired
	private PermissionChecker permissionChecker;

	@Autowired
	private OrderLineDao orderLineDao;

	@Autowired
	private AnnualBenefitsDao annualBennefitsDao;

	@Override
	public Enterprise createEnterprise(Long userId, Enterprise enterprise)
			throws DuplicateInstanceException, PermissionException, NumberException {

		Optional<User> userOp = null;
		User user = null;

		if (enterpriseDao.existsByEnterpriseName(enterprise.getEnterpriseName())) {
			throw new DuplicateInstanceException("project.entities.enterprise", enterprise.getEnterpriseName());
		}

		if (enterprise.getActions() < 0 || enterprise.getActionsPrice() < 0) {
			throw new NumberException();
		}

		userOp = userDao.findById(userId);
		if (userOp.isPresent()) { // Aqui habría que añadir algo para cuando el user no exista
			user = userOp.get();

			if (user.getRole() == RoleType.ADMIN) {

				enterpriseDao.save(enterprise);

				OrderLine order = new OrderLine(OrderType.SELL, null, enterprise.getActionsPrice(), enterprise.getActions(), enterprise);
				orderLineDao.save(order);

			} else {
				throw new PermissionException();
			}
		}

		return enterprise;
	}

	@Override
	public void transfer(Long userId, Float money, String operation)
			throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException {

		// Comprobamos la existencia del usuario
		permissionChecker.checkUser(userId);

		// Recuperamos el usuario ya existente
		Optional<User> existUser = userDao.findById(userId);
		User user = existUser.get();

		if (money <= 0) {
			throw new InvalidOperationException();
		}

		if (operation.equals("INCOME")) { // Caso de ingresar
			user.setBalance(money + user.getBalance());
			userDao.save(user);
		} else if (operation.equals("WITHDRAW")) { // Caso de retirar
			if (user.getBalance() >= money) {
				user.setBalance(user.getBalance() - money);
				userDao.save(user);
			} else {

				throw new NotEnoughBalanceException("Not enough balance"); // Cantidad para retirar insuficiente
			}

		} else {
			throw new InvalidOperationException();
		}

	}


	private void match() {

		for (Enterprise enterprise : enterpriseDao.findAll()) { // Por cada empresa se matchean sus orders

			Optional<List<OrderLine>> buyOrdersO = orderLineDao
					.findByOrderTypeAndEnterpriseAndAvaliableOrderByRequestDateDesc(OrderType.BUY, enterprise, true);

			Optional<List<OrderLine>> sellOrdersO = orderLineDao
					.findByOrderTypeAndEnterpriseAndAvaliableOrderByRequestDateDesc(OrderType.SELL, enterprise, true);

			if (!(buyOrdersO.isEmpty() || sellOrdersO.isEmpty())) {

				List<OrderLine> buyOrders = buyOrdersO.get();
				List<OrderLine> sellOrders = sellOrdersO.get();

				for (OrderLine buyOrder : buyOrders) {
					for (OrderLine sellOrder : sellOrders) {

						if (sellOrder.getPrice() <= buyOrder.getPrice()) {
							User buyOwner = buyOrder.getOwner();
							User sellOwner = sellOrder.getOwner();

							if (sellOrder.getNumber() > buyOrder.getNumber()) {
								buyOwner.setBalance(
										buyOwner.getBalance() - (sellOrder.getPrice() * buyOrder.getNumber()));
								sellOwner.setBalance(
										sellOwner.getBalance() + (sellOrder.getPrice() * buyOrder.getNumber()));

								int numSold = sellOrder.getNumber() - buyOrder.getNumber();
								int numRemain = sellOrder.getNumber() - numSold;
								OrderLine sold = new OrderLine(OrderType.SELL, sellOwner, sellOrder.getPrice(), numSold,
										enterprise);
								sold.setAvaliable(false);
								sold.setRequestDate(sellOrder.getRequestDate());
								OrderLine remain = new OrderLine(OrderType.SELL, sellOwner, sellOrder.getPrice(),
										numRemain, enterprise);
								remain.setRequestDate(sellOrder.getRequestDate());
								orderLineDao.delete(sellOrder);
								orderLineDao.save(sold);
								orderLineDao.save(remain);

								buyOrder.setNumber(0);
								buyOrder.setAvaliable(false);

								orderLineDao.save(buyOrder);

								enterprise.setActionsPrice(sold.getPrice());
							} else if (sellOrder.getNumber() < buyOrder.getNumber()) {
								buyOwner.setBalance(
										buyOwner.getBalance() - (sellOrder.getPrice() * sellOrder.getNumber()));
								sellOwner.setBalance(
										sellOwner.getBalance() + (sellOrder.getPrice() * sellOrder.getNumber()));
								buyOrder.setNumber(buyOrder.getNumber() - sellOrder.getNumber());
								sellOrder.setNumber(0);
								sellOrder.setAvaliable(false);

								int numSold = buyOrder.getNumber() - sellOrder.getNumber();
								int numRemain = buyOrder.getNumber() - numSold;
								OrderLine sold = new OrderLine(OrderType.BUY, buyOwner, sellOrder.getPrice(), numSold,
										enterprise);
								sold.setAvaliable(false);
								sold.setRequestDate(buyOrder.getRequestDate());
								OrderLine remain = new OrderLine(OrderType.BUY, buyOwner, buyOrder.getPrice(),
										numRemain, enterprise);
								remain.setRequestDate(buyOrder.getRequestDate());
								orderLineDao.delete(buyOrder);
								orderLineDao.save(sold);
								orderLineDao.save(remain);

								orderLineDao.save(sellOrder);

								enterprise.setActionsPrice(sellOrder.getPrice());
							} else {
								buyOwner.setBalance(
										buyOwner.getBalance() - (sellOrder.getPrice() * buyOrder.getNumber()));
								sellOwner.setBalance(
										sellOwner.getBalance() + (sellOrder.getPrice() * buyOrder.getNumber()));
								buyOrder.setNumber(0);
								buyOrder.setPrice(sellOrder.getPrice());
								sellOrder.setNumber(0);

								buyOrder.setAvaliable(false);
								sellOrder.setAvaliable(false);

								orderLineDao.save(sellOrder);
								orderLineDao.save(buyOrder);

								enterprise.setActionsPrice(sellOrder.getPrice());
							}
						}

					}
				}
			}

		}

	}

	@Override
	public void order(Long owner, OrderType orderType, Float price, int number, Long enterpriseId)
			throws NotEnoughBalanceException, NotOwnedException {

		User user = null;
		Enterprise enterprise = null;

		Optional<User> userOp = userDao.findById(owner);
		Optional<Enterprise> enterpriseOp = enterpriseDao.findById(enterpriseId);

		if (userOp.isPresent())
			user = userOp.get();
		if (enterpriseOp.isPresent())
			enterprise = enterpriseOp.get();

		OrderLine order = new OrderLine(orderType, user, price, number, enterprise);

		if (order.getOrderType() == OrderType.BUY) {
			if (order.getOwner().getBalance() < (order.getPrice() * order.getNumber()))
				throw new NotEnoughBalanceException("Offering more money than owned");

		} else {

			List<OrderLine> boughtStock = null;
			List<OrderLine> soldStock = null;

			Optional<List<OrderLine>> boughtStockOp = orderLineDao
					.findByOrderTypeAndOwnerAndEnterpriseAndAvaliableOrderByRequestDateDesc(OrderType.BUY, user,
							enterprise, false);
			Optional<List<OrderLine>> soldStockOp = orderLineDao
					.findByOrderTypeAndOwnerAndEnterpriseOrderByRequestDateDesc(OrderType.SELL, user, enterprise);

			if (boughtStockOp.isPresent() && soldStockOp.isPresent()) {
				boughtStock = boughtStockOp.get();
				soldStock = soldStockOp.get();
			}

			if ((boughtStock.size() - soldStock.size()) < number)
				throw new NotOwnedException();

		}

		orderLineDao.save(order);

		this.match();


	@Override
	public Enterprise createAnnualBenefits(Long userId, Long enterpriseId, AnnualBenefitsListDto benefitsList)
			throws DuplicateInstanceException, PermissionException, InstanceNotFoundException {

		Optional<User> userOp = null;
		User user = null;

		Optional<Enterprise> enterprise = enterpriseDao.findById(enterpriseId);

		if (enterprise.isEmpty()) {
			throw new InstanceNotFoundException("No existe empresa con id", enterpriseId);
		}
		Enterprise enter = enterprise.get();

		userOp = userDao.findById(userId);
		if (userOp.isPresent()) { // Aqui habría que añadir algo para cuando el user no exista
			user = userOp.get();

			if (user.getRole() != RoleType.ADMIN) {

				throw new PermissionException();
			} else {

				for (AnnualBenefitsParamsDto params : benefitsList.getBenefitsList()) {
					AnnualBenefits annualParms = new AnnualBenefits(enter, params.getYear(), params.getBenefits());
					enter.addAnnualBenefits(annualParms);
					annualBennefitsDao.save(annualParms);
				}

			}
		}

		return enter;
	}

}
