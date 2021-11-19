package es.udc.fi.dc.fd.rest.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.fi.dc.fd.model.entities.AnnualBenefits;
import es.udc.fi.dc.fd.model.entities.Enterprise;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class EnterpriseTest {
	
	@Test
	public void enterpriseParamsTest() {
		EnterpriseDto enterprise = new EnterpriseDto();
		long id = 1L;
		String enterpriseName = "Name";
		String acronim = "acronim";
		Date date = new Date(1000);
		Float incomes = 15F;
		int actions = 1000;
		Float actionsPrice = 20.00F;
		List<AnnualBenefitsDto> anualBenefitsDto = new ArrayList<>();
		boolean avaliable = true;

		enterprise.setId(id);
		enterprise.setEnterpriseName(enterpriseName);
		enterprise.setAcronim(acronim);
		enterprise.setFundation(date);
		enterprise.setIncomes(incomes);
		enterprise.setActions(actions);
		enterprise.setActionsPrice(actionsPrice);
		enterprise.setanualBenefitsDto(anualBenefitsDto);
		enterprise.setAvaliable(avaliable);
		
		EnterpriseDto enterprise2 = new EnterpriseDto(id,enterpriseName,acronim,date,
				incomes,actions,actionsPrice,anualBenefitsDto);
		enterprise.setAvaliable(avaliable);
		
		EnterpriseDto enterprise3 = new EnterpriseDto(id,enterpriseName,acronim,date,
				incomes,actions,actionsPrice,anualBenefitsDto,avaliable);
		
		Assert.assertTrue(enterprise.equals(enterprise2));
		Assert.assertTrue(enterprise2.equals(enterprise3));
		Assert.assertTrue(enterprise2.getanualBenefitsDto().equals(enterprise3.getanualBenefitsDto()));
		Assert.assertTrue(enterprise2.isAvaliable()==(enterprise3.isAvaliable()));
		Assert.assertTrue(enterprise2.hashCode()==(enterprise3.hashCode()));
		Assert.assertTrue(enterprise2.getId()==(enterprise3.getId()));
	}
	
	@Test
	public void enterpriseParamsConversorTest() {
		EnterpriseDto enterprise = new EnterpriseDto();
		long id = 1L;
		String enterpriseName = "Name";
		String acronim = "acronim";
		Date date = new Date(1000);
		Float incomes = 15F;
		int actions = 1000;
		Float actionsPrice = 20.00F;
		List<AnnualBenefitsDto> anualBenefitsDto = new ArrayList<>();
		boolean avaliable = true;

		enterprise.setId(id);
		enterprise.setEnterpriseName(enterpriseName);
		enterprise.setAcronim(acronim);
		enterprise.setFundation(date);
		enterprise.setIncomes(incomes);
		enterprise.setActions(actions);
		enterprise.setActionsPrice(actionsPrice);
		enterprise.setanualBenefitsDto(anualBenefitsDto);
		enterprise.setAvaliable(avaliable);
		
		EnterpriseDto enterprise2 = new EnterpriseDto(id,enterpriseName,acronim,date,
				incomes,actions,actionsPrice,anualBenefitsDto);
		enterprise.setAvaliable(avaliable);
		
		EnterpriseDto enterprise3 = new EnterpriseDto(id,enterpriseName,acronim,date,
				incomes,actions,actionsPrice,anualBenefitsDto,avaliable);
		
		Enterprise enterpriseAux = new Enterprise();
		Enterprise enterpriseAux2 = new Enterprise();
		EnterpriseDto enterpriseDto = new EnterpriseDto();
		long creatorId = 5;
		AnnualBenefits ab2 = new AnnualBenefits();
		Set<AnnualBenefits> annualBenefits = new HashSet<AnnualBenefits>();
		enterpriseAux.addAnnualBenefits(ab2);
		annualBenefits.add(ab2);
		enterpriseAux.setId(id);
		enterpriseAux.setCreatorId(creatorId);
		enterpriseAux.setEnterpriseName(enterpriseName);
		enterpriseAux.setAcronim(acronim);
		enterpriseAux.setFundation(date);
		enterpriseAux.setIncomes(incomes);
		enterpriseAux.setStock(actions);
		enterpriseAux.setStockPrice(actionsPrice);
		enterpriseAux.setAvaliable(avaliable);
		
		enterpriseDto = EnterpriseConversor.toEnterpriseDto(enterpriseAux);
		Assert.assertTrue(enterpriseDto.getClass().equals(enterprise.getClass()));
		
		enterpriseAux = EnterpriseConversor.toEnterprise(enterprise2);
		Assert.assertTrue(enterpriseAux.getClass().equals(enterpriseAux2.getClass()));
		
		List<EnterpriseDto> enterprisesDto = new ArrayList<>();
		List<Enterprise> list = new ArrayList<>();
		List<EnterpriseDto> list2 = new ArrayList<>();
		
		list2 = EnterpriseConversor.toEnterprisesDtos(list);
		Assert.assertTrue(list2.getClass().equals(list.getClass()));

		
		
	}

}