package br.com.mksoftware.control.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@ManyToOne
	private Officer officer;
	@ManyToOne
	private Department departament;
	@ManyToOne
	private Address address;
	
	@OneToMany
    @JoinColumn(name = "idUser")
	private List<Contact> contactList;
	
	
	@JoinTable(
		    name = "user_System",
		    joinColumns = @JoinColumn(name = "user_id"),
		    inverseJoinColumns = @JoinColumn(name = "system_id")
		)
	@OneToMany
	private List<System> systemList;
	
	//private List<Function> functionList;
	
	
	
	

}
