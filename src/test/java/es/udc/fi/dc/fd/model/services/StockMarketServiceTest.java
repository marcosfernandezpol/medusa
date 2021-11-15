package es.udc.fi.dc.fd.model.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
//import static Assertions.assertThrows;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

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
import es.udc.fi.dc.fd.model.entities.OrderLine.OrderType;
import es.udc.fi.dc.fd.model.entities.OrderLineDao;
import es.udc.fi.dc.fd.model.entities.User;
import es.udc.fi.dc.fd.model.entities.User.RoleType;
import es.udc.fi.dc.fd.model.entities.UserDao;
import es.udc.fi.dc.fd.model.services.exceptions.PermissionException;

/**
 * The Class StockMarketServiceTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class StockMarketServiceTest {

	@Autowired
	private StockMarketService stockMarketService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private EnterpriseDao enterpriseDao;

	@Autowired
	private OrderLineDao orderLineDao;

	// Creamos un usuario de tipo Administrador
	private User createAdmin() {
		return new User("Pol", "fdezpol", "masmejor", "password", "fdezpol007elmasmejor@gmail.com", "Spain", "Santiago",
				RoleType.ADMIN, 1000F);

	}

	// Creamos un usuario de tipo Cliente
	private User createClient() {
		return new User("MariaM", "Maria", "Martinez", "password", "mariamartinez@gmail.com", "Spain", "A Coruña",
				RoleType.CLIENT, 1200F);

	}

	// Creamos una empresa
	private Enterprise createEnterprise() {
		return new Enterprise("MedusaEnterprises", "ME", Date.valueOf("1999-01-17"), Float.valueOf(1000), 28,
				Float.valueOf(18));
	}

	@Test
	public void testCreateEnterprise() throws DuplicateInstanceException, PermissionException, NumberException {

		User admin = null;
		admin = createAdmin();
		userDao.save(admin);
		Enterprise enterprise = createEnterprise();
		Enterprise resultEnterprise = null;

		userDao.save(admin);
		enterprise.setCreatorId(admin.getId());
		resultEnterprise = stockMarketService.createEnterprise(admin.getId(), enterprise);

		assertNotNull(resultEnterprise.getId());

	}

	@Test
	public void testDepositTransfer()
			throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException {

		User client = createClient();

		userDao.save(client);

		float aux = stockMarketService.transfer(client.getId(), Float.valueOf(500), "INCOME");

		assertTrue(aux == client.getBalance());
	}

	@Test
	public void testRetireTransfer()
			throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException {

		User client = createClient();

		userDao.save(client);

		float aux = stockMarketService.transfer(client.getId(), Float.valueOf(200), "WITHDRAW");
		assertTrue(client.getBalance() == aux);

	}

	@Test
	public void createOrders() throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException,
			DuplicateInstanceException, PermissionException, NumberException, NotOwnedException {

		User client = createClient();
		Enterprise enterprise = createEnterprise();

		User savedClient = userDao.save(client);
		enterprise.setCreatorId(client.getId());
		Enterprise savedEnterprise = enterpriseDao.save(enterprise);

		stockMarketService.order(savedClient.getId(), OrderType.BUY, Float.valueOf(10), 3, savedEnterprise.getId(), LocalDate.now().plusDays(1));
		stockMarketService.order(savedClient.getId(), OrderType.BUY, Float.valueOf(10), 3, savedEnterprise.getId(), LocalDate.now().plusDays(1));
		stockMarketService.order(savedClient.getId(), OrderType.BUY, Float.valueOf(10), 3, savedEnterprise.getId(), LocalDate.now().plusDays(1));
		Optional<List<OrderLine>> orderListOp = orderLineDao
				.findByOwnerAndOrderTypeAndAvaliableOrderByRequestDateDesc(savedClient, OrderType.BUY, true);

		List<OrderLine> orderList = null;
		if (orderListOp.isPresent())
			orderList = orderListOp.get();

		assertTrue(orderList.size() == 3);

	}

}
