package es.udc.fi.dc.fd.model.services;

import java.util.List;

import es.udc.fi.dc.fd.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.dc.fd.model.entities.Enterprise;

public interface SearchService {

	public List<Enterprise> findAllEnterprises();

	public Enterprise findEnterprise(Long id) throws InstanceNotFoundException;

}