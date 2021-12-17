package es.udc.fi.dc.fd.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;
import es.udc.fi.dc.fd.model.entities.Enterprise;

public class EnterpriseSummaryConversor {

	private EnterpriseSummaryConversor() {
	}

	public static final EnterpriseSummaryDto toEnterpriseSummaryDto(Enterprise t) {
		return new EnterpriseSummaryDto(t.getId(), t.getEnterpriseName(), t.getCreatorId() ,t.getAcronim(), t.getFundation(), t.getIncomes(),
				t.getStock(), t.getStockPrice(), t.isAvaliable());
	}

	public static final List<EnterpriseSummaryDto> toEnterprisesDtos(List<Enterprise> list) {
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

		return new Enterprise( enterpriseSummaryDto.getCreatorId() ,enterpriseSummaryDto.getEnterpriseName(),enterpriseSummaryDto.getAcronim(),
				enterpriseSummaryDto.getFundation(), enterpriseSummaryDto.getIncomes(), enterpriseSummaryDto.getActions(),
				enterpriseSummaryDto.getActionsPrice());

	}

}