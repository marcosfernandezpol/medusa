package es.udc.fi.dc.fd.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.udc.fi.dc.fd.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.dc.fd.model.services.SearchService;
import es.udc.fi.dc.fd.rest.dtos.EnterpriseConversor;
import es.udc.fi.dc.fd.rest.dtos.EnterpriseDto;
import es.udc.fi.dc.fd.rest.dtos.OrderLineConversor;
import es.udc.fi.dc.fd.rest.dtos.OrderLineDto;
import es.udc.fi.dc.fd.rest.dtos.EnterpriseSummaryConversor;
import es.udc.fi.dc.fd.rest.dtos.EnterpriseSummaryDto;

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
		
		List<OrderLineDto> returned = new ArrayList<OrderLineDto>();
		returned = OrderLineConversor.toOrderLineDtos(searchService.findOrders(userId, option, avaliable));
		return returned;
	}

	@GetMapping("/enterprise/{id}")
	public EnterpriseDto find(@PathVariable Long id) throws InstanceNotFoundException {
		return EnterpriseConversor.toEnterpriseDto(searchService.findEnterprise(id));
	}

}
