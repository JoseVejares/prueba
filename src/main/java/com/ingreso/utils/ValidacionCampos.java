package com.ingreso.utils;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidacionCampos {
	private static final Logger LOGGER = Logger.getLogger( ValidacionCampos.class.getName() );
	
	public final Pattern EMAIL_FORMAT =  Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	public final Pattern PASSWORD_FORMAT =  Pattern.compile("^(?=\\w*\\d{2})(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$");
	
	public boolean validateEmail(String email) {
	        Matcher matcher = EMAIL_FORMAT.matcher(email);
	        if( matcher.find()) {
	        	return true;
	        }else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Formato de correo inválido");
	        }
	}
	
	public boolean validatePassword(String password) {
        Matcher matcher = PASSWORD_FORMAT.matcher(password);
        if (matcher.find()) {
        	return true;
        }else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Formato de contraseña inválido ,"
					+ " debe contener una letra mayúscula, letras minúsculas, y dos números ");
        }
	}
	
    public  boolean validateEmailAndPassword(String email,String password) {
    	LOGGER.info(" ValidacionCampos.validateEmailAndPassword email : "+email +", password : "+ password);
   	 	return (validateEmail(email) && validatePassword(password));
    }

}
