package es.udc.fi.dc.fd.rest.dtos;

import java.util.Objects;

public class AnnualBenefitsDto {

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

	private int year;

	private Float benefits;

	public AnnualBenefitsDto() {
	}

	public AnnualBenefitsDto(int year, Float benefits) {
		super();
		this.year = year;
		this.benefits = benefits;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Float getBenefits() {
		return benefits;
	}

	public void setBenefits(Float benefits) {
		this.benefits = benefits;
	}

	@Override
	public int hashCode() {
		return Objects.hash(benefits, id, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnnualBenefitsDto other = (AnnualBenefitsDto) obj;
		return Objects.equals(benefits, other.benefits) && Objects.equals(id, other.id) && year == other.year;
	}
	
	

}
