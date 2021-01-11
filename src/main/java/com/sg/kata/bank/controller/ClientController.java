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

import com.sg.kata.bank.model.Client;
import com.sg.kata.bank.service.ClientService;
import com.sg.kata.bank.tools.rest.ErrorDetails;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Client Controller
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Mon, 2021-01-11 - 02:34:12
 */
@RestController
@Validated
@RequestMapping(path = "/v1/client")
@Tag(
	name = "Bank Client API",
	description = "Endpoints  allowing Client Operations"
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
		),
		@ApiResponse(
			responseCode = "401",
			description = "Unauthorized",
			content = @Content(
					mediaType = "application/json",
					schema = @Schema(implementation = ErrorDetails.class)
			)
		)
	}
)
public class ClientController {
	
	/**
	 * Client Service
	 */
	@Autowired
	private ClientService service;
	
	/**
	 * Method used to save client
	 * @param client	Client to save
	 * @return	Saved Client
	 */
	@PostMapping(
		consumes = { APPLICATION_JSON_VALUE },
		produces = { MediaType.APPLICATION_PDF_VALUE }
	)
	public Client save(@RequestBody Client client) {
		
		// Save and return client
		return service.save(client);
	}
	
	/**
	 * Method used to find client by ID
	 * @param clientId	Client ID
	 * @return	Founded Client
	 */
	@ApiResponse(
		responseCode = "200",
		description = "Operation ended successfully.",
		content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))
	)
	@GetMapping(
		path = "/{clientId}",
		produces = { APPLICATION_JSON_VALUE }
	)
	@ResponseBody
	public Client findById(
			@PathVariable("clientId")
			@NotEmpty
			String clientId) {
		
		// Return founded Client
		return service.findById(clientId);
	}
}
