package es.udc.fi.dc.fd.rest.dtos;

import javax.validation.constraints.NotNull;

import es.udc.fi.dc.fd.model.entities.OrderLine.OrderType;

public class OrderParamsDto {

	private OrderType type;

	private Float price;

	private int number;

	private Long enterpriseId;

	@NotNull
	public OrderType getType() {
		return type;
	}

	public void setType(OrderType type) {
		this.type = type;
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

}
