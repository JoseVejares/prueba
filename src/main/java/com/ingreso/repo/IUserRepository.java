package com.ingreso.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingreso.model.User;

@Repository
public interface IUserRepository extends JpaRepository <User,UUID>{

	User getUserByEmail(String correo);

}
