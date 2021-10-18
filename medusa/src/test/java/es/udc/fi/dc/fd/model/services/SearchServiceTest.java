package es.udc.fi.dc.fd.model.services;

import static org.junit.Assert.assertNotNull;


import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.fi.dc.fd.model.common.exceptions.*;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.entities.UserDao;
import es.udc.fi.dc.fd.model.entities.User;
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

	private Enterprise createEnterprise(String name, String acronim) {
		return new Enterprise(name, acronim, Date.valueOf("1999-01-17"), Float.valueOf(1000), 12, Float.valueOf(18));

	}

	@Test
	public void testCreateEnterprise() throws DuplicateInstanceException, PermissionException, NumberException {

		User user = createUser("Manolo");
		Long id = adminId(user);

		List<Enterprise> enterprises = null;

		marketService.createEnterprise(id, createEnterprise("pol&sons", "PS"));
		marketService.createEnterprise(id, createEnterprise("aòiergo", "ASD"));

		enterprises = searchService.findAllEnterprises();

		for (int i = 0; i < 2; i++) {
			assertNotNull(enterprises.get(i).getId());

		}

	}

}