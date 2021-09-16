package es.udc.fi.dc.fd.model.entities;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;

/**
 * The Class Enterprise.
 */
@Entity
public class Enterprise {
	
	//Start atributes
	
	private Long id;
	
	private String enterpriseName;
	
	private String acronim;
	
	private Date fundation;
	
	private Float incomes;
	
	private Float annualBenefits;
	
	//End atributes
	
	
	public Enterprise(Long id, String enterpriseName, String acronim, Date fundation, Float incomes,
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

	
	
	@Override
	public String toString() {
		return "Enterprise [id=" + id + ", enterpriseName=" + enterpriseName + ", acronim=" + acronim + ", fundation="
				+ fundation + ", incomes=" + incomes + ", annualBenefits=" + annualBenefits + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(acronim, annualBenefits, enterpriseName, fundation, id, incomes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enterprise other = (Enterprise) obj;
		return Objects.equals(acronim, other.acronim) && Objects.equals(annualBenefits, other.annualBenefits)
				&& Objects.equals(enterpriseName, other.enterpriseName) && Objects.equals(fundation, other.fundation)
				&& Objects.equals(id, other.id) && Objects.equals(incomes, other.incomes);
	}
	
	
	
}