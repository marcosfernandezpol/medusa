package es.udc.fi.dc.fd.rest.controllers;

import static es.udc.fi.dc.fd.rest.dtos.UserConversor.toAuthenticatedUserDto;
import static es.udc.fi.dc.fd.rest.dtos.UserConversor.toUser;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.udc.fi.dc.fd.model.common.exceptions.DuplicateInstanceException;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.entities.User;
import es.udc.fi.dc.fd.model.services.StockMarketService;
import es.udc.fi.dc.fd.model.services.exceptions.PermissionException;
import es.udc.fi.dc.fd.rest.common.JwtGenerator;
import es.udc.fi.dc.fd.rest.dtos.AuthenticatedUserDto;
import es.udc.fi.dc.fd.rest.dtos.EnterpriseDto;
import es.udc.fi.dc.fd.rest.dtos.EnterpriseConversor;

@RestController
@RequestMapping("/market")
public class MarketController {

	/** The message source. */
	@Autowired
	private MessageSource messageSource;

	/** The jwt generator. */
	@Autowired
	private JwtGenerator jwtGenerator;

	/** The user service. */
	@Autowired
	private StockMarketService marketService;

	/**
	 * Sign up an enterprise.
	 *
	 * @param enterprise the enterprise dto
	 * @return the response entity
	 * @throws DuplicateInstanceException the duplicate instance exception
	 * @throws PermissionException 
	 */
	@PostMapping("/create_enterprise")
	public EnterpriseDto createEnterprise(@RequestAttribute Long userId,
			@Validated @RequestBody EnterpriseDto enterpriseDto)
			throws DuplicateInstanceException, PermissionException{

		Enterprise enterprise = EnterpriseConversor.toEnterprise(enterpriseDto);
		marketService.createEnterprise(userId, enterprise);
		return null;	
	}

}
