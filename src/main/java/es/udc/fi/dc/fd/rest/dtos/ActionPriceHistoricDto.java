package es.udc.fi.dc.fd.rest.dtos;

import java.util.Date;
import java.util.Objects;

public class ActionPriceHistoricDto {

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

	private Date date;

	private Float price;

	public ActionPriceHistoricDto() {
	}

	public ActionPriceHistoricDto(Date date, Float price) {
		super();
		this.date = date;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, id, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActionPriceHistoricDto other = (ActionPriceHistoricDto) obj;
		return Objects.equals(date, other.date) && Objects.equals(id, other.id) && Objects.equals(price, other.price);
	}

	

}
