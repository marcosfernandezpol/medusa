package es.udc.fi.dc.fd.rest.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.fi.dc.fd.model.common.exceptions.DuplicateInstanceException;
import es.udc.fi.dc.fd.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.dc.fd.model.common.exceptions.InvalidOperationException;
import es.udc.fi.dc.fd.model.common.exceptions.NotEnoughBalanceException;
import es.udc.fi.dc.fd.model.common.exceptions.NotOwnedException;
import es.udc.fi.dc.fd.model.common.exceptions.NotAvaliableException;
import es.udc.fi.dc.fd.model.common.exceptions.NotCreatorException;
import es.udc.fi.dc.fd.model.common.exceptions.NumberException;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.services.StockMarketService;
import es.udc.fi.dc.fd.model.services.exceptions.InvalidArgumentException;
import es.udc.fi.dc.fd.model.services.exceptions.PermissionException;
import es.udc.fi.dc.fd.rest.common.ErrorsDto;
import es.udc.fi.dc.fd.rest.dtos.AnnualBenefitsListDto;
import es.udc.fi.dc.fd.rest.dtos.DeleteParamsDto;
import es.udc.fi.dc.fd.rest.dtos.EnterpriseConversor;
import es.udc.fi.dc.fd.rest.dtos.EnterpriseDto;
import es.udc.fi.dc.fd.rest.dtos.OrderParamsDto;
import es.udc.fi.dc.fd.rest.dtos.EnterpriseSummaryConversor;
import es.udc.fi.dc.fd.rest.dtos.EnterpriseSummaryDto;
import es.udc.fi.dc.fd.rest.dtos.TransferParamsDto;

@RestController
@RequestMapping("/market")
public class MarketController {

	private static final String DUPLICATE_INSTANCE_EXCEPTION_CODE = "project.exceptions.DuplicateInstanceException";
	private static final String INVALID_OPERATION_EXCEPTION_CODE = "project.exceptions.InvalidOperationException";
	private static final String NOT_ENOUGH_BALANCE_EXCEPTION_CODE = "project.exceptions.NotEnoughBalanceException";
	private static final String NOT_OWNED_EXCEPTION_CODE = "project.exceptions.NotOwnedException";
	private static final String NUMBER_EXCEPTION_CODE = "project.exceptions.NumberException";
	private static final String INVALID_ARGUMENT_EXCEPTION_CODE = "project.exceptions.InvalidArgumentException";
	private static final String NOT_AVALIABLE_EXCEPTION_CODE = "project.exceptions.NotAvaliableException";

	/** The user service. */
	@Autowired
	private StockMarketService marketService;

	/** The message source. */
	@Autowired
	private MessageSource messageSource;

