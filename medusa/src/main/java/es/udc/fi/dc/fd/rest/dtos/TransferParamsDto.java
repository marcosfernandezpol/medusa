package es.udc.fi.dc.fd.rest.dtos;

import javax.validation.constraints.NotNull;

public class TransferParamsDto {

	 private float money;
	 
	 private String operation;

	    @NotNull
	    public float getMoney() {return money;}
	    public void setMoney(float money) { this.money = money; }
		
	    
	    
	    /**
		 * @return the operations
		 */
		public String getOperation() {
			return operation;
		}
		/**
		 * @param operations the operations to set
		 */
		public void setOperation(String operation) {
			this.operation = operation;
		}
	    
	 
	    
	    

}
