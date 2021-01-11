/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.dao.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sg.kata.bank.dao.ClientDao;
import com.sg.kata.bank.exception.BankAccountException;
import com.sg.kata.bank.model.Client;

/**
 * Client Data Access Object (In Memory) Implementation
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Sun, 2021-01-10 - 22:03:55
 */
@Repository
public class InMemoryMapClientDaoImpl implements ClientDao {
	
	/**
	 * In Memory Client Data Store
	 */
	private Map<String, Client> clients = new HashMap<>();
	
	/* (non-Javadoc)
	 * @see com.sg.kata.bank.dao.ClientDao#save(com.sg.kata.bank.model.Client)
	 */
	@Override
	public synchronized Client save(Client client) {
		
		// if Client is null
		if(Objects.isNull(client)) {
			
			// Throw Exception
			throw new BankAccountException("SAVE_CLIENT_INVALID_CLIENT_PARAMETER", "Invalid Client");
		}
		
		// if Client ID is null
		if(StringUtils.isBlank(client.getId())) {
			
			// Throw Exception
			throw new BankAccountException("SAVE_CLIENT_INVALID_CLIENT_ID", "Invalid Client ID");
		}
		
		// If Client Already Exists
		if(clients.containsKey(client.getId())) {
			
			// Throw Exception
			throw new BankAccountException("SAVE_CLIENT_ALREADY_EXISTS", "Invalid Client ID");
		}
		
		// Save in Map
		clients.put(client.getId(), client);
		
		// Save and return Saved Client
		return client;
	}
	
	/* (non-Javadoc)
	 * @see com.sg.kata.bank.dao.ClientDao#findById(java.lang.String)
	 */
	@Override
	public synchronized Client findById(String clientId) {

		// if Client ID is BlanK
		if(StringUtils.isBlank(clientId)) {
			
			// Return all Clients
			throw new BankAccountException("FIND_CLIENT_INVALID_CLIENT_ID", "Invalid Client ID");
		}
		
		// Get the Client
		Client client = clients.get(clientId);
		
		// If Client Is Null
		if(Objects.isNull(client)) {
			
			// Return all Clients
			throw new BankAccountException("FIND_CLIENT_NOT_FOUND", "Client ID Not Found");
		}
		
		// Find and return Client
		return client;
	}
}