	/**
	 * Handle duplicate instance exception.
	 *
	 * @param exception the exception
	 * @param locale    the locale
	 * @return the errors dto
	 */
	/**
	 * Handle duplicate instance exception.
	 *
	 * @param exception the exception
	 * @param locale    the locale
	 * @return the errors dto
	 */
	@ExceptionHandler(DuplicateInstanceException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorsDto handleDuplicateInstanceException(DuplicateInstanceException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(DUPLICATE_INSTANCE_EXCEPTION_CODE, null,
				DUPLICATE_INSTANCE_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);

	}

	/**
	 * Handle Stock Not Owned exception.
	 *
	 * @param exception the exception
	 * @param locale    the locale
	 * @return the errors dto
	 */
	@ExceptionHandler(NotOwnedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleNotOwnedException(NotOwnedException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(NOT_OWNED_EXCEPTION_CODE, null, NOT_OWNED_EXCEPTION_CODE,
				locale);

		return new ErrorsDto(errorMessage);

	}

	/**
	 * Handle invalid operation exception.
	 *
	 * @param exception the exception
	 * @param locale    the locale
	 * @return the errors dto
	 */
	@ExceptionHandler(InvalidOperationException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorsDto handleInvalidOperationException(InvalidOperationException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(INVALID_OPERATION_EXCEPTION_CODE, null,
				INVALID_OPERATION_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);

	}

	/**
	 * Handle not enough balance exception.
	 *
	 * @param exception the exception
	 * @param locale    the locale
	 * @return the errors dto
	 */
	@ExceptionHandler(NotEnoughBalanceException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorsDto handleNotEnoughBalanceException(NotEnoughBalanceException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(NOT_ENOUGH_BALANCE_EXCEPTION_CODE, null,
				NOT_ENOUGH_BALANCE_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);

	}

	@ExceptionHandler(NumberException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorsDto handleNumberException(NumberException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(NUMBER_EXCEPTION_CODE, null, NUMBER_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);

	}

	@ExceptionHandler(InvalidArgumentException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorsDto handleInvalidArgumentException(InvalidArgumentException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(INVALID_ARGUMENT_EXCEPTION_CODE, null,
				INVALID_ARGUMENT_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);

	}
	

	
	@ExceptionHandler(NotAvaliableException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorsDto handleNotAvaliableException(NotAvaliableException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(NOT_AVALIABLE_EXCEPTION_CODE, null,
				NOT_AVALIABLE_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);

	}

	/**
	 * Create an enterprise.
	 *
	 * @param enterprise the enterprise dto
	 * @return the response entity
	 * @throws DuplicateInstanceException the duplicate instance exception
	 * @throws PermissionException
	 * @throws NumberException
	 * @throws InvalidArgumentException
	 */
	@PostMapping("/create_enterprise")
	public EnterpriseSummaryDto createEnterprise(@RequestAttribute Long userId,
			@Validated @RequestBody EnterpriseSummaryDto enterpriseDto)
			throws DuplicateInstanceException, PermissionException, NumberException, InvalidArgumentException {
		
		enterpriseDto.setCreatorId(userId);
		Enterprise enterprise = EnterpriseSummaryConversor.toEnterpriseSummary(enterpriseDto);
		Enterprise e = marketService.createEnterprise(userId, enterprise);
		return EnterpriseSummaryConversor.toEnterpriseSummaryDto(e);
	}

	/**
	 * 
	 * @param enterprise the dto
	 * @return enterprise modified
	 * @throws DuplicateInstanceException
	 * 
	 */
	@PutMapping("/update_enterprise/{id}")
	public EnterpriseDto updateEnterprise(@RequestAttribute Long userId, @PathVariable("id") Long id,
			@Validated({ EnterpriseDto.UpdateValidations.class }) @RequestBody AnnualBenefitsListDto params)
			throws InstanceNotFoundException, PermissionException, DuplicateInstanceException,
			InvalidArgumentException {

		Enterprise enterprise = marketService.createAnnualBenefits(userId, id, params);

		return EnterpriseConversor.toEnterpriseDto(enterprise);
	}

	/**
	 * Make a money transfer.
	 */
	@PostMapping("/transfer")
	public TransferParamsDto transfer(@RequestAttribute Long userId, @Validated @RequestBody TransferParamsDto params)
			throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException {
			
			TransferParamsDto result = new TransferParamsDto();
			result.setMoney(marketService.transfer(userId, params.getMoney(), params.getOperation()));
			
		return result;
	}

	/**
	 * Order a stock transference.
	 */
	@PostMapping("/order")
	public void order(@RequestAttribute Long userId, @Validated @RequestBody OrderParamsDto params)
			throws NotEnoughBalanceException, NotOwnedException, NotAvaliableException {
		marketService.order(userId, params.getType(), params.getOrderLineType(), params.getPrice(), params.getNumber(), params.getEnterpriseId(),
				params.getDeadline());
	}

	/**
	 * Delete not executed orders.
	 */
	@PostMapping("/delete_order")
	public void delete(@RequestAttribute Long userId, @Validated @RequestBody DeleteParamsDto params)
			throws NotOwnedException, InstanceNotFoundException, NotAvaliableException {

		marketService.deleteOrder(userId, params.getOrderId(), params.getAvaliable());
	}

	/**
	 * Modify availability of enterprises.
	 */
	@PutMapping("/avaliable/{id}")
	public EnterpriseDto avaliable_enterprise(@RequestAttribute Long userId, @PathVariable("id") Long enterpriseId,
			@RequestBody EnterpriseSummaryDto params)

			throws NotCreatorException, InstanceNotFoundException {

		Enterprise enterprise = marketService.modifyAvaliableEnterprise(userId, enterpriseId, !params.isAvaliable());

		return EnterpriseConversor.toEnterpriseDto(enterprise);
	}

}
