package com.ingreso.serviceImpl;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingreso.model.User;
import com.ingreso.repo.IUserRepository;
import com.ingreso.service.UserService;

@Service
public class UserserviceImpl implements UserService {
	private static final Logger LOGGER = Logger.getLogger( UserserviceImpl.class.getName() );
	
	@Autowired
	IUserRepository repo;

	@Override
	public User saveUser(User user) {
		LOGGER.info(" UserserviceImpl.saveUser user : "+user.toString());
		User response = repo.save(user);
		return response;
	}

	@Override
	public User getUserByEmail(String correo) {
		LOGGER.info(" UserserviceImpl.getUserByEmail correo : "+correo);
		 User response = repo.getUserByEmail(correo);
		return response;
	}

}
