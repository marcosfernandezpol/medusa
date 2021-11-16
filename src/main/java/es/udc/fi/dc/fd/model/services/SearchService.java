package es.udc.fi.dc.fd.model.services;

import java.util.List;

import es.udc.fi.dc.fd.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.dc.fd.model.entities.ActionPriceHistoric;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.entities.OrderLine;

public interface SearchService {

	public List<Enterprise> findAllEnterprises();

	public List<OrderLine> findOrders(Long userId, Boolean option, Boolean avaliable);

	public Enterprise findEnterprise(Long id) throws InstanceNotFoundException;
	
	public List<ActionPriceHistoric> findHistorics(Long id, int numberOfDays) throws InstanceNotFoundException;

}
