/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sg.kata.bank.model.Account;
import com.sg.kata.bank.service.AccountService;
import com.sg.kata.bank.tools.rest.ErrorDetails;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Account Controller 
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Mon, 2021-01-11 - 09:51:56
 */
@RestController
@Validated
@RequestMapping(path = "/v1/account")
@Tag(
	name = "Bank Account API",
	description = "Endpoints  allowing Account Operations"
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
public class AccountController {
	
	/**
	 * Client Service
	 */
	@Autowired
	private AccountService service;
	
	/**
	 * Method used to save client
	 * @param account	Account to save
	 * @return	Saved Account
	 */
	@Operation(
		operationId = "save",
		summary = "Save Account",
		description = "Allow to Save Account Informations",
		tags = { "Account" }
	)
	@PostMapping(
		consumes = { APPLICATION_JSON_VALUE },
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	@ResponseBody
	public Account save(@RequestBody Account account) {
		
		// Save and return account
		return service.save(account);
	}
	
	/**
	 * Method used to find Account by Number
	 * @param accountNumber
	 * @return	Founded Account
	 */
	@Operation(
		operationId = "findByNumber",
		summary = "Find By Number",
		description = "Find Account by ID",
		tags = { "Account" }
	)
	@ApiResponse(
		responseCode = "200",
		description = "Operation ended successfully.",
		content = @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))
	)
	@GetMapping(
		path = "/{accountNumber}",
		produces = { APPLICATION_JSON_VALUE }
	)
	@ResponseBody
	public Account findByNumber(
			
			@Parameter(name = "accountNumber", description = "Target Account Number", required = true)
			@PathVariable(name = "accountNumber", required = true)
			@NotEmpty
			String accountNumber) {
		
		// Return founded Account
		return service.findByNumber(accountNumber);
	}
}
