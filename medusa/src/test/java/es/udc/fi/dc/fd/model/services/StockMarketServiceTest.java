package es.udc.fi.dc.fd.model.services;

import java.sql.Date;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.fi.dc.fd.model.common.exceptions.DuplicateInstanceException;
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

	private User createAdmin() {
		return new User("Pol", "fdezpol", "masmejor", "password", "fdezpol007elmasmejor@gmail.com", "Santiago",
				"Galicia", RoleType.ADMIN);
	}

//	private User createUser() {
//		return new User("Pol", "fdezpol", "modouser", "password", "fdezpol007humilde@gmail.com", "Lugo", "Galicia",
//				RoleType.CLIENT);
//	}

	private Enterprise createEnterprise() {
		return new Enterprise("MedusaEnterprises", "ME", Date.valueOf("1999-01-17"), Float.valueOf(1000),
				Float.valueOf(10000));
	}

	@Test
	public void testCreateEnterprise() throws DuplicateInstanceException, PermissionException {

		User admin = null;
		admin = createAdmin();
		Enterprise enterprise = createEnterprise();
		Enterprise resultEnterprise = null;

		userDao.save(admin);
		resultEnterprise = stockMarketService.createEnterprise(admin.getId(), enterprise);
		
		assertNotNull(resultEnterprise.getId());

	}

//	@Test
//	public void testCreateEnterprisePermissionExcpt() throws DuplicateInstanceException, PermissionException {
//
//		User user = null;
//		user = createUser();
//		Enterprise enterprise = createEnterprise();
//
//		userDao.save(user);
//		assertThrows(() -> stockMarketService.createEnterprise(user.getId(), enterprise));
//
//	}

}
