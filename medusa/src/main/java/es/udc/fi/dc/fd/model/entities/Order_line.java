package es.udc.fi.dc.fd.model.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;

public class Order_line {

	public enum OrderType {
		/** The user. */
		SELL, BUY
	}

	private Long id;

	private LocalDateTime date;

	private OrderType type;

	private User owner;

	private Float price;

	private int number;

	private Enterprise enterprise;

	private Boolean avaliable;

	public Order_line() {
		super();
	}

	public Order_line(LocalDateTime date, OrderType type, User owner, Float price, int number, Enterprise enterprise) {
		super();
		this.date = date;
		this.type = type;
		this.owner = owner;
		this.price = price;
		this.number = number;
		this.enterprise = enterprise;
		this.avaliable = true;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public OrderType getType() {
		return type;
	}

	public void setType(OrderType type) {
		this.type = type;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
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

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	@Override
	public int hashCode() {
		return Objects.hash(avaliable, date, enterprise, id, number, owner, price, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order_line other = (Order_line) obj;
		return Objects.equals(avaliable, other.avaliable) && Objects.equals(date, other.date)
				&& Objects.equals(enterprise, other.enterprise) && Objects.equals(id, other.id)
				&& number == other.number && Objects.equals(owner, other.owner) && Objects.equals(price, other.price)
				&& type == other.type;
	}

	@Override
	public String toString() {
		return "Order_line [id=" + id + ", date=" + date + ", type=" + type + ", owner=" + owner + ", price=" + price
				+ ", number=" + number + ", enterprise=" + enterprise + ", avaliable=" + avaliable + "]";
	}

}
