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
	
	private Long creatorId;

	private String enterpriseName;

	private String acronim;

	private Date fundation;

	private Float incomes;

	private int actions;

	private Float actionsPrice;

	private Set<AnnualBenefits> annualBenefits = new HashSet<>();

	private boolean avaliable;

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
		this.avaliable = true;
	}
	
	

	public Enterprise(Long id, Long creatorId, String enterpriseName, String acronim, Date fundation, Float incomes,
			int actions, Float actionsPrice, Set<AnnualBenefits> annualBenefits, boolean avaliable) {
		super();
		this.id = id;
		this.creatorId = creatorId;
		this.enterpriseName = enterpriseName;
		this.acronim = acronim;
		this.fundation = fundation;
		this.incomes = incomes;
		this.actions = actions;
		this.actionsPrice = actionsPrice;
		this.annualBenefits = annualBenefits;
		this.avaliable = avaliable;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
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

	public int getStock() {
		return actions;
	}

	public void setStock(int actions) {
		this.actions = actions;
	}

	public Float getStockPrice() {
		return actionsPrice;
	}

	public void setStockPrice(Float actionsPrice) {
		this.actionsPrice = actionsPrice;
	}

	public boolean isAvaliable() {
		return avaliable;
	}

	public void setAvaliable(boolean availiable) {
		this.avaliable = availiable;
	}

	@Transient
	public Optional<AnnualBenefits> getAnnualBenefits(Long id) {
		return annualBenefits.stream().filter(annualBenefits -> annualBenefits.getEnterprise().getId().equals(id))
				.findFirst();
	}

	public void addAnnualBenefits(AnnualBenefits annualBenefit) {

		annualBenefits.add(annualBenefit);
		annualBenefit.setEnterprise(this);

	}

	@Override
	public int hashCode() {
		return Objects.hash(acronim, actions, actionsPrice, annualBenefits, avaliable, creatorId, enterpriseName,
				fundation, id, incomes);
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
				&& Objects.equals(annualBenefits, other.annualBenefits) && avaliable == other.avaliable
				&& Objects.equals(creatorId, other.creatorId) && Objects.equals(enterpriseName, other.enterpriseName)
				&& Objects.equals(fundation, other.fundation) && Objects.equals(id, other.id)
				&& Objects.equals(incomes, other.incomes);
	}

	@Override
	public String toString() {
		return "Enterprise [id=" + id + ", creatorId=" + creatorId + ", enterpriseName=" + enterpriseName + ", acronim="
				+ acronim + ", fundation=" + fundation + ", incomes=" + incomes + ", actions=" + actions
				+ ", actionsPrice=" + actionsPrice + ", annualBenefits=" + annualBenefits + ", avaliable=" + avaliable
				+ "]";
	}

	

}