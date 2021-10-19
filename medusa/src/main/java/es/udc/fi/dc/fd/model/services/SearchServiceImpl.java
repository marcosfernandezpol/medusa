package es.udc.fi.dc.fd.model.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.dc.fd.model.common.exceptions.InstanceNotFoundException;
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
		if (userOp.isPresent())
			user = userOp.get();

		List<OrderLine> orders = new ArrayList<OrderLine>();

		OrderType type = null;
		if (option)
			type = OrderType.BUY;
		else
			type = OrderType.SELL;
			
		
		Iterable<OrderLine> ordersReturned = orderLineDao
				.findByOwnerAndOrderTypeAndAvaliableOrderByRequestDateDesc(user, type, avaliable);
		ordersReturned.forEach(orders::add);

		return orders;
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

}
