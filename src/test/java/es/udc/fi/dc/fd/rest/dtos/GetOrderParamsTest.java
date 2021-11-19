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
public class GetOrderParamsTest {
	
	@Test
	public void getOrderParamsTest() {
		
		GetOrdersParamsDto params = new GetOrdersParamsDto();
		
		long userId = 1L;
		boolean option = true;
		boolean avaliable = true;
		
		params.setAvaliable(avaliable);
		params.setOption(option);
		params.setUserId(userId);
		
		Assert.assertTrue(params.getUserId() == userId);
		Assert.assertTrue(params.getOption() == option);
		Assert.assertTrue(params.getAvaliable() == avaliable);
	}
	
	

}