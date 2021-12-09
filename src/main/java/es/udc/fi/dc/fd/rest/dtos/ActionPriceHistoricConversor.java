package es.udc.fi.dc.fd.rest.dtos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import es.udc.fi.dc.fd.model.entities.*;
import es.udc.fi.dc.fd.model.services.SearchService;

public class ActionPriceHistoricConversor {

	@Autowired
	SearchService searchService;

	private ActionPriceHistoricConversor() {
	}

	public static final  ActionPriceHistoricDto toActionPriceHistoricDto(ActionPriceHistoric t) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = t.getDate().format(formatter);
		
		return new ActionPriceHistoricDto(formatDateTime, t.getPrice());
	}
	
	public static final ActionPriceHistoric toActionPriceHistoric(ActionPriceHistoricDto t) {
		String str = t.getX();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		
		return new ActionPriceHistoric(dateTime, t.getY());
	}

	public static final List<ActionPriceHistoricDto> toActionPriceHistoricsDto(List<ActionPriceHistoric> list) {
		return list.stream().map(t -> toActionPriceHistoricDto(t)).collect(Collectors.toList());
	}

	public static final Enterprise toEnterprise(EnterpriseDto enterpriseDto) {

		return new Enterprise(enterpriseDto.getEnterpriseName(), enterpriseDto.getAcronim(),
				enterpriseDto.getFundation(), enterpriseDto.getIncomes(), enterpriseDto.getActions(),
				enterpriseDto.getActionsPrice());

	}

}