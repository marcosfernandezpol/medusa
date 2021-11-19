package es.udc.fi.dc.fd.model.entities;

import java.time.LocalDateTime;

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
public class ActionPriceHistoricTest {
	
	@Test
	public void actionPriceHistoricTest() {
		ActionPriceHistoric historic = new ActionPriceHistoric();
		
		Enterprise enterprise = new Enterprise();
		LocalDateTime date = LocalDateTime.now();
		historic.setId(1L);
		historic.setEnterprise(enterprise);
		historic.setDate(date);
		historic.setPrice(1.5F);
		
		ActionPriceHistoric historic2 = new ActionPriceHistoric(date,1.5F);
		historic2.setId(1L);
		historic2.setEnterprise(enterprise);
		
		ActionPriceHistoric historic3 = new ActionPriceHistoric(enterprise,date,1.5F);
		historic3.setId(1L);
		
		Assert.assertEquals(historic.getId(),historic2.getId());
		Assert.assertEquals(historic.getEnterprise(),historic2.getEnterprise());
		Assert.assertEquals(historic.getDate(),historic2.getDate());
		Assert.assertEquals(historic.getPrice(),historic2.getPrice());
		Assert.assertTrue(historic.equals(historic2));
		Assert.assertTrue(historic.equals(historic3));
		Assert.assertTrue(historic.hashCode()==historic3.hashCode());
		
	}

}