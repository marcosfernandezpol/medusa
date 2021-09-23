package es.udc.fi.dc.fd.rest.dtos;

import java.sql.Date;

public class EnterpriseDto {

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

	private String enterpriseName;

	private String acronim;

	private Date fundation;

	private Float incomes;

	private Float annualBenefits;

	public EnterpriseDto() {
	}

	public EnterpriseDto(Long id, String enterpriseName, String acronim, Date fundation, Float incomes,
			Float annualBenefits) {
		super();
		this.id = id;
		this.enterpriseName = enterpriseName;
		this.acronim = acronim;
		this.fundation = fundation;
		this.incomes = incomes;
		this.annualBenefits = annualBenefits;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getAcronim() {
		return acronim;
	}

	public void setAcronim(String acronim) {
		this.acronim = acronim;
	}

	public Date getFundation() {
		return fundation;
	}

	public void setFundation(Date fundation) {
		this.fundation = fundation;
	}

	public Float getIncomes() {
		return incomes;
	}

	public void setIncomes(Float incomes) {
		this.incomes = incomes;
	}

	public Float getAnnualBenefits() {
		return annualBenefits;
	}

	public void setAnnualBenefits(Float annualBenefits) {
		this.annualBenefits = annualBenefits;
	}
	
	

}
