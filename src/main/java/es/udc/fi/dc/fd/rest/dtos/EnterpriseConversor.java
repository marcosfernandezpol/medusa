package es.udc.fi.dc.fd.rest.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.udc.fi.dc.fd.model.entities.AnnualBenefits;
import es.udc.fi.dc.fd.model.entities.Enterprise;

public class EnterpriseConversor {

	private EnterpriseConversor() {
	}

	public final static EnterpriseDto toEnterpriseDto(Enterprise t) {
		List<AnnualBenefitsDto> annualBenefit = new ArrayList<AnnualBenefitsDto>();

		for (AnnualBenefits benefit : t.getAnnualBenefits()) {
			annualBenefit.add(AnnualBenefitsConversor.toAnnualBenefits(benefit));
		}

		return new EnterpriseDto(t.getId(), t.getEnterpriseName(), t.getAcronim(), t.getFundation(), t.getIncomes(),
				t.getStock(), t.getStockPrice(), annualBenefit, t.isAvaliable());
	}

	public final static List<EnterpriseDto> toEnterprisesDtos(List<Enterprise> list) {
		return list.stream().map(t -> toEnterpriseDto(t)).collect(Collectors.toList());
	}

	/*
	 * To user.
	 *
	 * @param enterpriseDto the Enterprise dto
	 * 
	 * @return the enterprise
	 */
	public static final Enterprise toEnterprise(EnterpriseDto enterpriseDto) {

		return new Enterprise(enterpriseDto.getEnterpriseName(), enterpriseDto.getAcronim(),
				enterpriseDto.getFundation(), enterpriseDto.getIncomes(), enterpriseDto.getActions(),
				enterpriseDto.getActionsPrice());

	}

}