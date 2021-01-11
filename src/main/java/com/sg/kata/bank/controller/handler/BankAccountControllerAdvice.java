package com.sg.kata.bank.controller.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.gson.Gson;
import com.sg.kata.bank.exception.BankAccountException;
import com.sg.kata.bank.tools.rest.ErrorDetails;

import lombok.extern.log4j.Log4j2;

/**
 * Rest Controller Exception handler advice 
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Mon, 2021-01-11 - 02:41:31
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Log4j2
public class BankAccountControllerAdvice extends ResponseEntityExceptionHandler {
	
	/**
	 * GSon Mapper
	 */
	@Autowired
	private Gson gson;

	/**
	 * Handle Application base exception
	 * @param exception	Exception occured
	 * @return	Response to send to client
	 */
	@ExceptionHandler(BankAccountException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorDetails handleBaseException(
			BankAccountException exception,
			WebRequest request) {
		
		// Build Error details
		ErrorDetails errorDetails = ErrorDetails.builder()
				.requestContextPath(request.getContextPath())
				.requestDescription(request.getDescription(true))
				.errorMessage(exception.getMessage())
				.errorCode(exception.getCode())
				.errorType(exception.getClass().getName())
				.error(exception)
				.build();
		
		// Log
		log.error(gson.toJson(errorDetails));
		
		// Build ane return error details
		return errorDetails;
	}
	
	/**
	 * Handle Application base exception
	 * @param exception	Exception occured
	 * @return	Response to send to client
	 */
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorDetails handleOtherException(
			Throwable exception,
			WebRequest request) {
		
		// Build Error details
		ErrorDetails errorDetails = ErrorDetails.builder()
				.requestContextPath(request.getContextPath())
				.requestDescription(request.getDescription(true))
				.errorMessage(exception.getMessage())
				.errorCode("UNKNOWN")
				.errorType(exception.getClass().getName())
				.error(exception)
				.build();

		// Log
		log.error(gson.toJson(errorDetails));
		
		// Build ane return error details
		return errorDetails;
	}
}
