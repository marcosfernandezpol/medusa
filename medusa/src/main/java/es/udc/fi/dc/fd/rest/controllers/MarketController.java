package es.udc.fi.dc.fd.rest.controllers;

import java.util.Locale;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.fi.dc.fd.model.common.exceptions.*;
import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.services.StockMarketService;
import es.udc.fi.dc.fd.model.services.exceptions.PermissionException;
import es.udc.fi.dc.fd.rest.dtos.EnterpriseDto;
import es.udc.fi.dc.fd.rest.dtos.TransferParamsDto;
import es.udc.fi.dc.fd.rest.common.ErrorsDto;
import es.udc.fi.dc.fd.rest.dtos.EnterpriseConversor;

@RestController
@RequestMapping("/market")
public class MarketController {

	private final static String DUPLICATE_INSTANCE_EXCEPTION_CODE = "project.exceptions.DuplicateInstanceException";
	private final static String INVALID_OPERATION_EXCEPTION_CODE = "project.exceptions.InvalidOperationException";
	private final static String NOT_ENOUGH_BALANCE_EXCEPTION_CODE = "project.exceptions.NotEnoughBalanceException";
	private final static String NUMBER_EXCEPTION_CODE = "project.exceptions.NumberException";

	
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
	@ExceptionHandler(DuplicateInstanceException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorsDto handleDuplicateInstanceException(DuplicateInstanceException exception, Locale locale) {

		String errorMessage = messageSource.getMessage(DUPLICATE_INSTANCE_EXCEPTION_CODE, null,
				DUPLICATE_INSTANCE_EXCEPTION_CODE, locale);

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

		String errorMessage = messageSource.getMessage(NUMBER_EXCEPTION_CODE, null,
				NUMBER_EXCEPTION_CODE, locale);

		return new ErrorsDto(errorMessage);

	}

	/**
	 * Create an enterprise.
	 *
	 * @param enterprise the enterprise dto
	 * @return the response entity
	 * @throws DuplicateInstanceException the duplicate instance exception
	 * @throws PermissionException
	 */
	@PostMapping("/create_enterprise")
	public EnterpriseDto createEnterprise(@RequestAttribute Long userId,
			@Validated @RequestBody EnterpriseDto enterpriseDto)
			throws DuplicateInstanceException, PermissionException, NumberException {

		Enterprise enterprise = EnterpriseConversor.toEnterprise(enterpriseDto);
		Enterprise e = marketService.createEnterprise(userId, enterprise);
		return EnterpriseConversor.toEnterpriseDto(e);
	}

	/**
	 * Make a money transfer.
	 */
	@PostMapping("/transfer")
	public void transfer(@RequestAttribute Long userId, @Validated @RequestBody TransferParamsDto params)
			throws InvalidOperationException, InstanceNotFoundException, NotEnoughBalanceException {
		marketService.transfer(userId, params.getMoney(), params.getOperation());
	}

}
