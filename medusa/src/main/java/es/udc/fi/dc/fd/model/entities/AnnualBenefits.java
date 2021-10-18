package es.udc.fi.dc.fd.model.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The Class AnnualBenefits.
 */
@Entity
public class AnnualBenefits {

	private Long id;

	private Enterprise enterprise;

	private int year;

	private Float benefits;

	public AnnualBenefits() {
	}

	public AnnualBenefits(Enterprise enterprise, int year, Float benefits) {
		super();
		this.enterprise = enterprise;
		this.year = year;
		this.benefits = benefits;
	}

	public AnnualBenefits(int year, Float benefits) {
		super();
		this.year = year;
		this.benefits = benefits;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "enterpriseId")
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
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
		AnnualBenefits other = (AnnualBenefits) obj;
		return Objects.equals(benefits, other.benefits) && Objects.equals(enterprise, other.enterprise)
				&& id == other.id && year == other.year;
	}

}
