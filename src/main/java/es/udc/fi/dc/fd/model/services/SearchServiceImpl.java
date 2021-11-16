package es.udc.fi.dc.fd.model.services;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.dc.fd.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.dc.fd.model.entities.ActionPriceHistoric;
import es.udc.fi.dc.fd.model.entities.ActionPriceHistoricDao;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.entities.EnterpriseDao;
import es.udc.fi.dc.fd.model.entities.OrderLine;
import es.udc.fi.dc.fd.model.entities.OrderLine.OrderType;
import es.udc.fi.dc.fd.model.entities.OrderLineDao;
import es.udc.fi.dc.fd.model.entities.User;
import es.udc.fi.dc.fd.model.entities.UserDao;

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

	@Override
	public List<Enterprise> findAllEnterprises() {

		List<Enterprise> enterpriseList = new ArrayList<Enterprise>();

		Iterable<Enterprise> enterprises = enterpriseDao.findAll(Sort.by(Sort.Direction.ASC, "enterpriseName"));
		enterprises.forEach(enterpriseList::add);

		return enterpriseList;
	}

	@Override
	public List<OrderLine> findOrders(Long userId, Boolean option, Boolean avaliable) {

		Optional<User> userOp = userDao.findById(userId);
		User user = null;
		List<OrderLine> returned = new ArrayList<>();
		if (userOp.isPresent())
			user = userOp.get();

		OrderType type = null;
		if (option)
			type = OrderType.BUY;
		else
			type = OrderType.SELL;

		Optional<List<OrderLine>> orders = orderLineDao.findByOwnerAndOrderTypeAndAvaliableOrderByRequestDateDesc(user,
				type, avaliable);

		if (orders.isPresent()) {
			List<OrderLine> returnList = orders.get();
			if (!avaliable)
				return returnList;
			else {
				List<OrderLine> onTimeOrders = new ArrayList<OrderLine>();
				for (OrderLine ord : returnList) 
					if (ord.getDeadline().isAfter(LocalDate.now()))
						onTimeOrders.add(ord);
				
				return onTimeOrders;
			}
		} else {
			return returned;
		}

	}

	public Enterprise findEnterprise(Long id) throws InstanceNotFoundException {

		Optional<Enterprise> enterprise = enterpriseDao.findById(id);

		if (enterprise.isEmpty()) {
			throw new InstanceNotFoundException("No existe empresa con id", id);
		}
		Enterprise enter;
		enter = enterprise.get();
		return enter;
	}

	public List<ActionPriceHistoric> findHistorics(Long id, int numberOfDays) throws InstanceNotFoundException {

		Optional<Enterprise> enterprise = enterpriseDao.findById(id);

		if (enterprise.isEmpty()) {
			throw new InstanceNotFoundException("No existe empresa con id", id);
		}
		Enterprise enter;
		enter = enterprise.get();

		List<ActionPriceHistoric> historic = historicDao.findActionPriceHistoricByEnterpriseIdOrderByDateAsc(id);
		List<ActionPriceHistoric> result = new ArrayList<>();

		LocalDateTime aux = LocalDateTime.now().minusDays(numberOfDays);

		for (int i = 0; i < historic.size(); i++) {

			if (aux.isBefore(historic.get(i).getDate())) {
				result.add(historic.get(i));
			}
		}

		return result;
	}

	@Override
	public List<OrderLine> findUserActions(Long userId, Boolean avaliable) throws InstanceNotFoundException {
		

		Optional<User> userOp = userDao.findById(userId);
		User user = null;
		
		if (userOp.isPresent())
			user = userOp.get();
		else throw new InstanceNotFoundException("No existe user con ese id", userId);
		

		List<OrderLine> buyOrders = orderLineDao.findByOwnerAndOrderTypeAndAvaliableOrderByRequestDateDesc(user, OrderType.BUY, false).get();
		
		List<OrderLine> sellOrders = orderLineDao.findByOwnerAndOrderTypeAndAvaliableOrderByRequestDateDesc(user, OrderType.SELL, false).get();
		
		
		List<OrderLine> userActions = null;
		
		if(sellOrders==null) {
			userActions=buyOrders;
			return userActions;
		}
		
		if(buyOrders==null) {
			return null;
		}
		
		for(OrderLine orderLine : buyOrders) {
			if (sellOrders.contains(orderLine.getEnterprise())) {
				
			}
		}
		
		return userActions;
	}

}
