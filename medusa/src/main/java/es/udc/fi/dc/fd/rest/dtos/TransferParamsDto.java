package es.udc.fi.dc.fd.rest.dtos;

import javax.validation.constraints.NotNull;

public class TransferParamsDto {

	 private float money;

	    @NotNull
	    public float getMoney() {return money;}
	    public void setMoney(float money) { this.money = money; }

}
