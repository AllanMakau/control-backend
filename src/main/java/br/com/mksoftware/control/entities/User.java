package br.com.mksoftware.control.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;



@Data
@Entity
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String nickname;
	private String mail;
	private String socialNumber;
	private String identityDocument;
	private String username;
	private String password;
	private LocalDate birthDate;
	private Boolean isActive;
	
	private Officer officer;
	
	private Department departament;
	
	private Address address;
	
	private List<Contact> contactList;
	
	private List<System> systemList;
	
	private List<Function> functionList;
	
	
	
	

}
