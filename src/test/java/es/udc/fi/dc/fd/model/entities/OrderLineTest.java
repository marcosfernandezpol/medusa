package es.udc.fi.dc.fd.model.entities;

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
	public void userTest() {
		
		OrderLine order = new OrderLine();
		Long id = 1L;
		LocalDateTime date = LocalDateTime.now();
		OrderType orderType = OrderType.BUY;
		OrderLineType orderLineType = OrderLineType.LIMIT;
		User user = new User();
		float price = 1F;
		int number = 100;
		Enterprise enterprise = new Enterprise();
		boolean avaliable = true;
		boolean cancelled = false;
		LocalDateTime deadline = LocalDateTime.now();
		
		order.setId(id);
		order.setRequestDate(date);
		order.setOrderType(orderType);
		order.setOrderLineType(orderLineType);
		order.setOwner(user);
		order.setPrice(price);
		order.setNumber(number);
		order.setEnterprise(enterprise);
		order.setAvaliable(avaliable);
		order.setDeadline(deadline);
		order.setCancelled(cancelled);
		
		OrderLine order2 = new OrderLine(
				orderType,orderLineType,user,price,number,enterprise);
		
		order2.setId(id);
		order2.setRequestDate(date);
		order2.setAvaliable(avaliable);
		order2.setDeadline(deadline);
		order2.setCancelled(cancelled);
		
		
		
		
		
		Assert.assertEquals(order,order2);
		Assert.assertTrue(order.hashCode()==(order2.hashCode()));
	
		String str = "Order_line [id=" + id + ", date=" + date + ", orderType=" + orderType + ", orderType=" + orderType 
				+ ", owner=" + user + ", price=" + price + ", number=" + number + ", enterprise=" + enterprise
				+ ", avaliable=" + avaliable + ", deadLine=" + deadline + "]";
		Assert.assertTrue(order.toString().equals(str));
		
	}

}