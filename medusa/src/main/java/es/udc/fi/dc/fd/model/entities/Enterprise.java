package es.udc.fi.dc.fd.model.entities;

import java.beans.Transient;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * The Class Enterprise.
 */
@Entity
public class Enterprise {

	// Start atributes

	private Long id;

	private String enterpriseName;

	private String acronim;

	private Date fundation;

	private Float incomes;

	private int actions;

	private Float actionsPrice;

	private Set<AnnualBenefits> annualBenefits = new HashSet<>();

	// End atributes

	/**
	 * 
	 */
	public Enterprise() {
	}

	public Enterprise(String enterpriseName, String acronim, Date fundation, Float incomes, int actions,
			Float actionsPrice) {
		super();
		this.enterpriseName = enterpriseName;
		this.acronim = acronim;
		this.fundation = fundation;
		this.incomes = incomes;
		this.actions = actions;
		this.actionsPrice = actionsPrice;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@OneToMany(mappedBy = "enterprise")
	public Set<AnnualBenefits> getAnnualBenefits() {
		return annualBenefits;
	}

	public void setAnnualBenefits(Set<AnnualBenefits> annualBenefits) {
		this.annualBenefits = annualBenefits;
	}

	public int getActions() {
		return actions;
	}

	public void setActions(int actions) {
		this.actions = actions;
	}

	public Float getActionsPrice() {
		return actionsPrice;
	}

	public void setActionsPrice(Float actionsPrice) {
		this.actionsPrice = actionsPrice;
	}
	
	@Transient
	public Optional<AnnualBenefits> getAnnualBenefits(Long id) {
		return annualBenefits.stream().filter(annualBenefits -> annualBenefits.getEnterprise().getId().equals(id)).findFirst();
	}

	public void addAnnualBenefits(AnnualBenefits annualBenefit) {
		
		annualBenefits.add(annualBenefit);
		annualBenefit.setEnterprise(this);
		
	}

	@Override
	public String toString() {
		return "Enterprise [id=" + id + ", enterpriseName=" + enterpriseName + ", acronim=" + acronim + ", fundation="
				+ fundation + ", incomes=" + incomes + ", actions=" + actions + ", actionsPrice=" + actionsPrice
				+ ", annualBenefits=" + annualBenefits + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(acronim, actions, actionsPrice, annualBenefits, enterpriseName, fundation, id, incomes);
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
		return Objects.equals(acronim, other.acronim) && actions == other.actions
				&& Objects.equals(actionsPrice, other.actionsPrice)
				&& Objects.equals(annualBenefits, other.annualBenefits)
				&& Objects.equals(enterpriseName, other.enterpriseName) && Objects.equals(fundation, other.fundation)
				&& Objects.equals(id, other.id) && Objects.equals(incomes, other.incomes);
	}

}