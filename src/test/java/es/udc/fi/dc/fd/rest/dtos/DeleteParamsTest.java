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
public class DeleteParamsTest {
	
	@Test
	public void deleteParamsTest() {
		DeleteParamsDto deleteParams = new DeleteParamsDto();
		long orderId = 1L;
		boolean avaliable = true;

		deleteParams.setOrderId(orderId);
		deleteParams.setAvaliable(avaliable);
		
		Assert.assertTrue(deleteParams.getAvaliable().equals(avaliable));
		Assert.assertTrue(deleteParams.getOrderId().equals(orderId));
			
	}

}