/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Bank Account
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Sun, 2021-01-10 - 18:34:17
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class Account {
	
	/**
	 * Account Number
	 */
	@JsonProperty
	@Schema(description = "Account Number")
	private String number;
	
	/**
	 * Account Balance (compute automatically)
	 */
	@Builder.Default
	@JsonProperty
	@Schema(description = "Account Balance")
	private float balance = 0F;
	
	/**
	 * Account Owner
	 */
	@Schema(description = "Account Owner")
	private String clientId; 
}
