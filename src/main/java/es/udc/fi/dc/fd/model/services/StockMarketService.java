package es.udc.fi.dc.fd.model.services;

import es.udc.fi.dc.fd.model.common.exceptions.*;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.entities.User;
import es.udc.fi.dc.fd.model.entities.OrderLine.*;
import es.udc.fi.dc.fd.model.services.exceptions.*;
import es.udc.fi.dc.fd.rest.dtos.AnnualBenefitsListDto;
import java.time.LocalDateTime;

public interface StockMarketService {

	public Enterprise createEnterprise(Long userId, Enterprise enterprise)
			throws DuplicateInstanceException, PermissionException, NumberException;

	public Enterprise createAnnualBenefits(Long userId, Long enterpriseId, AnnualBenefitsListDto benefitsList)
			throws DuplicateInstanceException, PermissionException, InstanceNotFoundException, InvalidArgumentException;

	public float transfer(Long userId, Float money, String operation)
			throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException;

	public long order(Long owner, OrderType orderType, OrderLineType orderLineType, Float price, int number, Long enterpriseId, LocalDateTime deadline)
			throws NotEnoughBalanceException, NotOwnedException, NotAvaliableException;
	
	public void deleteOrder(Long owner, Long orderId, Boolean avaliable)
			throws NotOwnedException, InstanceNotFoundException, NotAvaliableException;

	public Enterprise modifyAvaliableEnterprise(Long creator, Long enterpriseId, Boolean avaliable)
			throws NotCreatorException, InstanceNotFoundException;
	
	public int searchUserActionsNumber(User user, Enterprise enterprise, Boolean sellOnlyNotAbaliable);
}