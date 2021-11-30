package es.udc.fi.dc.fd.rest.dtos;

import java.time.LocalDateTime;
import java.util.Objects;
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
	
	private LocalDateTime deadline;
	
	private OrderLineType orderLineType;
	
	private boolean cancelled;
	
	
	
	

	public OrderLineDto() {
		super();
	}

	public OrderLineDto(Long id, LocalDateTime requestDate, OrderType orderType, Long ownerId, Float price, int number,
			Long enterpriseId, boolean cancelled) {
		super();
		this.id = id;
		this.requestDate = requestDate;
		this.orderType = orderType;
		this.ownerId = ownerId;
		this.price = price;
		this.number = number;
		this.enterpriseId = enterpriseId;
		this.cancelled = cancelled;
	}
	
	public OrderLineDto(Long id, LocalDateTime requestDate, OrderType orderType, Long ownerId, Float price, int number,
			Long enterpriseId, LocalDateTime deadline, boolean cancelled) {
		super();
		this.id = id;
		this.requestDate = requestDate;
		this.orderType = orderType;
		this.ownerId = ownerId;
		this.price = price;
		this.number = number;
		this.enterpriseId = enterpriseId;
		this.deadline = deadline;
		this.cancelled = cancelled;
	}
	
	public OrderLineDto(Long id, LocalDateTime requestDate, OrderType orderType, Long ownerId, Float price, int number,
			Long enterpriseId, LocalDateTime deadline, OrderLineType orderLineType, boolean cancelled) {
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
		this.cancelled = cancelled;
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
	
	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cancelled, deadline, enterpriseId, id, number, orderLineType, orderType, ownerId, price,
				requestDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderLineDto other = (OrderLineDto) obj;
		return cancelled == other.cancelled && Objects.equals(deadline, other.deadline)
				&& Objects.equals(enterpriseId, other.enterpriseId) && Objects.equals(id, other.id)
				&& number == other.number && orderLineType == other.orderLineType && orderType == other.orderType
				&& Objects.equals(ownerId, other.ownerId) && Objects.equals(price, other.price)
				&& Objects.equals(requestDate, other.requestDate);
	}

	@Override
	public String toString() {
		return "OrderLineDto [id=" + id + ", requestDate=" + requestDate + ", orderType=" + orderType + ", ownerId="
				+ ownerId + ", price=" + price + ", number=" + number + ", enterpriseId=" + enterpriseId + ", deadline="
				+ deadline + ", orderLineType=" + orderLineType + ", cancelled=" + cancelled + "]";
	}

	

}
