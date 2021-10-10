package es.udc.fi.dc.fd.rest.dtos;

import javax.validation.constraints.NotNull;

public class TransferParamsDto {

	
	 private float money;
	 private String operation;

	   	@NotNull
	    public float getMoney() {return money;}
	    public void setMoney(float money) { this.money = money; }
		
	    @NotNull
		public String getOperation() { return operation;}

		public void setOperation(String operation) { this.operation = operation; }

}
