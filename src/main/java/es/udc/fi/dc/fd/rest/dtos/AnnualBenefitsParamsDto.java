package es.udc.fi.dc.fd.rest.dtos;

import java.util.Objects;

public class AnnualBenefitsParamsDto {

	private int year;

	private Float benefits;

	public AnnualBenefitsParamsDto() {
		super();
	}

	public AnnualBenefitsParamsDto(int year, Float benefits) {
		super();
		this.year = year;
		this.benefits = benefits;
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
		return Objects.hash(benefits, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnnualBenefitsParamsDto other = (AnnualBenefitsParamsDto) obj;
		return Objects.equals(benefits, other.benefits) && year == other.year;
	}
	
	

}
