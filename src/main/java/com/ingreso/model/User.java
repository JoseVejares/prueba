package com.ingreso.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "users", schema = "public")
public class User {
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private UUID id;
	  @Column(name="name" , length = 50)
	  private String name;
	  @Column(name="email" ,unique=true, length = 50)
	  private String email;
	  @Column(name="password" , length = 50)
	  private String password;
	  @Column(name="created")
	  @CreationTimestamp
	  private Date created;
	  @Column(name="modified", updatable = true)
	  @Temporal(TemporalType.DATE)
	  private Date modified;
	  @Column(name="last_login")
	  @Temporal(TemporalType.DATE)
	  private Date lastLogin = new Date();
	  @Column(name="token")
	  @GeneratedValue( strategy = GenerationType.AUTO)
	  private UUID token= UUID.randomUUID();
	  @Column(name="isactive")
	  private boolean isActive = true;
	  @JoinTable(
           name = "users_phones",
           joinColumns = @JoinColumn(name = "FK_USER", nullable = false),
           inverseJoinColumns = @JoinColumn(name="FK_PHONE", nullable = false)
      )
      @ManyToMany(cascade = CascadeType.ALL)
      private List<Phone> phones;

}
