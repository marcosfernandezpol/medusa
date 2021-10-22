package es.udc.fi.dc.fd.rest.dtos;

import org.springframework.beans.factory.annotation.Autowired;

import es.udc.fi.dc.fd.model.entities.AnnualBenefits;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.services.SearchService;

public class AnnualBenefitsConversor {

	@Autowired
	SearchService searchService;

	private AnnualBenefitsConversor() {
	}

	public final static AnnualBenefitsDto toAnnualBenefitsDto(AnnualBenefits t) {
		return new AnnualBenefitsDto(t.getYear(), t.getBenefits());
	}
	
	public final static AnnualBenefits toAnnualBenefits(AnnualBenefitsDto t) {
		return new AnnualBenefits(t.getYear(), t.getBenefits());
	}

	public static final AnnualBenefitsDto toAnnualBenefits(AnnualBenefits annualBenefits) {
		return new AnnualBenefitsDto(annualBenefits.getYear(), annualBenefits.getBenefits());
	}

	public static final Enterprise toEnterprise(EnterpriseDto enterpriseDto) {

		return new Enterprise(enterpriseDto.getEnterpriseName(), enterpriseDto.getAcronim(),
				enterpriseDto.getFundation(), enterpriseDto.getIncomes(), enterpriseDto.getActions(),
				enterpriseDto.getActionsPrice());

	}

}
