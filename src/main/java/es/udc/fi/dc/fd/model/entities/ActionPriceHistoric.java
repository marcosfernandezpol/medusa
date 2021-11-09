package es.udc.fi.dc.fd.model.entities;

import java.util.Date;
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
public class ActionPriceHistoric {

	private Long id;

	private Enterprise enterprise;

	private Date date;

	private Float price;

	public ActionPriceHistoric() {
	}
	
	

	

	public ActionPriceHistoric(Date date, Float price) {
		super();
		this.date = date;
		this.price = price;
	}





	public ActionPriceHistoric(Enterprise enterprise, Date date, Float price) {
		super();
		this.enterprise = enterprise;
		this.date = date;
		this.price = price;
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



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public Float getPrice() {
		return price;
	}



	public void setPrice(Float price) {
		this.price = price;
	}



	@Override
	public int hashCode() {
		return Objects.hash(date, enterprise, id, price);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActionPriceHistoric other = (ActionPriceHistoric) obj;
		return Objects.equals(date, other.date) && Objects.equals(enterprise, other.enterprise)
				&& Objects.equals(id, other.id) && Objects.equals(price, other.price);
	}



	

}
