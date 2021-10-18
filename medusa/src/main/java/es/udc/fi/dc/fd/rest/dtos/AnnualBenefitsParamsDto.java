package es.udc.fi.dc.fd.rest.dtos;

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

}
