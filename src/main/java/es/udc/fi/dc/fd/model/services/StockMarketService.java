package es.udc.fi.dc.fd.model.services;

import es.udc.fi.dc.fd.model.common.exceptions.DuplicateInstanceException;
import es.udc.fi.dc.fd.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.dc.fd.model.common.exceptions.InvalidOperationException;
import es.udc.fi.dc.fd.model.common.exceptions.NotAvaliableException;
import es.udc.fi.dc.fd.model.common.exceptions.NotCreatorException;
import es.udc.fi.dc.fd.model.common.exceptions.NotEnoughBalanceException;
import es.udc.fi.dc.fd.model.common.exceptions.NotOwnedException;
import es.udc.fi.dc.fd.model.common.exceptions.NumberException;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.entities.OrderLine.OrderType;
import es.udc.fi.dc.fd.model.services.exceptions.InvalidArgumentException;
import es.udc.fi.dc.fd.model.services.exceptions.PermissionException;
import es.udc.fi.dc.fd.rest.dtos.AnnualBenefitsListDto;

public interface StockMarketService {

	public Enterprise createEnterprise(Long userId, Enterprise enterprise)
			throws DuplicateInstanceException, PermissionException, NumberException;
			
	public Enterprise createAnnualBenefits(Long userId, Long enterpriseId, AnnualBenefitsListDto benefitsList)
			throws DuplicateInstanceException, PermissionException, InstanceNotFoundException, InvalidArgumentException;

	public void transfer(Long userId, Float money, String Operation)
			throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException;

	public void order(Long owner, OrderType orderType, Float price, int number, Long enterpriseId)
			throws NotEnoughBalanceException, NotOwnedException;

	public void deleteOrder (Long owner, Long orderId, Boolean avaliable) throws NotOwnedException, 
	InstanceNotFoundException, NotAvaliableException;
	
	public Enterprise modifyAvaliableEnterprise (Long creator, Long enterpriseId, Boolean avaliable) throws NotCreatorException, 
	InstanceNotFoundException;
} 