package com.ingreso.controller;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ingreso.model.User;
import com.ingreso.service.UserService;
import com.ingreso.utils.ValidacionCampos;

@RestController
@RequestMapping(value = "/Users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	private static final Logger LOGGER = Logger.getLogger( UserController.class.getName() );
	
	@Autowired
	UserService userService;
	
	@PostMapping("/saveUser")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		ValidacionCampos validacionCampos = new ValidacionCampos();
		LOGGER.info("UserController.saveUser User : "+user.toString());
		try {
			//Validate email and password fields
			if(
				user.getEmail() != null && !user.getEmail().trim().equals("") &&
				user.getPassword() != null && !user.getPassword().trim().equals("") &&
				validacionCampos.validateEmailAndPassword(user.getEmail(), user.getPassword())
				) {
				//Validate email don't exist in DB 
				if (userService.getUserByEmail(user.getEmail()) != null) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuario ya existe en base de datos");
				}
				//Use of stream and lambda
				if(user.getPhones() != null) {
					user.getPhones().stream().forEach(
						phone -> LOGGER.info("Número a registrar : "+phone.getNumber())		
					);
				}
				//Save the user
				User response = userService.saveUser(user);
				return ResponseEntity.ok(response);	
			}else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Verifique que el campo 'email' o 'password' contenga información");

			}
		}catch(ResponseStatusException e) {
			LOGGER.warning(e.getMessage());
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		}
	}
}

