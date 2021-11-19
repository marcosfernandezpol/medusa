package es.udc.fi.dc.fd.model.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

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
public class EnterpriseTest {
	
	@Test
	public void enterpriseTest() {
		Enterprise enterprise = new Enterprise();
		long id = 1;
		long creatorId = 5;
		String enterpriseName = "EnterpriseName" ;
		String acronim = "EN";
		Date date = new Date(creatorId);
		float incomes = 12F;
		int actions = 1000;
		float actionsPrice = 10;
		AnnualBenefits ab2 = new AnnualBenefits();
		Set<AnnualBenefits> annualBenefits = new HashSet<AnnualBenefits>();
		enterprise.addAnnualBenefits(ab2);
		annualBenefits.add(ab2);
		boolean avaliable = true;
		enterprise.setId(id);
		enterprise.setCreatorId(creatorId);
		enterprise.setEnterpriseName(enterpriseName);
		enterprise.setAcronim(acronim);
		enterprise.setFundation(date);
		enterprise.setIncomes(incomes);
		enterprise.setStock(actions);
		enterprise.setStockPrice(actionsPrice);
		enterprise.setAvaliable(avaliable);
		
		Enterprise enterprise2 = new Enterprise(id,creatorId,enterpriseName,
				acronim,date,incomes,actions,actionsPrice,annualBenefits,
				avaliable);
		
		Assert.assertTrue(enterprise.equals(enterprise2));
		Assert.assertTrue(enterprise.getAnnualBenefits().equals(enterprise2.getAnnualBenefits()));
		

		
		
	}

}