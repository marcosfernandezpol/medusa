package es.udc.fi.dc.fd.model.entities;


import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AnnualBenefitsTest {
	
	@Test
	public void annualBenefitsTest() {
		AnnualBenefits annualBenefits = new AnnualBenefits();
		long id = 1;
		int year = 5;
		Enterprise enterprise = new Enterprise();
		float benefits = 1.0F;
		
		annualBenefits.setId(id);
		annualBenefits.setYear(year);
		annualBenefits.setEnterprise(enterprise);
		annualBenefits.setBenefits(benefits);
		
		
		AnnualBenefits annualBenefits2 = new AnnualBenefits(enterprise,year,benefits);
		annualBenefits2.setId(id);

		
		AnnualBenefits annualBenefits3 = new AnnualBenefits(year,benefits);

		annualBenefits3.setId(id);
		annualBenefits3.setEnterprise(enterprise);
		
		
		Assert.assertEquals(annualBenefits.getId(),annualBenefits2.getId());
		Assert.assertEquals(annualBenefits.getEnterprise(),annualBenefits2.getEnterprise());
		Assert.assertEquals(annualBenefits.getYear(),annualBenefits2.getYear());
		Assert.assertEquals(annualBenefits.getBenefits(),annualBenefits2.getBenefits());
		
		Assert.assertTrue(annualBenefits.equals(annualBenefits2));
		Assert.assertTrue(annualBenefits.equals(annualBenefits3));

		Assert.assertTrue(annualBenefits.hashCode()==annualBenefits2.hashCode());
		
	}

}