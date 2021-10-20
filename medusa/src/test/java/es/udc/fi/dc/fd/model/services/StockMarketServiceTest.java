package es.udc.fi.dc.fd.model.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
//import static Assertions.assertThrows;

import java.sql.Date;

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
import es.udc.fi.dc.fd.model.common.exceptions.NumberException;
import es.udc.fi.dc.fd.model.entities.Enterprise;
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
		resultEnterprise = stockMarketService.createEnterprise(admin.getId(), enterprise);

		assertNotNull(resultEnterprise.getId());

	}

	@Test
	public void testDepositTransfer()
			throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException {

		User client = createClient();

		userDao.save(client);

		stockMarketService.transfer(client.getId(), Float.valueOf(500), "INCOME");

		assertTrue(client.getBalance() == 1700F);
	}

	@Test
	public void testRetireTransfer()
			throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException {

		User client = createClient();

		userDao.save(client);

		stockMarketService.transfer(client.getId(), Float.valueOf(200), "WITHDRAW");
		assertTrue(client.getBalance() == 1000F);

	}

}
