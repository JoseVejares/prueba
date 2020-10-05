package com.ingreso.test.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import com.ingreso.controller.UserController;
import com.ingreso.model.Phone;
import com.ingreso.model.User;
import com.ingreso.service.UserService;
import com.ingreso.utils.ValidacionCampos;

@SpringBootTest
@ActiveProfiles("test")
public class UserControllerTest {
	
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;
    
    @Mock
    ValidacionCampos validacionCampos;
    
    User user = new User();
	Phone phone = new Phone();
	List<Phone> phones = new ArrayList<>();

	@BeforeEach
	public void before() {
		
		user.setName("name");
		user.setPassword("Jvdfas12");
		user.setEmail("email@domain.com");
		
		phone.setNumber("12345678");
		phone.setCitycode("34");
		phone.setContrycode("325");
		phones.add(phone);
		user.setPhones(phones);
	}
    
    @Test
    public void createUserOk() {
    	phone.setNumber(null);
        Mockito.when(userService.saveUser(user)).thenReturn(new User());
        Mockito.when(userService.getUserByEmail(anyString())).thenReturn(null);
        Mockito.when(validacionCampos.validateEmailAndPassword(anyString(), anyString()))
        .thenReturn(true);
        Mockito.when(userService.saveUser(user)).thenReturn(new User());
        ResponseEntity<User> resp = userController.saveUser(user);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    
    @Test
    public void createUserOkWithPhone() {
    	phone.setNumber("123456");
        Mockito.when(userService.saveUser(user)).thenReturn(new User());
        Mockito.when(userService.getUserByEmail(anyString())).thenReturn(null);
        Mockito.when(userService.saveUser(user)).thenReturn(new User());
        ResponseEntity<User> resp = userController.saveUser(user);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    
    @Test
    public void createUserExistsEmail() {
        Mockito.when(userService.saveUser(user)).thenReturn(new User());
        Mockito.when(userService.getUserByEmail(anyString())).thenReturn(user);
        Assertions.assertThrows(ResponseStatusException.class,
				() -> userController.saveUser(user));

    }
    
    @Test
    public void createUserInvalidEmailAndPassword() {
    	user.setEmail(null);
    	user.setPassword(null);
        Mockito.when(validacionCampos.validateEmailAndPassword(anyString(), anyString()))
        .thenReturn(false);
        Assertions.assertThrows(ResponseStatusException.class,
				() -> userController.saveUser(user));

    }
    
    

}
