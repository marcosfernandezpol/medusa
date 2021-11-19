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
public class OrderLineTest {
	
	@Test
	public void orderLineParamsTest() {
		
		OrderLineDto params = new OrderLineDto();
		
		long id = 1L;
		LocalDateTime date = LocalDateTime.now();
		LocalDate localDate = LocalDate.now();
		OrderType orderType = OrderType.BUY;
		long ownerId = 1L;
		float price = 1.5F;
		int number = 100;
		long enterpriseId = 1L;
		OrderLineType orderLineType= OrderLineType.LIMIT;
		String str = "OrderLineDto [id=" + id + ", requestDate=" + date + ", orderType=" + orderType + ", ownerId="
				+ ownerId + ", price=" + price + ", number=" + number + ", enterpriseId=" + enterpriseId + ", deadline="
				+ localDate + "]";
		
		params.setId(id);
		params.setRequestDate(date);
		params.setOrderType(orderType);
		params.setOwnerId(ownerId);
		params.setPrice(price);
		params.setNumber(number);
		params.setEnterpriseId(enterpriseId);
		params.setDeadline(localDate);
		params.setOrderLineType(orderLineType);
		
		OrderLineDto params2 = new OrderLineDto(id,date,orderType,
				ownerId,price,number,enterpriseId);
		params2.setDeadline(localDate);
		params2.setOrderLineType(orderLineType);
		
		OrderLineDto params3 = new OrderLineDto(id,date,orderType,
				ownerId,price,number,enterpriseId,localDate);
		params3.setOrderLineType(orderLineType);
		
		OrderLineDto params4 = new OrderLineDto(id,date,orderType,
				ownerId,price,number,enterpriseId,localDate,orderLineType);
		
		Assert.assertTrue(params2.getId()==params4.getId());
		Assert.assertTrue(params2.getOrderLineType()==params4.getOrderLineType());
		Assert.assertTrue(params2.getRequestDate() == params4.getRequestDate());
		Assert.assertTrue(params2.getOrderType() == params4.getOrderType());
		Assert.assertTrue(params2.getOwnerId() == params4.getOwnerId());
		Assert.assertTrue(params2.getPrice().equals(params4.getPrice()));
		Assert.assertTrue(params2.getNumber() == params4.getNumber());
		Assert.assertTrue(params2.getEnterpriseId() == params4.getEnterpriseId());
		Assert.assertTrue(params2.getDeadline() == params4.getDeadline());
		
		Assert.assertTrue(params2.equals(params4));
		Assert.assertTrue(params2.hashCode() == params3.hashCode());
		Assert.assertTrue(params4.toString().equals(str));
	}
	

}