package es.udc.fi.dc.fd.model.services;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import es.udc.fi.dc.fd.model.common.exceptions.DuplicateInstanceException;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.entities.EnterpriseDao;
import es.udc.fi.dc.fd.model.services.exceptions.PermissionException;

/**
 * @author anton
 *
 */
public class SearchServiceTest {
	

	@Autowired
	private SearchService searchService;
	private EnterpriseDao enterpriseDao;


	private Enterprise createEnterprise(String name, String acronim) {
		return new Enterprise(name, acronim, Date.valueOf("1999-01-17"), Float.valueOf(1000),
				Float.valueOf(10000));
	}
	
	@Test
	public void testCreateEnterprise() throws DuplicateInstanceException, PermissionException {

		List<Enterprise> enterprises;
		
		enterpriseDao.save(createEnterprise("pol&sons", "PS"));
		enterpriseDao.save(createEnterprise("aòiergo", "ASD"));
		
		enterprises = searchService.findAllEnterprises();
		
		for (int i = 0; i < 2; i++) {
			assertNotNull(	enterprises.get(i).getId());

		}

	}

}
