package es.udc.fi.dc.fd.rest.dtos;

import java.util.List;

public class AnnualBenefitsListDto {

	List<AnnualBenefitsParamsDto> benefitsList;

	public AnnualBenefitsListDto() {
		super();
	}

	public AnnualBenefitsListDto(List<AnnualBenefitsParamsDto> benefitsList) {
		super();
		this.benefitsList = benefitsList;
	}

	public List<AnnualBenefitsParamsDto> getBenefitsList() {
		return benefitsList;
	}

	public void setBenefitsList(List<AnnualBenefitsParamsDto> benefitsList) {
		this.benefitsList = benefitsList;
	}

}
