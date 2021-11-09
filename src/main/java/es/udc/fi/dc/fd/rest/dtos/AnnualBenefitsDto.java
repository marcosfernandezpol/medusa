package es.udc.fi.dc.fd.rest.dtos;

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

}
