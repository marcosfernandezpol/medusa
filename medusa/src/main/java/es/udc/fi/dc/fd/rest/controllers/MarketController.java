package es.udc.fi.dc.fd.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.fi.dc.fd.model.common.exceptions.DuplicateInstanceException;
import es.udc.fi.dc.fd.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.dc.fd.model.common.exceptions.InvalidOperationException;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.services.StockMarketService;
import es.udc.fi.dc.fd.model.services.exceptions.PermissionException;
import es.udc.fi.dc.fd.rest.dtos.EnterpriseDto;
import es.udc.fi.dc.fd.rest.dtos.TransferParamsDto;
import es.udc.fi.dc.fd.rest.dtos.EnterpriseConversor;

@RestController
@RequestMapping("/market")
public class MarketController {


	/** The user service. */
	@Autowired
	private StockMarketService marketService;

	
	/**
	 * Create an enterprise.
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
	
	/**
	 * Make a money transfer.
	 */
    @PostMapping("/transfer")
    public void transfer (
    	@RequestAttribute Long userId,
    	@Validated @RequestBody TransferParamsDto params) 
    throws InvalidOperationException, InstanceNotFoundException{
    	
    	marketService.transfer(userId, params.getMoney());
    }
	

}
