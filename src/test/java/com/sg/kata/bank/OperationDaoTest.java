/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank;

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
import com.sg.kata.bank.model.Operation;

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
	 * Client DAO
	 */
	@Autowired
	private ClientDao clientDao;

	/**
	 * Account DAO
	 */
	@Autowired
	private AccountDao accountDao;

	/**
	 * Operation DAO
	 */
	@Autowired
	private OperationDao operationDao;
	
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
	@Test(expected = BankAccountException.class)
	public void testSaveOperation_No_Account_Number() {
		
		// Build a operation
		Operation opetation = Operation.builder().accountNumber("").build();
		
		// Try to save null operation
		operationDao.save(opetation);
	}
}
