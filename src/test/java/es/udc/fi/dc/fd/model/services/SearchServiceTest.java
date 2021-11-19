package es.udc.fi.dc.fd.model.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.fi.dc.fd.model.common.exceptions.DuplicateInstanceException;
import es.udc.fi.dc.fd.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.dc.fd.model.common.exceptions.InvalidOperationException;
import es.udc.fi.dc.fd.model.common.exceptions.NotEnoughBalanceException;
import es.udc.fi.dc.fd.model.common.exceptions.NotOwnedException;
import es.udc.fi.dc.fd.model.common.exceptions.NumberException;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.entities.EnterpriseDao;
import es.udc.fi.dc.fd.model.entities.OrderLine;
import es.udc.fi.dc.fd.model.entities.OrderLine.OrderLineType;
import es.udc.fi.dc.fd.model.entities.OrderLine.OrderType;
import es.udc.fi.dc.fd.model.entities.OrderLineDao;
import es.udc.fi.dc.fd.model.entities.User;
import es.udc.fi.dc.fd.model.entities.User.RoleType;
import es.udc.fi.dc.fd.model.entities.UserDao;
import es.udc.fi.dc.fd.model.services.exceptions.PermissionException;

/**
 * @author anton
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class SearchServiceTest {

	@Autowired
	private SearchService searchService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private StockMarketService marketService;

	@Autowired
	private EnterpriseDao enterpriseDao;

	@Autowired
	private OrderLineDao orderLineDao;

	/**
	 * Creates the user.
	 *
	 * @param userName the user name
	 * @return the user
	 */
	private User createUser(String userName) {
		return new User(userName, "firstName", "lastName", "password", userName + "@" + userName + ".com",
				User.RoleType.ADMIN, "Spain", "Galicia");
	}

	private Long adminId(User user) throws DuplicateInstanceException {

		userDao.save(user);

		return user.getId();
	}

	private Enterprise createEnterprise(String name, String acronim, long id) {
		return new Enterprise(id, name, acronim, Date.valueOf("1999-01-17"), Float.valueOf(1000), 12,
				Float.valueOf(18));

	}

	// Creamos un usuario de tipo Cliente
	private User createClient() {
		return new User("MariaM", "Maria", "Martinez", "password", "mariamartinez@gmail.com", "Spain", "A Coruña",
				RoleType.CLIENT, 1200F);

	}

	@Test
	public void testCreateEnterprise() throws DuplicateInstanceException, PermissionException, NumberException {

		User user = createUser("Manolo");
		Long id = adminId(user);

		List<Enterprise> enterprises = null;

		marketService.createEnterprise(id, createEnterprise("pol&sons", "PS", id));
		marketService.createEnterprise(id, createEnterprise("aòiergo", "ASD", id));

		enterprises = searchService.findAllEnterprises();

		for (int i = 0; i < 2; i++) {
			assertNotNull(enterprises.get(i).getId());

		}

	}

	@Test
	public void findOrders() throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException,
			DuplicateInstanceException, PermissionException, NumberException, NotOwnedException {

		User client = createClient();
		Long id = adminId(client);

		Enterprise enterprise = createEnterprise("adidas", "ads", id);

		User savedClient = userDao.save(client);
		Enterprise savedEnterprise = enterpriseDao.save(enterprise);

		orderLineDao.save(new OrderLine(OrderType.BUY, OrderLineType.LIMIT, savedClient, Float.valueOf(10), 3, savedEnterprise, LocalDate.now().plusDays(1)));
		orderLineDao.save(new OrderLine(OrderType.BUY, OrderLineType.LIMIT, savedClient, Float.valueOf(10), 3, savedEnterprise, LocalDate.now().plusDays(1)));
		orderLineDao.save(new OrderLine(OrderType.BUY, OrderLineType.LIMIT, savedClient, Float.valueOf(10), 3, savedEnterprise, LocalDate.now().plusDays(1)));
		List<OrderLine> orderList = searchService.findOrders(savedClient.getId(), true, true);

		assertTrue(orderList.size() == 3);

	}

	@Test
	public void findOrdersOutOfTime()
			throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException,
			DuplicateInstanceException, PermissionException, NumberException, NotOwnedException {

		User client = createClient();
		Long id = adminId(client);
		Enterprise enterprise = createEnterprise("adidas", "ads", id);

		User savedClient = userDao.save(client);
		Enterprise savedEnterprise = enterpriseDao.save(enterprise);

		orderLineDao.save(new OrderLine(OrderType.BUY, OrderLineType.LIMIT, savedClient, Float.valueOf(10), 3, savedEnterprise, LocalDate.now().minusDays(1)));
		orderLineDao.save(new OrderLine(OrderType.BUY, OrderLineType.LIMIT, savedClient, Float.valueOf(10), 3, savedEnterprise, LocalDate.now().plusDays(1)));
		orderLineDao.save(new OrderLine(OrderType.BUY, OrderLineType.LIMIT, savedClient, Float.valueOf(10), 3, savedEnterprise, LocalDate.now().plusDays(1)));
		List<OrderLine> orderList = searchService.findOrders(savedClient.getId(), true, true);

		assertTrue(orderList.size() == 2);

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testInstanceNotFoundExceptionUserDao() throws InstanceNotFoundException, DuplicateInstanceException {

		User client = createUser("jose");
		Long id = adminId(client);
		Enterprise enterprise = createEnterprise("adidas", "ads", id);

		Enterprise savedEnterprise = enterpriseDao.save(enterprise);
		searchService.findEnterprise(savedEnterprise.getId());
		searchService.findEnterprise(Long.valueOf(-1));

	}

}