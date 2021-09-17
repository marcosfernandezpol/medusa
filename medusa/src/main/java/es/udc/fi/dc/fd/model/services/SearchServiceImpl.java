package es.udc.fi.dc.fd.model.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import antlr.collections.List;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.entities.EnterpriseDao;


@Service
@Transactional(readOnly=true)
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private EnterpriseDao enterpriseDao;

	@Override
	public List findAllEnterprises() {
		
		List enterpriseList = (List) new ArrayList<Enterprise>();
		
		Iterable<Enterprise> enterprises = enterpriseDao.findAll(
				Sort.by(Sort.Direction.ASC,"enterpriseName"));
		enterprises.forEach(enterpriseList::add);
		
		return enterpriseList;
	}
	
}
