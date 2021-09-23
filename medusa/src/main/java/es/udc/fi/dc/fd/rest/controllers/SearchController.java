package es.udc.fi.dc.fd.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.fi.dc.fd.model.services.SearchService;
import es.udc.fi.dc.fd.rest.dtos.EnterpriseConversor;
import es.udc.fi.dc.fd.rest.dtos.EnterpriseDto;

/**
 * The Class SearchController.
 */
@RestController
@RequestMapping("/search")

public class SearchController {

	@Autowired
	private SearchService searchService;

	@GetMapping("/enterprises")
	public List<EnterpriseDto> findAllEnterpirses() {
		return  EnterpriseConversor.toEnterprisesDtos(searchService.findAllEnterprises());
	}

}
