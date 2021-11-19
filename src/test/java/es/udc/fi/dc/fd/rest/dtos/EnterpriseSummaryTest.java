package es.udc.fi.dc.fd.rest.dtos;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.fi.dc.fd.model.entities.Enterprise;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class EnterpriseSummaryTest {
	
	@Test
	public void enterpriseParamsTest() {
		EnterpriseSummaryDto enterprise = new EnterpriseSummaryDto();
		long id = 1L;
		long creatorId = 1L;
		String enterpriseName = "Name";
		String acronim = "acronim";
		Date date = new Date(1000);
		Float incomes = 15F;
		int actions = 1000;
		Float actionsPrice = 20.00F;
		boolean avaliable = true;

		enterprise.setId(id);
		enterprise.setEnterpriseName(enterpriseName);
		enterprise.setAcronim(acronim);
		enterprise.setFundation(date);
		enterprise.setIncomes(incomes);
		enterprise.setActions(actions);
		enterprise.setActionsPrice(actionsPrice);
		enterprise.setCreatorId(creatorId);
		enterprise.setAvaliable(avaliable);
		
		EnterpriseSummaryDto enterprise2 = new EnterpriseSummaryDto(id,enterpriseName,creatorId,acronim,date,
				incomes,actions,actionsPrice,avaliable);
		
		
		Assert.assertTrue(enterprise.equals(enterprise2));
		Assert.assertTrue(enterprise2.hashCode()==(enterprise.hashCode()));
		Assert.assertTrue(enterprise2.getId()==(enterprise.getId()));
		Assert.assertTrue(enterprise2.getEnterpriseName()==(enterprise.getEnterpriseName()));
		Assert.assertTrue(enterprise2.getAcronim()==(enterprise.getAcronim()));
		Assert.assertTrue(enterprise2.getFundation()==(enterprise.getFundation()));
		Assert.assertTrue(enterprise2.getIncomes()==(enterprise.getIncomes()));
		Assert.assertTrue(enterprise2.getActions()==(enterprise.getActions()));
		Assert.assertTrue(enterprise2.getActionsPrice()==(enterprise.getActionsPrice()));
		Assert.assertTrue(enterprise2.isAvaliable()==(enterprise.isAvaliable()));
		Assert.assertTrue(enterprise2.getCreatorId()==(enterprise.getCreatorId()));
	
	}
	
	@Test
	public void enterpriseParamsConversorTest() {
		EnterpriseSummaryDto enterprise = new EnterpriseSummaryDto();
		Enterprise enterpiseAux = new Enterprise();
		EnterpriseSummaryDto enterprise2 = new EnterpriseSummaryDto();
		enterprise = EnterpriseSummaryConversor.toEnterpriseSummaryDto(enterpiseAux);
		Assert.assertTrue(enterprise.getClass().equals(enterprise2.getClass()));
		
		List<EnterpriseSummaryDto> enterprisesList = new ArrayList<>();
		List<EnterpriseSummaryDto> enterprisesList2 = new ArrayList<>();
		List<Enterprise> list = new ArrayList<>();
		enterprisesList = EnterpriseSummaryConversor.toEnterprisesDtos(list);
		Assert.assertTrue(enterprisesList.getClass().equals(enterprisesList2.getClass()));
	
		Enterprise aux = new Enterprise();
		Enterprise aux2 = new Enterprise();
		aux = EnterpriseSummaryConversor.toEnterpriseSummary(enterprise);
		Assert.assertTrue(aux2.getClass().equals(aux.getClass()));

	}

}