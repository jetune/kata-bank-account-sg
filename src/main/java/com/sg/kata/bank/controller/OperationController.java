/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.controller;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sg.kata.bank.service.OperationService;
import com.sg.kata.bank.tools.rest.ErrorDetails;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Operation Controller 
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Mon, 2021-01-11 - 09:51:56
 */
@RestController
@Validated
@RequestMapping(path = "/v1/operation")
@Tag(
	name = "Bank Operations API",
	description = "Endpoints  allowing execute Account Operations"
)
@ApiResponses(
	value = {
		@ApiResponse(
			responseCode = "500",
			description = "Something wrong in the back-end process",
			content = @Content(
					mediaType = "application/json",
					schema = @Schema(implementation = ErrorDetails.class)
			)
		)
	}
)
public class OperationController {
	
	/**
	 * Operation Service
	 */
	@Autowired
	private OperationService service;
	
	/**
	 * Method used to execute deposit operation 
	 * @param accountNumber	Target Account Number
	 * @param amount	Deposit Amount
	 * @return	Operation Status
	 */
	@Operation(
		operationId = "deposit",
		summary = "Execute deposit",
		description = "Allow to execute deposit",
		tags = { "Operations" }
	)
	@PutMapping(
		path = "/deposit/{accountNumber}/{amount}",
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	@ResponseBody
	public Boolean deposit(
			
			@Parameter(name = "accountNumber", description = "Deposit Target Account", required = true)
			@PathVariable(name = "accountNumber", required = true)
			@NotEmpty
			String accountNumber,
			
			@Parameter(name = "amount", description = "Deposit Amount", required = true)
			@PathVariable(name = "amount", required = true)
			Float amount) {
		
		// Execute deposit and return status
		return service.deposit(accountNumber, amount);
	}

	/**
	 * Method used to execute withdrawal operation 
	 * @param accountNumber	Target Account Number
	 * @param amount	Withdrawal Amount
	 * @return	Operation Status
	 */
	@Operation(
		operationId = "withdrawal",
		summary = "Execute Withdrawal",
		description = "Allow to execute withdrawal",
		tags = { "Operations" }
	)
	@PutMapping(
		path = "/withdrawal/{accountNumber}/{amount}",
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	@ResponseBody
	public Boolean withdrawal(
			
			@Parameter(name = "accountNumber", description = "Withdrawal Target Acount", required = true)
			@PathVariable("accountNumber")
			@NotEmpty
			String accountNumber,
			
			@Parameter(name = "amount", description = "Withdrawal Amount", required = true)
			@PathVariable("amount")
			@NotEmpty
			Float amount,
			
			@Parameter(name = "full", description = "Full Withdrawal", required = false)
			@RequestParam(name = "full", required = false, defaultValue = "false")
			Boolean full) {
		
		// Execute withdrawal and return status
		return service.withdrawal(accountNumber, amount);
	}

}
