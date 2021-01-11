/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * Account History Item Informations
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Sun, 2021-01-10 - 21:29:17
 */
@Builder
@Data
public class OperationHistory {
	
	/**
	 * Operation Timestamp
	 */
	@Schema(description = "Aource Operation")
	private Operation operation;
	
	/**
	 * Account Balance
	 */
	@Schema(description = "Account Balance")
	private float accountBalance;
}
