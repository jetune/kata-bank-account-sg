/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Bank client
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Sun, 2021-01-10 - 17:19:45
 */
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client {
	
	/**
	 * Client ID
	 */
	@Schema(description = "Client ID")
	@EqualsAndHashCode.Include
	private String id;
	
	/**
	 * Client Last Name
	 */
	@Schema(description = "Client Last Name")
	private String lastName;
	
	/**
	 * Client First Name
	 */
	@Schema(description = "Client First Name")
	private String firstName;
	
	/**
	 * Client Birth Date
	 */
	@Schema(description = "Client Birth Date")
	private LocalDate birthDate;
	
	/**
	 * Client Address
	 */
	@Schema(description = "Client Address")
	private Address address;
	
	/**
	 * Client Phone
	 */
	@Schema(description = "Client Phone")
	private String phone;
	
	/**
	 * Client Email
	 */
	@Schema(description = "Client Email")
	private String email;
}
