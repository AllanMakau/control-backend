package br.com.mksoftware.control.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_USER")
public class User implements Serializable {

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
	private Department department;

	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idUser")
	private List<Contact> contactList;

	@JoinTable(name = "user_System", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "system_id"))
	@OneToMany
	private Set<System> systemList = new HashSet<System>();

	@JoinTable(name = "user_Function", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "function_id"))
	@OneToMany
	private Set<Function> functionList = new HashSet<Function>();

	
	public boolean addSystem(System system) {
		return getSystemList().add(system);
	}

	public boolean removeSystem(System system) {
		return getSystemList().remove(system);
	}

	public boolean addFunction(Function function) {
		return getFunctionList().add(function);
	}

	public boolean removeFunction(Function function) {
		return getFunctionList().remove(function);
	}

}
