package es.udc.fi.dc.fd.rest.dtos;

import java.util.Objects;

import javax.validation.constraints.NotNull;

public class DeleteParamsDto {
	
	private Long orderId;
	
	private Boolean avaliable;


	@NotNull
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Boolean getAvaliable() {
		return avaliable;
	}

	public void setAvaliable(Boolean avaliable) {
		this.avaliable = avaliable;
	}



}
