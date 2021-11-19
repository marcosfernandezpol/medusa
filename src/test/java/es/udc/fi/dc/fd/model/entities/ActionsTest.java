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
public class ActionsTest {
	
	@Test
	public void actionsTest() {
		Actions actions = new Actions();
		int number = 5;
		
		Enterprise enterprise = new Enterprise();
		
		actions.setEnterprise(enterprise);
		actions.setNumber(number);
		
		
		Actions actions2 = new Actions(number,enterprise);
		actions2.setNumber(number);
		actions2.setEnterprise(enterprise);
		
		Actions actions3 = new Actions(number,enterprise);

		String str = "Actions [number=" + number + ", enterprise=" + enterprise + "]";
		Assert.assertEquals(actions.getNumber(),actions2.getNumber());
		Assert.assertEquals(actions.getEnterprise(),actions2.getEnterprise());
		Assert.assertTrue(actions.equals(actions2));
		Assert.assertTrue(actions.equals(actions3));
		Assert.assertEquals(actions.toString(),str);
		Assert.assertTrue(actions.hashCode()==actions2.hashCode());
		
	}

}