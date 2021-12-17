package es.udc.fi.dc.fd.model.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
//import static Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.floatThat;
import static org.mockito.ArgumentMatchers.longThat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.fi.dc.fd.model.common.exceptions.DuplicateInstanceException;
import es.udc.fi.dc.fd.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.dc.fd.model.common.exceptions.InvalidOperationException;
import es.udc.fi.dc.fd.model.common.exceptions.NotAvaliableException;
import es.udc.fi.dc.fd.model.common.exceptions.NotCreatorException;
import es.udc.fi.dc.fd.model.common.exceptions.NotEnoughBalanceException;
import es.udc.fi.dc.fd.model.common.exceptions.NotOwnedException;
import es.udc.fi.dc.fd.model.common.exceptions.NumberException;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.entities.EnterpriseDao;
import es.udc.fi.dc.fd.model.entities.OrderLine;
import es.udc.fi.dc.fd.model.entities.OrderLine.OrderLineType;
import es.udc.fi.dc.fd.model.entities.OrderLine.OrderType;
import es.udc.fi.dc.fd.model.entities.OrderLineDao;
import es.udc.fi.dc.fd.model.entities.User;
import es.udc.fi.dc.fd.model.entities.User.RoleType;
import es.udc.fi.dc.fd.model.entities.UserDao;
import es.udc.fi.dc.fd.model.services.exceptions.InvalidArgumentException;
import es.udc.fi.dc.fd.model.services.exceptions.PermissionException;
import es.udc.fi.dc.fd.rest.dtos.AnnualBenefitsDto;
import es.udc.fi.dc.fd.rest.dtos.AnnualBenefitsListDto;
import es.udc.fi.dc.fd.rest.dtos.AnnualBenefitsParamsDto;

