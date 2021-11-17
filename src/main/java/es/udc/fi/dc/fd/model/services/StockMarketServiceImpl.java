package es.udc.fi.dc.fd.model.services;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.dc.fd.model.common.exceptions.DuplicateInstanceException;
import es.udc.fi.dc.fd.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.dc.fd.model.common.exceptions.InvalidOperationException;
import es.udc.fi.dc.fd.model.common.exceptions.NotAvaliableException;
import es.udc.fi.dc.fd.model.common.exceptions.NotCreatorException;
import es.udc.fi.dc.fd.model.common.exceptions.NotEnoughBalanceException;
import es.udc.fi.dc.fd.model.common.exceptions.NotOwnedException;
import es.udc.fi.dc.fd.model.common.exceptions.NumberException;
import es.udc.fi.dc.fd.model.entities.ActionPriceHistoric;
import es.udc.fi.dc.fd.model.entities.ActionPriceHistoricDao;
import es.udc.fi.dc.fd.model.entities.AnnualBenefits;
import es.udc.fi.dc.fd.model.entities.AnnualBenefitsDao;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.entities.EnterpriseDao;
import es.udc.fi.dc.fd.model.entities.OrderLine;
import es.udc.fi.dc.fd.model.entities.OrderLine.OrderLineType;
import es.udc.fi.dc.fd.model.entities.OrderLine.OrderType;
import es.udc.fi.dc.fd.model.entities.OrderLineDao;
import es.udc.fi.dc.fd.model.entities.User;
import es.udc.fi.dc.fd.model.entities.User.RoleType;
import es.udc.fi.dc.fd.model.entities.UserDao;
import es.udc.fi.dc.fd.model.services.exceptions.InvalidArgumentException;
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
	
	@Autowired
	private ActionPriceHistoricDao actionPriceHistoricDao;

	@Override
	public Enterprise createEnterprise(Long userId, Enterprise enterprise)
			throws DuplicateInstanceException, PermissionException, NumberException {

		Optional<User> userOp = null;
		User user = null;

		if (enterpriseDao.existsByEnterpriseName(enterprise.getEnterpriseName())) {
			throw new DuplicateInstanceException("project.entities.enterprise", enterprise.getEnterpriseName());
		}

		userOp = userDao.findById(userId);
		if (userOp.isPresent()) { // Aqui habría que añadir algo para cuando el user no exista
			user = userOp.get();

			if (user.getRole() == RoleType.ADMIN) {

				Calendar cal = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();
				cal.setTime(enterprise.getFundation());
				LocalDate now = LocalDate.now();
				cal2.set(now.getYear(), now.getMonthValue() - 1, now.getDayOfMonth());

				if (cal.after(cal2)) {
					throw new PermissionException();
				}

				enterpriseDao.save(enterprise);

				OrderLine order = new OrderLine(OrderType.SELL, OrderLineType.LIMIT ,null, enterprise.getStockPrice(), enterprise.getStock(),
						enterprise);

				orderLineDao.save(order);

			} else {
				throw new PermissionException();
			}
		}

		return enterprise;
	}

	@Override
	public float transfer(Long userId, Float money, String operation)
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
			return user.getBalance();
		} else if (operation.equals("WITHDRAW")) { // Caso de retirar
			if (user.getBalance() >= money) {
				user.setBalance(user.getBalance() - money);
				userDao.save(user);
				return user.getBalance();
			} else {

				throw new NotEnoughBalanceException("Not enough balance"); // Cantidad para retirar insuficiente
			}

		} else {
			throw new InvalidOperationException();
		}

	}
	
	private void matchUserManagement (OrderLine buyOrder, OrderLine sellOrder, int numSold, float operationPrice) {
		User buyOwner = buyOrder.getOwner();
		User sellOwner = sellOrder.getOwner();
		
		buyOwner.setBalance(
				buyOwner.getBalance() - (operationPrice * numSold));
		if (sellOwner != null) {
			sellOwner.setBalance(
					sellOwner.getBalance() + (operationPrice * numSold));
		}
		
	}
	
	private void matchOrderManagement(OrderLine buyOrder, OrderLine sellOrder, int numSold, int numRemain,
			int control) {
		if (control == 0) {
			OrderLine sellRemain = new OrderLine(OrderType.SELL, OrderLineType.LIMIT, sellOrder.getOwner(), sellOrder.getPrice(), numRemain,
					sellOrder.getEnterprise(), sellOrder.getDeadline());
			sellRemain.setRequestDate(sellOrder.getRequestDate());
			orderLineDao.save(sellRemain);

			sellOrder.setNumber(numSold);
			sellOrder.setAvaliable(false);

			buyOrder.setPrice(sellOrder.getPrice());
			buyOrder.setAvaliable(false);

			match(sellRemain.getEnterprise());
		} else if (control == 1) {
			OrderLine buyRemain = new OrderLine(OrderType.BUY, OrderLineType.LIMIT, buyOrder.getOwner(), buyOrder.getPrice(), numRemain,
					buyOrder.getEnterprise(), buyOrder.getDeadline());
			buyRemain.setRequestDate(buyOrder.getRequestDate());
			orderLineDao.save(buyRemain);

			buyOrder.setNumber(numSold);
			buyOrder.setPrice(sellOrder.getPrice());
			buyOrder.setAvaliable(false);

			sellOrder.setAvaliable(false);

			match(buyRemain.getEnterprise());
		} else if (control == 2) {
			buyOrder.setPrice(sellOrder.getPrice());

			buyOrder.setAvaliable(false);
			sellOrder.setAvaliable(false);
		}
	}

	private void match(Enterprise enterprise) {

		Optional<List<OrderLine>> buyOrdersO = orderLineDao
				.findByOrderTypeAndEnterpriseAndAvaliableOrderByRequestDateDesc(OrderType.BUY, enterprise, true);

		Optional<List<OrderLine>> sellOrdersO = orderLineDao
				.findByOrderTypeAndEnterpriseAndAvaliableOrderByRequestDateDesc(OrderType.SELL, enterprise, true);

		if (!(buyOrdersO.isEmpty() || sellOrdersO.isEmpty())) {

			List<OrderLine> buyOrders = buyOrdersO.get();
			List<OrderLine> sellOrders = sellOrdersO.get();

			for (OrderLine buyOrder : buyOrders) {

				if (buyOrder.getDeadline()==null || buyOrder.getDeadline().isAfter(LocalDate.now())) {
					for (OrderLine sellOrder : sellOrders) {

						if (sellOrder.getDeadline()==null || sellOrder.getDeadline().isAfter(LocalDate.now())) {
							if (sellOrder.getPrice() <= buyOrder.getPrice()) {

								float operationPrice = sellOrder.getPrice();

								if (sellOrder.getAvaliable() && buyOrder.getAvaliable()) {

									if (sellOrder.getNumber() > buyOrder.getNumber()) {
										int numSold = buyOrder.getNumber();
										int numRemain = sellOrder.getNumber() - numSold;

										matchUserManagement(buyOrder, sellOrder, numSold, operationPrice);

										matchOrderManagement(buyOrder, sellOrder, numSold, numRemain, 0);

									} else if (sellOrder.getNumber() < buyOrder.getNumber()) {

										int numSold = sellOrder.getNumber();
										int numRemain = buyOrder.getNumber() - numSold;

										matchUserManagement(buyOrder, sellOrder, numSold, operationPrice);

										matchOrderManagement(buyOrder, sellOrder, numSold, numRemain, 1);

									} else {
										int numSold = sellOrder.getNumber();
										int numRemain = 0;

										matchUserManagement(buyOrder, sellOrder, numSold, operationPrice);

										matchOrderManagement(buyOrder, sellOrder, numSold, numRemain, 2);
									}

									enterprise.setStockPrice(sellOrder.getPrice());
									ActionPriceHistoric historic = new ActionPriceHistoric(enterprise,LocalDateTime.now(),sellOrder.getPrice());
									actionPriceHistoricDao.save(historic);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public int searchUserActionsNumber(User user, Enterprise enterprise) {
		List<OrderLine> boughtStock = null;
		List<OrderLine> soldStock = null;

		Optional<List<OrderLine>> boughtStockOp = orderLineDao
				.findByOrderTypeAndOwnerAndEnterpriseAndAvaliableOrderByRequestDateDesc(OrderType.BUY, user,
						enterprise, false);
		Optional<List<OrderLine>> soldStockOp = orderLineDao
				.findByOrderTypeAndOwnerAndEnterpriseOrderByRequestDateDesc(OrderType.SELL, user, enterprise);

		int bs = 0;
		int ss = 0;

		if (boughtStockOp.isPresent()) {
			boughtStock = boughtStockOp.get();
			for (OrderLine orderLine : boughtStock) {
				bs += orderLine.getNumber();
			}

		} 

		if (soldStockOp.isPresent()) {
			soldStock = soldStockOp.get();
			for (OrderLine orderLine : soldStock) {
				ss += orderLine.getNumber();
			}
		}
		
		return bs-ss;
	}

	@Override
	public void order(Long owner, OrderType orderType, OrderLineType orderLineType, Float price, int number, Long enterpriseId, LocalDate deadline)
			throws NotEnoughBalanceException, NotOwnedException, NotAvaliableException {

		User user = null;
		Enterprise enterprise = null;

		Optional<User> userOp = userDao.findById(owner);
		Optional<Enterprise> enterpriseOp = enterpriseDao.findById(enterpriseId);

		if (userOp.isPresent())
			user = userOp.get(); // Else exception
		if (enterpriseOp.isPresent())
			enterprise = enterpriseOp.get();// Else exception
		
		if(enterprise.isAvaliable()) {
			OrderLine order = new OrderLine(orderType, orderLineType, user, price, number, enterprise, deadline);
	
			if (order.getOrderType() == OrderType.BUY) {
				if (user.getBalance() < (price * number))
					throw new NotEnoughBalanceException("Offering more money than owned");
	
			} else {
	
				int ownedActionNumber = searchUserActionsNumber(user, enterprise);
				
				if (ownedActionNumber < number) {
					throw new NotOwnedException();
				}
	
			}
			orderLineDao.save(order);
			
	
			this.match(enterprise);
		} else {
			throw new NotAvaliableException();
		}
	}

	@Override
	public Enterprise createAnnualBenefits(Long userId, Long enterpriseId, AnnualBenefitsListDto benefitsList)
			throws DuplicateInstanceException, PermissionException, InstanceNotFoundException,
			InvalidArgumentException {

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
					Calendar cal = Calendar.getInstance();
					cal.setTime(enter.getFundation());
					int year = LocalDate.now().getYear();
					Set<AnnualBenefits> benefits = enter.getAnnualBenefits();
					if (!benefits.isEmpty()) {
						Iterator<AnnualBenefits> iter = benefits.iterator();
						while (iter.hasNext()) {
							if (iter.next().getYear() == params.getYear())
								throw new InvalidArgumentException();
						}
					}

					if (params.getYear() < cal.get(Calendar.YEAR) || params == null || params.getYear() >= year) {
						throw new InvalidArgumentException();
					} else {
						AnnualBenefits annualParms = new AnnualBenefits(enter, params.getYear(), params.getBenefits());
						enter.addAnnualBenefits(annualParms);
						annualBennefitsDao.save(annualParms);
					}
				}

			}
		}

		return enter;
	}

	public void deleteOrder(Long owner, Long orderId, Boolean avaliable)
			throws NotOwnedException, InstanceNotFoundException, NotAvaliableException {

		OrderLine order = null;

		Optional<User> userOp = userDao.findById(owner);
		Optional<OrderLine> orderOp = orderLineDao.findById(orderId);

		if (userOp.get().equals(orderOp.get().getOwner())) { //condition changed

			if (orderOp.isPresent()) {
				order = orderOp.get();

				if (avaliable) {
					orderLineDao.delete(order);
				} else {
					throw new NotAvaliableException();
				}

			} else {
				throw new InstanceNotFoundException("No existe order con id", orderId);
			}

		} else {
			throw new NotOwnedException();
		}

	}
	
	@Override
	public Enterprise modifyAvaliableEnterprise(Long adminId, Long enterpriseId, Boolean avaliable)
			throws NotCreatorException, InstanceNotFoundException {

		Enterprise enterprise = null;

		Optional<User> adminOp = userDao.findById(adminId);
		Optional<Enterprise> enterpriseOp = enterpriseDao.findById(enterpriseId);

		if (adminOp.isEmpty() || enterpriseOp.isEmpty()) {
			throw new InstanceNotFoundException("No existe empresa con id", adminId);
		}

		enterprise = enterpriseOp.get();

		if (adminId != enterprise.getCreatorId()) {
			throw new NotCreatorException();
		}

		enterprise.setAvaliable(avaliable);
		enterpriseDao.save(enterprise);

		return enterprise;

	}

}
