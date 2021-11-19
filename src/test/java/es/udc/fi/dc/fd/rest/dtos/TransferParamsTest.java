package es.udc.fi.dc.fd.rest.dtos;

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
public class TransferParamsTest {
	
	@Test
	public void enterpriseParamsTest() {
		TransferParamsDto params = new TransferParamsDto();
		
		float money = 1F;
		String operation = "operation";
		params.setMoney(money);
		params.setOperation(operation);
		
		
		
		Assert.assertTrue(params.getOperation() == operation);
		Assert.assertTrue(params.getMoney() == money);

	}
	

}