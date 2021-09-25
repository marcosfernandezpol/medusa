package es.udc.fi.dc.fd.model.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.dc.fd.model.common.exceptions.DuplicateInstanceException;
import es.udc.fi.dc.fd.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.dc.fd.model.common.exceptions.InvalidOperationException;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.entities.EnterpriseDao;
import es.udc.fi.dc.fd.model.entities.User;
import es.udc.fi.dc.fd.model.entities.User.RoleType;
import es.udc.fi.dc.fd.model.entities.UserDao;
import es.udc.fi.dc.fd.model.services.exceptions.PermissionException;
import es.udc.fi.dc.fd.model.services.exceptions.*;

@Service
@Transactional
public class StockMarketServiceImpl implements StockMarketService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired 
	private EnterpriseDao enterpriseDao;
	
	@Autowired
	private PermissionChecker permissionChecker;
	

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
	
	
	@Override
	public void transfer(Long userId, Float money) 
			throws InvalidOperationException, InstanceNotFoundException {
		
		//Comprobamos la existencia del usuario
		permissionChecker.checkUser(userId);
		
		
		//Recuperamos el usuario ya existente
		Optional<User> existUser = userDao.findById(userId);
		User user = existUser.get();
		
		if (money > 0) { //Caso de ingresar
			user.setBalance(money + user.getBalance());
			userDao.save(user);
		} else if (money < 0) { //Caso de retirar
			if (user.getBalance() > money) {
				user.setBalance(user.getBalance() - money);
				userDao.save(user);
			} else {
				throw new InvalidOperationException(); //Cantidad para retirar insuficiente
			}
		} else {
			throw new InvalidOperationException();	//Caso de 0 (inválido)
		}
		
		
	}
}


