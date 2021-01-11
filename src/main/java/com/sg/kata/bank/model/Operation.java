/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.model;

import java.time.LocalDateTime;

import com.sg.kata.bank.model.constants.OperationType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * Account Operation
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Sun, 2021-01-10 - 19:32:49
 */
@Builder
@Data
public class Operation {
	
	/**
	 * Operation Type
	 */
	@Schema(description = "Operation Type")
	private OperationType type;
	
	/**
	 * Operation Amount
	 */
	@Schema(description = "Operation Amount")
	private float amount;
	
	/**
	 * Target Account
	 */
	@Schema(description = "Operation Account Number")
	private String accountNumber;
	
	/**
	 * Operation Date/Time
	 */
	@Schema(description = "Operation Timestamp")
	private LocalDateTime timestamp;
}
