package es.udc.fi.dc.fd.model.entities;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author siro
 *
 */
@Entity
public class OrderLine {

	public enum OrderType {
		/** The user. */
		SELL, BUY
	}

	public enum OrderLineType {
		/** The order. */
		LIMIT, MARKET
	}
	
	
	private Long id;

	private LocalDateTime requestDate;

	private OrderType orderType;
	
	private OrderLineType orderLineType;

	private User owner;

	private Float price;

	private int number;

	private Enterprise enterprise;

	private Boolean avaliable;
	
	private Boolean cancelled;

	private LocalDateTime deadline;

	public OrderLine() {
		super();
	}

	public OrderLine(OrderType orderType, OrderLineType orderLineType, User owner, Float price, int number, Enterprise enterprise) {
		super();
		this.requestDate = LocalDateTime.now();
		this.orderType = orderType;
		this.orderLineType = orderLineType;
		this.owner = owner;
		this.price = price;
		this.number = number;
		this.enterprise = enterprise;
		this.avaliable = true;
		this.cancelled = false;
	}

	public OrderLine(OrderType orderType, OrderLineType orderLineType, User owner, Float price, int number, Enterprise enterprise, LocalDateTime deadline) {
		super();
		this.requestDate = LocalDateTime.now();
		this.orderType = orderType;
		this.orderLineType = orderLineType;
		this.owner = owner;
		this.price = price;
		this.number = number;
		this.enterprise = enterprise;
		this.avaliable = true;
		this.cancelled = false;
		this.deadline = deadline;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDateTime date) {
		this.requestDate = date;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType type) {
		this.orderType = type;
	}
	
	public OrderLineType getOrderLineType() {
		return orderLineType;
	}

	public void setOrderLineType(OrderLineType type) {
		this.orderLineType = type;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
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

	public Boolean getAvaliable() {
		return avaliable;
	}

	public void setAvaliable(Boolean avaliable) {
		this.avaliable = avaliable;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	public Boolean getCancelled() {
		return cancelled;
	}

	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "enterpriseId")
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	@Override
	public int hashCode() {
		return Objects.hash(avaliable, requestDate, enterprise, id, number, owner, price, orderType, orderLineType, deadline);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderLine other = (OrderLine) obj;
		return Objects.equals(avaliable, other.avaliable) && Objects.equals(requestDate, other.requestDate)
				&& Objects.equals(enterprise, other.enterprise) && Objects.equals(id, other.id)
				&& number == other.number && Objects.equals(owner, other.owner) && Objects.equals(price, other.price)
				&& orderType == other.orderType && orderLineType == other.orderLineType && deadline == other.deadline && cancelled == other.cancelled;
	}

	@Override
	public String toString() {
		return "Order_line [id=" + id + ", date=" + requestDate + ", orderType=" + orderType + ", orderType=" + orderType 
				+ ", owner=" + owner + ", price=" + price + ", number=" + number + ", enterprise=" + enterprise
				+ ", avaliable=" + avaliable + ", deadLine=" + deadline + "]";
	}

}
