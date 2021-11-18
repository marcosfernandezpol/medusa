package es.udc.fi.dc.fd.rest.dtos;

import java.time.LocalDateTime;
import java.time.LocalDate;

import es.udc.fi.dc.fd.model.entities.OrderLine.OrderType;
import es.udc.fi.dc.fd.model.entities.OrderLine.OrderLineType;

public class OrderLineDto {

	/**
	 * The Interface AllValidations.
	 */
	public interface AllValidations {
	}

	/**
	 * The Interface UpdateValidations.
	 */
	public interface UpdateValidations {
	}

	private Long id;

	private LocalDateTime requestDate;

	private OrderType orderType;

	private Long ownerId;

	private Float price;

	private int number;

	private Long enterpriseId;
	
	private LocalDate deadline;
	
	private OrderLineType orderLineType;
	
	

	public OrderLineDto() {
		super();
	}

	public OrderLineDto(Long id, LocalDateTime requestDate, OrderType orderType, Long ownerId, Float price, int number,
			Long enterpriseId) {
		super();
		this.id = id;
		this.requestDate = requestDate;
		this.orderType = orderType;
		this.ownerId = ownerId;
		this.price = price;
		this.number = number;
		this.enterpriseId = enterpriseId;
	}
	
	public OrderLineDto(Long id, LocalDateTime requestDate, OrderType orderType, Long ownerId, Float price, int number,
			Long enterpriseId, LocalDate deadline) {
		super();
		this.id = id;
		this.requestDate = requestDate;
		this.orderType = orderType;
		this.ownerId = ownerId;
		this.price = price;
		this.number = number;
		this.enterpriseId = enterpriseId;
		this.deadline = deadline;
	}
	
	public OrderLineDto(Long id, LocalDateTime requestDate, OrderType orderType, Long ownerId, Float price, int number,
			Long enterpriseId, LocalDate deadline, OrderLineType orderLineType) {
		super();
		this.id = id;
		this.requestDate = requestDate;
		this.orderType = orderType;
		this.ownerId = ownerId;
		this.price = price;
		this.number = number;
		this.enterpriseId = enterpriseId;
		this.deadline = deadline;
		this.orderLineType = orderLineType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public OrderLineType getOrderLineType() {
		return orderLineType;
	}

	public void setOrderLineType(OrderLineType type) {
		this.orderLineType = type;
	}

	public LocalDateTime getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDateTime requestDate) {
		this.requestDate = requestDate;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	@Override
	public String toString() {
		return "OrderLineDto [id=" + id + ", requestDate=" + requestDate + ", orderType=" + orderType + ", ownerId="
				+ ownerId + ", price=" + price + ", number=" + number + ", enterpriseId=" + enterpriseId + ", deadline="
				+ deadline + "]";
	}

}
