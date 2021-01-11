/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sg.kata.bank.model.OperationHistory;
import com.sg.kata.bank.service.AccountStatementService;
import com.sg.kata.bank.tools.rest.ErrorDetails;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Account Statement Controller 
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Mon, 2021-01-11 - 09:51:56
 */
@RestController
@Validated
@RequestMapping(path = "/v1/statement")
@Tag(
	name = "Bank Account Statement API",
	description = "Endpoints  allowing execute Account Statement Operations"
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
public class AccountStatementController {
	
	/**
	 * Account Statement Service
	 */
	@Autowired
	private AccountStatementService service;
	
	/**
	 * Method used to execute deposit operation 
	 * @param accountNumber	Target Account Number
	 * @param amount	Deposit Amount
	 * @return	Operation Status
	 */
	@Operation(
		operationId = "operationHistories",
		summary = "Account Operation History",
		description = "Allow to find Account Oerations History",
		tags = { "Account Statements" }
	)
	@GetMapping(
		path = "/data/{accountNumber}",
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	@ResponseBody
	public List<OperationHistory> operationHistories(
			
			@Parameter(name = "accountNumber", description = "Deposit Target Account", required = true)
			@PathVariable(name = "accountNumber", required = true)
			@NotEmpty
			String accountNumber) {
		
		// Execute deposit and return status
		return service.accountStatement(accountNumber, LocalDateTime.MIN);
	}
	
	/**
	 * Method used to execute deposit operation 
	 * @param accountNumber	Target Account Number
	 * @param amount	Deposit Amount
	 * @return	Operation Status
	 */
	@Operation(
		operationId = "printAccountStatement",
		summary = "Print Account Statements",
		description = "Allow to find Formatted Account Statement",
		tags = { "Account Statements" }
	)
	@GetMapping(
		path = "/print/{accountNumber}",
		produces = { MediaType.TEXT_PLAIN_VALUE }
	)
	@ResponseBody
	public String printAccountStatement(
			
			@Parameter(name = "accountNumber", description = "Deposit Target Account", required = true)
			@PathVariable(name = "accountNumber", required = true)
			@NotEmpty
			String accountNumber) {
		
		// Execute deposit and return status
		return service.printAccountStatement(accountNumber, LocalDateTime.MIN);
	}
}
