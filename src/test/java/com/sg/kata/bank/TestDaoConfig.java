/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration Class for test
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Mon, 2021-01-11 - 17:48:46
 */
@Configuration
@ConditionalOnProperty(
	prefix = "app.bank.account.test.dao",
	name = "enabled",
	havingValue = "true",
	matchIfMissing = false
)
@ComponentScan ("com.sg.kata.bank.dao")
public class TestDaoConfig {}
