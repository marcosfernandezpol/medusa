package es.udc.fi.dc.fd.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.dc.fd.model.common.exceptions.DuplicateInstanceException;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.entities.EnterpriseDao;
import es.udc.fi.dc.fd.model.entities.User;
import es.udc.fi.dc.fd.model.entities.User.RoleType;
import es.udc.fi.dc.fd.model.entities.UserDao;
import es.udc.fi.dc.fd.model.services.exceptions.PermissionException;


@Service
@Transactional
public class StockMarketServiceImpl implements StockMarketService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired 
	private EnterpriseDao enterpriseDao;
	

	@Override
	public Enterprise createEnterprise(Long userId, Enterprise enterprise) 
			throws DuplicateInstanceException, PermissionException {
		
		Optional<User> userOp = null;
		User user = null;
		
		if (enterpriseDao.existsByEnterpriseName(enterprise.getEnterpriseName())) {
			throw new DuplicateInstanceException("project.entities.enterprise",
					enterprise.getEnterpriseName());
		}
		
		userOp = userDao.findById(userId);
		if (userOp.isPresent()) { //Aqui habría que añadir algo para cuando el user no exista
			user = userOp.get();
			
			if (user.getRole() == RoleType.ADMIN) {
				
				enterpriseDao.save(enterprise);
				
			} else {
				throw new PermissionException();		
			}
		}

		return enterprise;
	}

}
