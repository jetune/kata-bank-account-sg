/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sg.kata.bank.model.constants.OperationType;
import com.sg.kata.bank.model.constants.TimeHelper;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account Operation
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Sun, 2021-01-10 - 19:32:49
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TimeHelper.DATE_FORMAT_TIME)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime timestamp;
}
