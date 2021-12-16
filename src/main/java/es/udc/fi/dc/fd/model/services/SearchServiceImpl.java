package es.udc.fi.dc.fd.model.services;

import java.time.LocalDateTime;
import java.util.*;

import org.hibernate.boot.model.relational.AuxiliaryDatabaseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.dc.fd.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.dc.fd.model.entities.*;
import es.udc.fi.dc.fd.model.entities.OrderLine.OrderType;
import es.udc.fi.dc.fd.model.entities.User.UserType;

@Service
@Transactional(readOnly = true)
public class SearchServiceImpl implements SearchService {

	@Autowired
	private EnterpriseDao enterpriseDao;

	@Autowired
	private ActionPriceHistoricDao historicDao;

	@Autowired
	private OrderLineDao orderLineDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private StockMarketService marketService;

	private Enterprise getDelayedPriceEnterprise(Enterprise enterprise) {
		List<ActionPriceHistoric> historic = historicDao
				.findActionPriceHistoricByEnterpriseIdOrderByDateDesc(enterprise.getId());
		List<ActionPriceHistoric> result = new ArrayList<>();

		LocalDateTime standardTimeLapse = LocalDateTime.now().minusMinutes(10);

		boolean aux = false;
		
		for (int i = 0; i < historic.size() && aux == false; i++) {

			if (standardTimeLapse.isAfter(historic.get(i).getDate())) {
					enterprise.setStockPrice(historic.get(i).getPrice());
					aux = true;
			}
		}
		
		if (aux==false) {
				enterprise.setStockPrice(0f);
		}
			
		return enterprise;
	}

	@Override
	public List<Enterprise> findAllEnterprises(Long userId) throws InstanceNotFoundException {
		Optional<User> userOp = userDao.findById(userId);

		User user;

		if (userOp.isEmpty()) {
			throw new InstanceNotFoundException("No existe usuario con id", userId);
		} else
			user = userOp.get();

		List<Enterprise> enterpriseList = new ArrayList<>();

		Iterable<Enterprise> enterprises = enterpriseDao.findAll(Sort.by(Sort.Direction.ASC, "enterpriseName"));
		enterprises.forEach(enterpriseList::add);

		List<Enterprise> resultList = new ArrayList<>();
		if (user.getType() == UserType.STANDARD) {
			for (Enterprise enterprise : enterpriseList) {
				resultList.add(getDelayedPriceEnterprise(enterprise));
			}
		}

		if (resultList.isEmpty())
			return enterpriseList;
		else
			return resultList;
	}

	@Override
	public List<OrderLine> findOrders(Long userId, Boolean option, Boolean avaliable) {

		Optional<User> userOp = userDao.findById(userId);
		User user = null;
		List<OrderLine> returned = new ArrayList<>();
		if (userOp.isPresent())
			user = userOp.get();

		OrderType type = null;
		if (Boolean.TRUE.equals(option))
			type = OrderType.BUY;
		else
			type = OrderType.SELL;

		Optional<List<OrderLine>> orders = orderLineDao.findByOwnerAndOrderTypeAndAvaliableOrderByRequestDateDesc(user,
				type, avaliable);

		if (orders.isPresent()) {
			List<OrderLine> returnList = orders.get();
			if (Boolean.FALSE.equals(avaliable))
				return returnList;
			else {
				List<OrderLine> onTimeOrders = new ArrayList<>();
				for (OrderLine ord : returnList)
					if (ord.getDeadline().isAfter(LocalDateTime.now()))
						onTimeOrders.add(ord);

				return onTimeOrders;
			}
		} else {
			return returned;
		}

	}

	public Enterprise findEnterprise(Long userId, Long id) throws InstanceNotFoundException {

		Optional<Enterprise> enterprise = enterpriseDao.findById(id);
		Optional<User> userOp = userDao.findById(userId);

		User user;

		if (userOp.isEmpty()) {
			throw new InstanceNotFoundException("No existe usuario con id", userId);
		} else
			user = userOp.get();

		if (enterprise.isEmpty()) {
			throw new InstanceNotFoundException("No existe empresa con id", id);
		}
		Enterprise enter;
		enter = enterprise.get();

		if (user.getType() == UserType.STANDARD) {

			enter = getDelayedPriceEnterprise(enter);

		}

		return enter;
	}

	public List<ActionPriceHistoric> findHistorics(Long userId, Long id, int numberOfDays)
			throws InstanceNotFoundException {

		Optional<Enterprise> enterprise = enterpriseDao.findById(id);
		Optional<User> userOp = userDao.findById(userId);
		User user;

		if (enterprise.isEmpty()) {
			throw new InstanceNotFoundException("No existe empresa con id", id);
		}
		if (userOp.isEmpty()) {
			throw new InstanceNotFoundException("No existe usuario con id", userId);
		} else
			user = userOp.get();

		List<ActionPriceHistoric> historic = historicDao.findActionPriceHistoricByEnterpriseIdOrderByDateAsc(id);
		List<ActionPriceHistoric> result = new ArrayList<>();

		LocalDateTime aux = LocalDateTime.now().minusDays(numberOfDays);
		LocalDateTime standardTimeLapse = LocalDateTime.now().minusMinutes(10);

		if (user.getType() == UserType.PREMIUM) {
			for (int i = 0; i < historic.size(); i++) {

				if (aux.isBefore(historic.get(i).getDate())) {
					result.add(historic.get(i));
				}
			}
		} else {
			for (int i = 0; i < historic.size(); i++) {

				if (aux.isBefore(historic.get(i).getDate()) && standardTimeLapse.isAfter(historic.get(i).getDate())) {
					result.add(historic.get(i));
				}
			}
		}

		return result;
	}

	@Override
	public List<Actions> findUserActions(Long userId) throws InstanceNotFoundException {

		Optional<User> userOp = userDao.findById(userId);
		User user = null;

		if (userOp.isPresent())
			user = userOp.get();
		else
			throw new InstanceNotFoundException("No existe user con ese id", userId);

		List<Actions> userActions = new ArrayList<>();

		List<Enterprise> allEnterprises = findAllEnterprises(userId);

		for (Enterprise enterprise : allEnterprises) {
			int aux = marketService.searchUserActionsNumber(user, enterprise, true);
			if (aux != 0) {
				userActions.add(new Actions(aux, enterprise));
			}
		}

		return userActions;
	}

}
