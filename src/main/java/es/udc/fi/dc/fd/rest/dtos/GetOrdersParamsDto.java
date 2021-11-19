package es.udc.fi.dc.fd.rest.dtos;

public class GetOrdersParamsDto {

	private Long userId;

	private Boolean option;

	private Boolean avaliable;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getOption() {
		return option;
	}

	public void setOption(Boolean option) {
		this.option = option;
	}

	public Boolean getAvaliable() {
		return avaliable;
	}

	public void setAvaliable(Boolean avaliable) {
		this.avaliable = avaliable;
	}

}
