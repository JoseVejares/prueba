package com.ingreso.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "phones", schema = "public")
public class Phone {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private UUID id;
	 @Column(name="number" , length = 50)
	 private String number;
	 @Column(name="city_code" , length = 50)
	 private String citycode;
	 @Column(name="country_code" , length = 50)
	 private String contrycode;
	 
}
