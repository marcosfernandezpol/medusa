package es.udc.fi.dc.fd.rest.dtos;

import java.time.LocalDateTime;
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

	private String x;

	private Float y;

	public ActionPriceHistoricDto() {
	}

	public ActionPriceHistoricDto(String date, Float price) {
		super();
		this.x = date;
		this.y = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getX() {
		return x;
	}

	public void setX(String date) {
		this.x = date;
	}

	public Float getY() {
		return y;
	}

	public void setY(Float price) {
		this.y = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, id, y);
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
		return Objects.equals(x, other.x) && Objects.equals(id, other.id) && Objects.equals(y, other.y);
	}

	

}
