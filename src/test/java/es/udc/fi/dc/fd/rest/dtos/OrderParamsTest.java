package es.udc.fi.dc.fd.rest.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.fi.dc.fd.model.entities.OrderLine.OrderLineType;
import es.udc.fi.dc.fd.model.entities.OrderLine.OrderType;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class OrderParamsTest {
	
	@Test
	public void orderParamsTest() {
		
		OrderParamsDto params = new OrderParamsDto();
		OrderType orderType = OrderType.BUY;
		float price = 1.5F;
		int number = 100;
		long enterpriseId = 1L;
		OrderLineType orderLineType= OrderLineType.LIMIT;
		LocalDateTime deadline = LocalDateTime.now();
		
		params.setType(orderType);
		params.setPrice(price);
		params.setNumber(number);
		params.setEnterpriseId(enterpriseId);
		params.setOrderLineType(orderLineType);
		params.setDeadline(deadline);
		
		Assert.assertTrue(params.getOrderLineType()==orderLineType);
		Assert.assertTrue(params.getType() == orderType);
		Assert.assertTrue(params.getPrice().equals(price));
		Assert.assertTrue(params.getNumber() == number);
		Assert.assertTrue(params.getEnterpriseId() == enterpriseId);
		Assert.assertTrue(params.getDeadline() == deadline);
		

	}
	

}