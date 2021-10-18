package es.udc.fi.dc.fd.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.fi.dc.fd.model.entities.Enterprise;

public class EnterpriseSummaryConversor {

	private EnterpriseSummaryConversor() {
	}

	public final static EnterpriseSummaryDto toEnterpriseSummaryDto(Enterprise t) {
		return new EnterpriseSummaryDto(t.getId(), t.getEnterpriseName(), t.getAcronim(), t.getFundation(), t.getIncomes(),
				t.getActions(), t.getActionsPrice());
	}

	public final static List<EnterpriseSummaryDto> toEnterprisesDtos(List<Enterprise> list) {
		return list.stream().map(t -> toEnterpriseSummaryDto(t)).collect(Collectors.toList());
	}

	/*
	 * To user.
	 *
	 * @param enterpriseSummaryDto the Enterprise dto
	 * 
	 * @return the enterprise
	 */
	public static final Enterprise toEnterpriseSummary(EnterpriseSummaryDto enterpriseSummaryDto) {

		return new Enterprise(enterpriseSummaryDto.getEnterpriseName(), enterpriseSummaryDto.getAcronim(),
				enterpriseSummaryDto.getFundation(), enterpriseSummaryDto.getIncomes(), enterpriseSummaryDto.getActions(),
				enterpriseSummaryDto.getActionsPrice());

	}

}