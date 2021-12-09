package es.udc.fi.dc.fd.rest.dtos;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import es.udc.fi.dc.fd.model.entities.OrderLine.*;

public class OrderParamsDto {

	private OrderType type;
	
	private OrderLineType orderLineType;

	private Float price;

	private int number;

	private Long enterpriseId;
	
	private LocalDateTime deadline;

	@NotNull
	public OrderType getType() {
		return type;
	}

	public void setType(OrderType type) {
		this.type = type;
	}
	
	@NotNull
	public OrderLineType getOrderLineType() {
		return orderLineType;
	}

	public void setOrderLineType(OrderLineType type) {
		this.orderLineType = type;
	}

	@NotNull
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@NotNull
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@NotNull
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	public LocalDateTime getDeadline() {
		return deadline;
	}
	
	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

}
