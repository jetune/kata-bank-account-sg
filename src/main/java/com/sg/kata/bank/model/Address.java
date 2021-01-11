/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * Address Informations
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Sun, 2021-01-10 - 17:26:01
 */
@Builder
@Data
public class Address {
	
	/**
	 * Country
	 */
	@Schema(description = "Address Country")
	private String country;
	
	/**
	 * Town
	 */
	@Schema(description = "Address Town")
	private String town;
	
	/**
	 * Street
	 */
	@Schema(description = "Address Street")
	private String street;
	
	/**
	 * Number
	 */
	@Schema(description = "Building Number")
	private String number;
}
