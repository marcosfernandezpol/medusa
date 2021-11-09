package es.udc.fi.dc.fd.rest.dtos;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import es.udc.fi.dc.fd.model.entities.ActionPriceHistoric;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.services.SearchService;

public class ActionPriceHistoricConversor {

	@Autowired
	SearchService searchService;

	private ActionPriceHistoricConversor() {
	}

	public final static ActionPriceHistoricDto toActionPriceHistoricDto(ActionPriceHistoric t) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = t.getDate().format(formatter);
		
		return new ActionPriceHistoricDto(formatDateTime, t.getPrice());
	}
	
	public final static ActionPriceHistoric toActionPriceHistoric(ActionPriceHistoricDto t) {
		String str = t.getX();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		
		return new ActionPriceHistoric(dateTime, t.getY());
	}

	public static final List<ActionPriceHistoricDto> toActionPriceHistoricsDto(List<ActionPriceHistoric> list) {
		List<ActionPriceHistoricDto> result = list.stream().map(t -> toActionPriceHistoricDto(t)).collect(Collectors.toList());
		return result;
	}

	public static final Enterprise toEnterprise(EnterpriseDto enterpriseDto) {

		return new Enterprise(enterpriseDto.getEnterpriseName(), enterpriseDto.getAcronim(),
				enterpriseDto.getFundation(), enterpriseDto.getIncomes(), enterpriseDto.getActions(),
				enterpriseDto.getActionsPrice());

	}

}