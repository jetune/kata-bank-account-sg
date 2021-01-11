/**
 * DISNEY ::LODGING :: OLCI
 */
package com.sg.kata.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.kata.bank.dao.ClientDao;
import com.sg.kata.bank.model.Client;
import com.sg.kata.bank.service.ClientService;

/**
 * Client Service Implementation
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Mon, 2021-01-11 - 00:06:40
 */
@Service
public class ClientServiceImpl implements ClientService {
	
	/**
	 * Client DAO
	 */
	@Autowired
	private ClientDao clientDao;
	
	/* (non-Javadoc)
	 * @see com.sg.kata.bank.service.ClientService#save(com.sg.kata.bank.model.Client)
	 */
	@Override
	public Client save(Client client) {
		
		// Return client
		return clientDao.save(client);
	}

	/* (non-Javadoc)
	 * @see com.sg.kata.bank.service.ClientService#findById(java.lang.String)
	 */
	@Override
	public Client findById(String clientId) {
		
		// Find and return the Client by ID
		return clientDao.findById(clientId);
	}
}
