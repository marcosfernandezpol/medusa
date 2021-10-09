package es.udc.fi.dc.fd.model.entities;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	private Float annualBenefits;
	
	private int actions;

<<<<<<< Updated upstream
	private int stock;

	private Float stockPrice;

=======
	private Float actionsPrice;
	
>>>>>>> Stashed changes
	// End atributes

	public Enterprise(Long id, String enterpriseName, String acronim, Date fundation, Float incomes,
<<<<<<< Updated upstream
			Float annualBenefits, int stock, Float stockPrice) {
=======
			Float annualBenefits, int actions, Float actionsPrice) {
>>>>>>> Stashed changes
		super();
		this.enterpriseName = enterpriseName;
		this.acronim = acronim;
		this.fundation = fundation;
		this.incomes = incomes;
		this.annualBenefits = annualBenefits;
<<<<<<< Updated upstream
		this.stock = stock;
		this.stockPrice = stockPrice;
=======
		this.actions = actions;
		this.actionsPrice = actionsPrice; 
>>>>>>> Stashed changes
	}

	/**
	 * 
	 */
	public Enterprise() {
	}

<<<<<<< Updated upstream
	public Enterprise(String enterpriseName, String acronim, Date fundation, Float incomes, Float annualBenefits,
			int stock, Float stockPrice) {
=======
	public Enterprise(String enterpriseName, String acronim, Date fundation, Float incomes, Float annualBenefits, int actions, Float actionsPrice) {
>>>>>>> Stashed changes
		super();
		this.enterpriseName = enterpriseName;
		this.acronim = acronim;
		this.fundation = fundation;
		this.incomes = incomes;
		this.annualBenefits = annualBenefits;
<<<<<<< Updated upstream
		this.stock = stock;
		this.stockPrice = stockPrice;
=======
		this.actions = actions;
		this.actionsPrice = actionsPrice; 
>>>>>>> Stashed changes
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

	public Float getAnnualBenefits() {
		return annualBenefits;
	}

	public void setAnnualBenefits(Float annualBenefits) {
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

<<<<<<< Updated upstream
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Float getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(Float stockPrice) {
		this.stockPrice = stockPrice;
	}

=======
	public void setActionsPrice(Float actionsPrice) {
		this.actionsPrice = actionsPrice;
	}
	
>>>>>>> Stashed changes
	@Override
	public String toString() {
		return "Enterprise [id=" + id + ", enterpriseName=" + enterpriseName + ", acronim=" + acronim + ", fundation="
				+ fundation + ", incomes=" + incomes + ", annualBenefits=" + annualBenefits + ", actions=" + actions
				+ ", actionsPrice=" + actionsPrice + "]";
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