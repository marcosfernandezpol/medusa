package es.udc.fi.dc.fd.model.entities;

import java.util.Objects;



public class Actions {

	private int number;

	private Enterprise enterprise;

	
	
	public Actions() {
		super();
	}

	public Actions(int number, Enterprise enterprise) {
		super();
		this.number = number;
		this.enterprise = enterprise;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	@Override
	public int hashCode() {
		return Objects.hash(enterprise, number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actions other = (Actions) obj;
		return Objects.equals(enterprise, other.enterprise) && number == other.number;
	}

	@Override
	public String toString() {
		return "Actions [number=" + number + ", enterprise=" + enterprise + "]";
	}
	
	

	

}