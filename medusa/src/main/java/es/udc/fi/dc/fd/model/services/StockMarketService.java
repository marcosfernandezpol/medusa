package es.udc.fi.dc.fd.model.services;

import es.udc.fi.dc.fd.model.common.exceptions.DuplicateInstanceException;
import es.udc.fi.dc.fd.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.dc.fd.model.common.exceptions.InvalidOperationException;
import es.udc.fi.dc.fd.model.common.exceptions.NotEnoughBalanceException;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.services.exceptions.PermissionException;

public interface StockMarketService {
	
	public Enterprise createEnterprise(Long userId, Enterprise enterprise) 
			throws DuplicateInstanceException, PermissionException;
	
	public void transfer(Long userId, Float money, String Operation) 
			throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException;

}
