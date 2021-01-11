package com.sg.kata.bank.tools.rest;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Rest error details informations 
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Mon, 2021-01-11 - 02:38:56
 */
@Schema(description = "Informations about some internal compute error")
@Builder
@Getter
@ToString
public class ErrorDetails {

	/**
	 * Date the error occured
	 */
	@Schema(
			accessMode = AccessMode.READ_ONLY,
			name = "timestamp",
			required = true,
			description = "Date the error occured"
	)
	@Builder.Default
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime timestamp = LocalDateTime.now();
	
	/**
	 * ContextPath of the source request
	 */
	@Schema(
			accessMode = AccessMode.READ_ONLY,
			name = "requestContextPath",
			required = true,
			description = "ContextPath of the source request"
	)
	private String requestContextPath;
	
	/**
	 * Request description of the service that caused the error
	 */
	@Schema(
			accessMode = AccessMode.READ_ONLY,
			name = "requestDescription",
			required = false,
			description = "Request description of the service that caused the error"
	)
	private String requestDescription;

	/**
	 * Error code
	 */
	@Schema(
			accessMode = AccessMode.READ_ONLY,
			name = "errorCode",
			required = true,
			description = "Error code"
	)
	private String errorCode;
	
	/**
	 * Error message
	 */
	@Schema(
			accessMode = AccessMode.READ_ONLY,
			name = "errorMessage",
			required = true,
			description = "Error message"
	)
	private String errorMessage;
	
	/**
	 * Error type
	 */
	@Schema(
			accessMode = AccessMode.READ_ONLY,
			name = "errorType",
			required = true,
			description = "Error type"
	)
	private String errorType;
	
	/**
	 * Error extended informations
	 */
	@Schema(
			accessMode = AccessMode.READ_ONLY,
			name = "extendedInfos",
			required = false,
			description = "Error Extended Informations"
	)
	private String extendedInfos;

	/**
	 * Error Value
	 */
	@Schema(
			accessMode = AccessMode.READ_ONLY,
			name = "error"
	)
	@JsonIgnore
	private Throwable error;
}
