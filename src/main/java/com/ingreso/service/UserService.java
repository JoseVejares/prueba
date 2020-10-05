package com.ingreso.service;

import com.ingreso.model.User;

public interface UserService {
	
	 User saveUser(User user);
	 User getUserByEmail(String correo);	

}
