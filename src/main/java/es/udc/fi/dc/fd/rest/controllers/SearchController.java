package es.udc.fi.dc.fd.rest.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import es.udc.fi.dc.fd.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.dc.fd.model.entities.Actions;
import es.udc.fi.dc.fd.model.services.SearchService;
import es.udc.fi.dc.fd.rest.dtos.*;
/**
 * The Class SearchController.
 */
@RestController
@RequestMapping("/search")

public class SearchController {

	@Autowired
	private SearchService searchService;

	@GetMapping("/enterprises")
	public List<EnterpriseSummaryDto> findAllEnterpirses() {
		return EnterpriseSummaryConversor.toEnterprisesDtos(searchService.findAllEnterprises());
	}

	@GetMapping("/orders")
	public List<OrderLineDto> findOrders(@RequestAttribute Long userId, @RequestParam Boolean option,
			@RequestParam Boolean avaliable) {
		 return OrderLineConversor.toOrderLineDtos(searchService.findOrders(userId, option, avaliable));

	}

	@GetMapping("/enterprise/{id}")
	public EnterpriseDto find(@PathVariable Long id) throws InstanceNotFoundException {
		return EnterpriseConversor.toEnterpriseDto(searchService.findEnterprise(id));
	}
	
	@GetMapping("/enterprise/{id}/historic")
	public List<ActionPriceHistoricDto> findHistoricPrice(@PathVariable Long id, @RequestParam int numberOfDays) throws InstanceNotFoundException {
		return ActionPriceHistoricConversor.toActionPriceHistoricsDto(searchService.findHistorics(id,numberOfDays));
	}
	
	@GetMapping("/user/actions")
	public List<Actions> findUserActions(@RequestAttribute Long userId) throws InstanceNotFoundException {
		return searchService.findUserActions(userId);
	}

}
