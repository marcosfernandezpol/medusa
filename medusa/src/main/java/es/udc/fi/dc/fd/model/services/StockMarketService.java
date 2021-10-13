package es.udc.fi.dc.fd.model.services;

import es.udc.fi.dc.fd.model.common.exceptions.*;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.services.exceptions.PermissionException;

public interface StockMarketService {
	
	public Enterprise createEnterprise(Long userId, Enterprise enterprise) 
			throws DuplicateInstanceException, PermissionException, NumberException;
	
	public void transfer(Long userId, Float money, String Operation) 
			throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException;

}
