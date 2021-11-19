package es.udc.fi.dc.fd.rest.dtos;

import java.util.ArrayList;
import java.util.List;

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
public class AnnualBenefitsTest {
	
	@Test
	public void annualBenefitsTest() {
		long id = 1L;
		int year = 2020;
		float benefits = 1.5F;
		
		AnnualBenefitsDto annualBenefits = new AnnualBenefitsDto();
		annualBenefits.setId(id);
		annualBenefits.setYear(year);
		annualBenefits.setBenefits(benefits);
		
		AnnualBenefitsDto annualBenefits2 = new AnnualBenefitsDto(year,benefits);
		annualBenefits2.setId(id);
	
		Assert.assertTrue(annualBenefits.equals(annualBenefits2));
		Assert.assertTrue(annualBenefits.hashCode()==annualBenefits2.hashCode());
		Assert.assertTrue(annualBenefits.getId().equals(annualBenefits2.getId()));
		Assert.assertTrue(annualBenefits.getYear() == (annualBenefits2.getYear()));
		Assert.assertTrue(annualBenefits.getBenefits().equals(annualBenefits2.getBenefits()));
		
		
	}
	
	@Test
	public void annualBenefitsConversorTest() {
		long id = 1L;
		int year = 2020;
		float benefits = 1.5F;
		
		AnnualBenefitsDto annualBenefits = new AnnualBenefitsDto();
		annualBenefits.setId(id);
		annualBenefits.setYear(year);
		annualBenefits.setBenefits(benefits);
		
		AnnualBenefitsDto annualBenefits2 = new AnnualBenefitsDto(year,benefits);
		annualBenefits2.setId(id);
	
		AnnualBenefits aux = new AnnualBenefits();
		AnnualBenefits aux2 = new AnnualBenefits();
		
		annualBenefits2 = AnnualBenefitsConversor.toAnnualBenefitsDto(aux);
		Assert.assertTrue(annualBenefits2.getClass()==annualBenefits.getClass());
		
		aux = AnnualBenefitsConversor.toAnnualBenefits(annualBenefits2);
		Assert.assertTrue(aux.getClass()==aux2.getClass());
		
		annualBenefits2 = AnnualBenefitsConversor.toAnnualBenefits(aux);
		Assert.assertTrue(annualBenefits2.getClass()==annualBenefits.getClass());
	
		Enterprise enterprise = new Enterprise();
		EnterpriseDto enterpriseAux = new EnterpriseDto();
		Enterprise enterpriseAux2 = new Enterprise();
		enterpriseAux2 = AnnualBenefitsConversor.toEnterprise(enterpriseAux);
		Assert.assertTrue(enterpriseAux2.getClass()==enterprise.getClass());
		
	}
	
	@Test
	public void annualBenefitsListTest() {
		List<AnnualBenefitsParamsDto> benefitsList = new ArrayList<>();
		AnnualBenefitsListDto list = new AnnualBenefitsListDto();
		
		list.setBenefitsList(benefitsList);
		AnnualBenefitsListDto list2 = new AnnualBenefitsListDto(benefitsList);

		Assert.assertTrue(list2.getClass()==list.getClass());
		Assert.assertTrue(list.getBenefitsList().equals(list2.getBenefitsList()));
		
	}
	
	@Test
	public void annualBenefitsParamsTest() {
		long id = 1L;
		int year = 2020;
		float benefits = 1.5F;
		
		AnnualBenefitsParamsDto annualBenefits = new AnnualBenefitsParamsDto();
		annualBenefits.setYear(year);
		annualBenefits.setBenefits(benefits);
		
		AnnualBenefitsParamsDto annualBenefits2 = new AnnualBenefitsParamsDto(year,benefits);
	
		Assert.assertTrue(annualBenefits.equals(annualBenefits2));
		Assert.assertTrue(annualBenefits.hashCode()==annualBenefits2.hashCode());
		Assert.assertTrue(annualBenefits.getYear() == (annualBenefits2.getYear()));
		Assert.assertTrue(annualBenefits.getBenefits().equals(annualBenefits2.getBenefits()));
		
		
	}
	

}