/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.sg.kata.bank.dao.AccountDao;
import com.sg.kata.bank.dao.ClientDao;
import com.sg.kata.bank.dao.OperationDao;
import com.sg.kata.bank.exception.BankAccountException;
import com.sg.kata.bank.model.Account;
import com.sg.kata.bank.model.Client;
import com.sg.kata.bank.model.Operation;
import com.sg.kata.bank.model.constants.OperationType;

/**
 * Test Class for Operation DAO
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Mon, 2021-01-11 - 17:52:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource({
	"classpath:test-dao/test-dao-config.properties"
})
@ContextConfiguration(classes = { TestDaoConfig.class})
public class OperationDaoTest {
	
	/**
	 * Operation DAO
	 */
	@Autowired
	private OperationDao operationDao;

	/**
	 * Account DAO
	 */
	@Autowired
	private AccountDao accountDao;

	/**
	 * Client DAO
	 */
	@Autowired
	private ClientDao clientDao;
	
	/**
	 * Method used to test the save Operation with null operation
	 */
	@Test(expected = BankAccountException.class)
	public void testSaveOperation_Null_Operation() {
		
		// Try to save null operation
		operationDao.save(null);
	}

	/**
	 * Method used to test the save Operation with no account number
	 */
	@Test
	public void testSaveOperation_No_Account_Number() {
		
		// Build a operation
		Operation opetation = Operation.builder().accountNumber("").build();
		
		try {
			
			// Try to save null operation
			operationDao.save(opetation);
			
		} catch (BankAccountException e) {
			
			// Assertion
			assertEquals("SAVE_OPERATION_INVALID_OPERATION_ACCOUNT", e.getCode());
		}	
	}

	/**
	 * Method used to test the save Operation with no account number
	 */
	@Test
	public void testSaveOperation_OK() {
		
		// Client ID
		String clientId = "CLT001";
		
		// Create Client
		clientDao.save(Client.builder()
			.id(clientId)
			.email("jetune@kube-cloud.com")
			.lastName("ETUNE")
			.firstName("JJ").build()
		);
		
		// Account Number
		String accountNumber = "ACC001";
		
		// Create Account
		accountDao.save(Account.builder()
			.number(accountNumber)
			.clientId(clientId)
			.build()
		);
		
		// Build a operation
		Operation opetation = Operation.builder()
				.accountNumber(accountNumber)
				.amount(10000)
				.timestamp(LocalDateTime.now())
				.type(OperationType.DEPOSIT)
				.build();

		// Try to save null operation
		operationDao.save(opetation);
		
		// Find the operation
		List<Operation> operations = operationDao.findByAccountNumber(accountNumber);
		
		// Checks
		assertEquals(1, operations.size());
		assertEquals(accountNumber, operations.get(0).getAccountNumber());
		assertEquals(10000, operations.get(0).getAmount());
		assertEquals(OperationType.DEPOSIT, operations.get(0).getType());
	}
}