/**
 * The Class StockMarketServiceTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class StockMarketServiceTest {

	@Autowired
	private StockMarketService stockMarketService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private EnterpriseDao enterpriseDao;

	@Autowired
	private OrderLineDao orderLineDao;

	// Creamos un usuario de tipo Administrador
	private User createAdmin() {
		return new User("Pol", "fdezpol", "masmejor", "password", "fdezpol007elmasmejor@gmail.com", "Spain", "Santiago",
				RoleType.ADMIN, 1000F,User.UserType.STANDARD);

	}

	private User createSavedAdmin() {
		User admin = createAdmin();
		return userDao.save(admin);
	}

	// Creamos un usuario de tipo Cliente
	private User createClient() {
		return new User("MariaM", "Maria", "Martinez", "password", "mariamartinez@gmail.com", "Spain", "A Coruña",
				RoleType.CLIENT, 1200F,User.UserType.STANDARD);

	}

	private User createSavedClient() {
		User client = createClient();
		return userDao.save(client);
	}
	private User createNSavedClientWhitBalance(int n, float balance) {
		String name = "Maria";
		User user = new User(name + n, "Maria", "Martinez", "password", "mariamartinez@gmail.com", "Spain", "A Coruña",
				RoleType.CLIENT, balance,User.UserType.STANDARD);
		return userDao.save(user);
	}

	// Creamos un usuario de tipo Cliente sin dinero.
	private User createClientNoMoney() {
		return new User("MariaM", "Maria", "Martinez", "password", "mariamartinez@gmail.com", "Spain", "A Coruña",
				RoleType.CLIENT, 0F,User.UserType.STANDARD);

	}

	// Creamos una empresa
	private Enterprise createEnterprise() {
		return new Enterprise("MedusaEnterprises", "ME", Date.valueOf("1999-01-17"), Float.valueOf(1000), 28,
				Float.valueOf(18));
	}
	
	private AnnualBenefitsListDto createAnnualBenefits() {
		List <AnnualBenefitsParamsDto> annualBenefitsList = new ArrayList<AnnualBenefitsParamsDto>();
		
		AnnualBenefitsParamsDto AnnualBenefits2000 = new AnnualBenefitsParamsDto(2000, 1000f);
		AnnualBenefitsParamsDto AnnualBenefits2001 = new AnnualBenefitsParamsDto(2001, 9000f);
		
		annualBenefitsList.add(AnnualBenefits2000);
		annualBenefitsList.add(AnnualBenefits2001);
		AnnualBenefitsListDto annualBenefitsListDto = new AnnualBenefitsListDto(annualBenefitsList);
		return annualBenefitsListDto;
	}

	@Test
	public void testCreateEnterprise() throws DuplicateInstanceException, PermissionException, NumberException {

		User admin = null;
		admin = createAdmin();
		userDao.save(admin);
		Enterprise enterprise = createEnterprise();
		Enterprise resultEnterprise = null;

		userDao.save(admin);
		enterprise.setCreatorId(admin.getId());
		resultEnterprise = stockMarketService.createEnterprise(admin.getId(), enterprise);

		assertNotNull(resultEnterprise.getId());

	}

	@Test(expected = PermissionException.class)
	public void testCreateEnterpriseThrowsPermissionException()
			throws DuplicateInstanceException, PermissionException, NumberException {

		User client = createClient();
		userDao.save(client);
		Enterprise enterprise = createEnterprise();

		userDao.save(client);
		enterprise.setCreatorId(client.getId());
		stockMarketService.createEnterprise(client.getId(), enterprise);

	}

	@SuppressWarnings("deprecation")
	@Test(expected = PermissionException.class)
	public void testCreateEnterpriseFundationAfterNowThrowsPermissionException()
			throws DuplicateInstanceException, PermissionException, NumberException {

		User admin = createAdmin();
		userDao.save(admin);
		Enterprise enterprise = createEnterprise();

		userDao.save(admin);
		enterprise.setCreatorId(admin.getId());
		enterprise.setFundation(new Date(2040, 10, 10));
		stockMarketService.createEnterprise(admin.getId(), enterprise);

	}

	@Test(expected = DuplicateInstanceException.class)
	public void testCreateEnterpriseThrowsDuplicateInstanceException()
			throws DuplicateInstanceException, PermissionException, NumberException {

		User admin = createAdmin();
		userDao.save(admin);
		Enterprise enterprise = createEnterprise();

		userDao.save(admin);
		enterprise.setCreatorId(admin.getId());
		stockMarketService.createEnterprise(admin.getId(), enterprise);
		stockMarketService.createEnterprise(admin.getId(), enterprise);

	}

	@Test
	public void testDepositTransfer()
			throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException {

		User client = createClient();

		userDao.save(client);

		float aux = stockMarketService.transfer(client.getId(), Float.valueOf(500), "INCOME");

		assertTrue(aux == client.getBalance());
	}

	@Test
	public void testRetireTransfer()
			throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException {

		User client = createClient();

		userDao.save(client);

		float aux = stockMarketService.transfer(client.getId(), Float.valueOf(200), "WITHDRAW");
		assertTrue(client.getBalance() == aux);

	}

	@Test(expected = InvalidOperationException.class)
	public void InvalidOperatorException()
			throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException {

		User client = createClient();

		userDao.save(client);

		stockMarketService.transfer(client.getId(), Float.valueOf(200), "NULL");

	}

	@Test(expected = InvalidOperationException.class)
	public void testTransferThrowsInvalidOperatorExpcetion()
			throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException {

		User client = createClient();

		userDao.save(client);

		stockMarketService.transfer(client.getId(), Float.valueOf(-2), "WITHDRAW");

	}

	@Test(expected = NotEnoughBalanceException.class)
	public void testTransferThrowsNotEnoughBalanceException()
			throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException {

		User client = createClientNoMoney();

		userDao.save(client);

		stockMarketService.transfer(client.getId(), Float.valueOf(1000), "WITHDRAW");

	}

	@Test
	public void createOrders() throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException,
			DuplicateInstanceException, PermissionException, NumberException, NotOwnedException, NotAvaliableException {

		User client = createClient();
		Enterprise enterprise = createEnterprise();

		User savedClient = userDao.save(client);
		enterprise.setCreatorId(client.getId());
		Enterprise savedEnterprise = enterpriseDao.save(enterprise);

		stockMarketService.order(savedClient.getId(), OrderType.BUY, OrderLineType.LIMIT, Float.valueOf(10), 3,
				savedEnterprise.getId(), LocalDateTime.now().plusDays(1));
		stockMarketService.order(savedClient.getId(), OrderType.BUY, OrderLineType.LIMIT, Float.valueOf(10), 3,
				savedEnterprise.getId(), LocalDateTime.now().plusDays(1));
		stockMarketService.order(savedClient.getId(), OrderType.BUY, OrderLineType.LIMIT, Float.valueOf(10), 3,
				savedEnterprise.getId(), LocalDateTime.now().plusDays(1));
		Optional<List<OrderLine>> orderListOp = orderLineDao
				.findByOwnerAndOrderTypeAndAvaliableOrderByRequestDateDesc(savedClient, OrderType.BUY, true);

		List<OrderLine> orderList = null;
		if (orderListOp.isPresent())
			orderList = orderListOp.get();

		assertTrue(orderList.size() == 3);

	}

	@Test(expected = NotOwnedException.class)
	public void NotOwnedException() throws NotOwnedException, NotEnoughBalanceException, NotAvaliableException {

		User client = createClient();
		Enterprise enterprise = createEnterprise();

		User savedClient = userDao.save(client);
		enterprise.setCreatorId(client.getId());
		Enterprise savedEnterprise = enterpriseDao.save(enterprise);

		stockMarketService.order(savedClient.getId(), OrderType.SELL, OrderLineType.LIMIT, Float.valueOf(10), 3,
				savedEnterprise.getId(), LocalDateTime.now().plusDays(1));

	}

	@Test
	public void buyEnterpriseInitialActionsLimit()
			throws DuplicateInstanceException, PermissionException, NumberException, InstanceNotFoundException,
			InvalidOperationException, NotEnoughBalanceException, NotOwnedException, NotAvaliableException {

		User admin = createSavedAdmin();
		Enterprise enterprise = createEnterprise();
		enterprise.setCreatorId(admin.getId());
		enterprise.setStockPrice(10F);
		enterprise.setStock(10);

		stockMarketService.createEnterprise(admin.getId(), enterprise);

		User client = createSavedClient();
		stockMarketService.transfer(client.getId(), 1000F, "INCOME");
		stockMarketService.order(client.getId(), OrderType.BUY, OrderLineType.LIMIT, 12F, 12, enterprise.getId(),
				LocalDateTime.now().plusDays(10));
		assertEquals(10, stockMarketService.searchUserActionsNumber(client, enterprise, false));
		
	}
	
	@Test
	public void buyEnterpriseInitialActionsMarket()
			throws DuplicateInstanceException, PermissionException, NumberException, InstanceNotFoundException,
			InvalidOperationException, NotEnoughBalanceException, NotOwnedException, NotAvaliableException {

		User admin = createSavedAdmin();
		Enterprise enterprise = createEnterprise();
		enterprise.setCreatorId(admin.getId());
		enterprise.setStockPrice(10F);
		enterprise.setStock(10);

		stockMarketService.createEnterprise(admin.getId(), enterprise);

		User client = createSavedClient();
		stockMarketService.transfer(client.getId(), 1000F, "INCOME");
		stockMarketService.order(client.getId(), OrderType.BUY, OrderLineType.MARKET, 12F, 12, enterprise.getId(),
				LocalDateTime.now().plusDays(10));
		assertEquals(stockMarketService.searchUserActionsNumber(client, enterprise, false), 10);
		
	}
	
	@Test 
	public void buyEnterpriseInitialActionsMarketNotAvaliableEnterprise()
			throws DuplicateInstanceException, PermissionException, NumberException, InstanceNotFoundException,
			InvalidOperationException, NotEnoughBalanceException, NotOwnedException, NotAvaliableException, NotCreatorException {

		User admin = createSavedAdmin();
		Enterprise enterprise = createEnterprise();
		enterprise.setCreatorId(admin.getId());
		enterprise.setStockPrice(10F);
		enterprise.setStock(10);

		stockMarketService.createEnterprise(admin.getId(), enterprise);
		stockMarketService.modifyAvaliableEnterprise(admin.getId(), enterprise.getId(), false);

		User client = createSavedClient();
		stockMarketService.transfer(client.getId(), 1000F, "INCOME");
		stockMarketService.order(client.getId(), OrderType.BUY, OrderLineType.MARKET, 12F, 12, enterprise.getId(),
				LocalDateTime.now().plusDays(10));
		assertEquals(stockMarketService.searchUserActionsNumber(client, enterprise, false), 0);
		assertEquals(false, enterprise.isAvaliable());
		
	}
	
	@Test 
	public void buyEnterpriseInitialActionsResume()
			throws DuplicateInstanceException, PermissionException, NumberException, InstanceNotFoundException,
			InvalidOperationException, NotEnoughBalanceException, NotOwnedException, NotAvaliableException, NotCreatorException {

		User admin = createSavedAdmin();
		Enterprise enterprise = createEnterprise();
		enterprise.setCreatorId(admin.getId());
		enterprise.setStockPrice(10F);
		enterprise.setStock(10);

		stockMarketService.createEnterprise(admin.getId(), enterprise);
		stockMarketService.modifyAvaliableEnterprise(admin.getId(), enterprise.getId(), false);

		User client = createSavedClient();
		stockMarketService.transfer(client.getId(), 1000F, "INCOME");
		stockMarketService.order(client.getId(), OrderType.BUY, OrderLineType.MARKET, 12F, 12, enterprise.getId(),
				LocalDateTime.now().plusDays(10));
		stockMarketService.modifyAvaliableEnterprise(admin.getId(), enterprise.getId(), true);
		assertEquals(stockMarketService.searchUserActionsNumber(client, enterprise, false), 10);
		assertEquals(true, enterprise.isAvaliable());
		
	}
	
	@Test
	public void sellUserActionsLimitAndBuyUserMarket()
			throws DuplicateInstanceException, PermissionException, NumberException, InstanceNotFoundException,
			InvalidOperationException, NotEnoughBalanceException, NotOwnedException, NotAvaliableException {

		User admin = createSavedAdmin();
		Enterprise enterprise = createEnterprise();
		enterprise.setCreatorId(admin.getId());
		enterprise.setStockPrice(10F);
		enterprise.setStock(10);

		stockMarketService.createEnterprise(admin.getId(), enterprise);

		User client = createSavedClient();
		stockMarketService.transfer(client.getId(), 1000F, "INCOME");
		stockMarketService.order(client.getId(), OrderType.BUY, OrderLineType.MARKET, 12F, 12, enterprise.getId(),
				LocalDateTime.now().plusDays(10));
		stockMarketService.order(client.getId(), OrderType.SELL, OrderLineType.LIMIT, 8F, 10, enterprise.getId(),
				LocalDateTime.now().plusDays(10));
		
		User client2 = createNSavedClientWhitBalance(1, 1000F);
		stockMarketService.order(client2.getId(), OrderType.BUY, OrderLineType.MARKET, 12F, 8, enterprise.getId(),
				LocalDateTime.now().plusDays(10));
		
		
		assertEquals(8, stockMarketService.searchUserActionsNumber(client2, enterprise, false));
		
	}
	
	@Test
	public void sellUserActionsMarketAndBuyUserLimit()
			throws DuplicateInstanceException, PermissionException, NumberException, InstanceNotFoundException,
			InvalidOperationException, NotEnoughBalanceException, NotOwnedException, NotAvaliableException {

		User admin = createSavedAdmin();
		Enterprise enterprise = createEnterprise();
		enterprise.setCreatorId(admin.getId());
		enterprise.setStockPrice(10F);
		enterprise.setStock(10);

		stockMarketService.createEnterprise(admin.getId(), enterprise);

		User client = createSavedClient();
		stockMarketService.transfer(client.getId(), 1000F, "INCOME");
		stockMarketService.order(client.getId(), OrderType.BUY, OrderLineType.MARKET, 12F, 12, enterprise.getId(),
				LocalDateTime.now().plusDays(10));
		stockMarketService.order(client.getId(), OrderType.SELL, OrderLineType.MARKET, 8F, 10, enterprise.getId(),
				LocalDateTime.now().plusDays(10));
		
		User client2 = createNSavedClientWhitBalance(1, 1000F);
		stockMarketService.order(client2.getId(), OrderType.BUY, OrderLineType.LIMIT, 12F, 8, enterprise.getId(),
				LocalDateTime.now().plusDays(10));
		
		assertEquals(2, stockMarketService.searchUserActionsNumber(client, enterprise, true));
		assertEquals(8, stockMarketService.searchUserActionsNumber(client2, enterprise, false));
		
	}
	
//	@Test (expected = InstanceNotFoundException.class)
//	
//	public void sellUserActionsMarketAndBuyUserLimitDeletedOrder()
//			throws DuplicateInstanceException, PermissionException, NumberException, InstanceNotFoundException,
//			InvalidOperationException, NotEnoughBalanceException, NotOwnedException, NotAvaliableException {
//
//		User admin = createSavedAdmin();
//		Enterprise enterprise = createEnterprise();
//		enterprise.setCreatorId(admin.getId());
//		enterprise.setStockPrice(10F);
//		enterprise.setStock(10);
//
//		stockMarketService.createEnterprise(admin.getId(), enterprise);
//
//		User client = createSavedClient();
//		long orderId = stockMarketService.order(client.getId(), OrderType.BUY, OrderLineType.MARKET, 12F, 12, enterprise.getId(),
//				LocalDateTime.now().plusDays(10));
//		stockMarketService.deleteOrder(client.getId(), orderId, true);
//		stockMarketService.deleteOrder(client.getId(), orderId, true);
//		
//		
//	}
	
@Test 
	public void searchUserActionsDeletedOrders()
			throws DuplicateInstanceException, PermissionException, NumberException, InstanceNotFoundException,
			InvalidOperationException, NotEnoughBalanceException, NotOwnedException, NotAvaliableException {

		User admin = createSavedAdmin();
		Enterprise enterprise = createEnterprise();
		enterprise.setCreatorId(admin.getId());
		enterprise.setStockPrice(10F);
		enterprise.setStock(10);

		stockMarketService.createEnterprise(admin.getId(), enterprise);

		User client = createSavedClient();
		long orderId = stockMarketService.order(client.getId(), OrderType.BUY, OrderLineType.MARKET, 12F, 12, enterprise.getId(),
				LocalDateTime.now().plusDays(10));
		stockMarketService.deleteOrder(client.getId(), orderId, true);
		assertEquals(true,orderLineDao.findById(orderId).get().getCancelled());
		
		
	}

@Test (expected = NotAvaliableException.class)
public void searchUserActionsDeletedOrdersNotAvaliableThrowsNotAvaliableException()
		throws DuplicateInstanceException, PermissionException, NumberException, InstanceNotFoundException,
		InvalidOperationException, NotEnoughBalanceException, NotOwnedException, NotAvaliableException {

	User admin = createSavedAdmin();
	Enterprise enterprise = createEnterprise();
	enterprise.setCreatorId(admin.getId());
	enterprise.setStockPrice(10F);
	enterprise.setStock(10);

	stockMarketService.createEnterprise(admin.getId(), enterprise);

	User client = createSavedClient();
	long orderId = stockMarketService.order(client.getId(), OrderType.BUY, OrderLineType.MARKET, 12F, 12, enterprise.getId(),
			LocalDateTime.now().plusDays(10));
	stockMarketService.deleteOrder(client.getId(), orderId, false);
	
	
}

@Test (expected = NotOwnedException.class)
public void searchUserActionsDeletedOrdersThrowsNotOwnedException()
		throws DuplicateInstanceException, PermissionException, NumberException, InstanceNotFoundException,
		InvalidOperationException, NotEnoughBalanceException, NotOwnedException, NotAvaliableException {

	User admin = createSavedAdmin();
	Enterprise enterprise = createEnterprise();
	enterprise.setCreatorId(admin.getId());
	enterprise.setStockPrice(10F);
	enterprise.setStock(10);

	stockMarketService.createEnterprise(admin.getId(), enterprise);

	User client = createSavedClient();
	long orderId = stockMarketService.order(client.getId(), OrderType.BUY, OrderLineType.MARKET, 12F, 12, enterprise.getId(),
			LocalDateTime.now().plusDays(10));
	User client2 = createNSavedClientWhitBalance(1, 10f);
	stockMarketService.deleteOrder(client2.getId(), orderId, false);
	
	
}

@Test (expected = NotCreatorException.class)
public void modifyAvaliableEnterpriseThrowsNotCreator()
		throws DuplicateInstanceException, PermissionException, NumberException, InstanceNotFoundException,
		InvalidOperationException, NotEnoughBalanceException, NotOwnedException, NotAvaliableException, NotCreatorException {

	User admin = createSavedAdmin();
	User client = createSavedClient();
	Enterprise enterprise = createEnterprise();
	enterprise.setCreatorId(admin.getId());
	enterprise.setStockPrice(10F);
	enterprise.setStock(10);

	stockMarketService.createEnterprise(admin.getId(), enterprise);
	stockMarketService.modifyAvaliableEnterprise(client.getId(), enterprise.getId(), false);

}

@Test (expected = InstanceNotFoundException.class)
public void modifyAvaliableEnterpriseThrowsInstanceNotFoundExceptionEnterprise()
		throws DuplicateInstanceException, PermissionException, NumberException, InstanceNotFoundException,
		InvalidOperationException, NotEnoughBalanceException, NotOwnedException, NotAvaliableException, NotCreatorException {

	User admin = createSavedAdmin();
	User client = createSavedClient();
	Enterprise enterprise = createEnterprise();
	enterprise.setCreatorId(admin.getId());
	enterprise.setStockPrice(10F);
	enterprise.setStock(10);

	stockMarketService.createEnterprise(admin.getId(), enterprise);
	stockMarketService.modifyAvaliableEnterprise(client.getId(), 2452l, false);

}

@Test (expected = InstanceNotFoundException.class)
public void modifyAvaliableEnterpriseThrowsInstanceNotFoundExceptionUser()
		throws DuplicateInstanceException, PermissionException, NumberException, InstanceNotFoundException,
		InvalidOperationException, NotEnoughBalanceException, NotOwnedException, NotAvaliableException, NotCreatorException {

	User admin = createSavedAdmin();
	User client = createSavedClient();
	Enterprise enterprise = createEnterprise();
	enterprise.setCreatorId(admin.getId());
	enterprise.setStockPrice(10F);
	enterprise.setStock(10);

	stockMarketService.createEnterprise(admin.getId(), enterprise);
	stockMarketService.modifyAvaliableEnterprise(2451l, enterprise.getId(), false);

}

@Test 
public void createAnnualBenefitsTest() 
		throws DuplicateInstanceException, PermissionException, NumberException, InstanceNotFoundException,
		InvalidOperationException, NotEnoughBalanceException, NotOwnedException, NotAvaliableException, NotCreatorException, InvalidArgumentException {

	User admin = createSavedAdmin();
	User client = createSavedClient();
	Enterprise enterprise = createEnterprise();
	enterprise.setCreatorId(admin.getId());
	enterprise.setStockPrice(10F);
	enterprise.setStock(10);

	stockMarketService.createEnterprise(admin.getId(), enterprise);
	stockMarketService.createAnnualBenefits(admin.getId(), enterprise.getId(), createAnnualBenefits());

}

@Test (expected = PermissionException.class)
public void createAnnualBenefitsTestThrowsPermissionException() 
		throws DuplicateInstanceException, PermissionException, NumberException, InstanceNotFoundException,
		InvalidOperationException, NotEnoughBalanceException, NotOwnedException, NotAvaliableException, NotCreatorException, InvalidArgumentException {

	User admin = createSavedAdmin();
	User client = createSavedClient();
	Enterprise enterprise = createEnterprise();
	enterprise.setCreatorId(admin.getId());
	enterprise.setStockPrice(10F);
	enterprise.setStock(10);

	stockMarketService.createEnterprise(admin.getId(), enterprise);
	stockMarketService.createAnnualBenefits(client.getId(), enterprise.getId(), createAnnualBenefits());

}
	
@Test (expected = InstanceNotFoundException.class)
public void createAnnualBenefitsTestThrowsInstanceNotFoundException() 
		throws DuplicateInstanceException, PermissionException, NumberException, InstanceNotFoundException,
		InvalidOperationException, NotEnoughBalanceException, NotOwnedException, NotAvaliableException, NotCreatorException, InvalidArgumentException {

	User admin = createSavedAdmin();
	User client = createSavedClient();
	Enterprise enterprise = createEnterprise();
	enterprise.setCreatorId(admin.getId());
	enterprise.setStockPrice(10F);
	enterprise.setStock(10);

	stockMarketService.createEnterprise(admin.getId(), enterprise);
	stockMarketService.createAnnualBenefits(admin.getId(), 23453245l, createAnnualBenefits());

}

}
