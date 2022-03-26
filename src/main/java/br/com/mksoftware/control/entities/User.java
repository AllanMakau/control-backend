package br.com.mksoftware.control.entities;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "TB_USER")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = true)
	private String nickname;
	
	@Column(nullable = false)
	private String mail;
	
	@Column(nullable = false)
	private String socialNumber;
	
	@Column(nullable = false)
	private String identityDocument;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private OffsetDateTime birthDate;
	
	@Column(nullable = false)
	private Boolean isActive;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dateRegistration;
	
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dateUpdated;

	@ManyToOne
	private Officer officer;

	@ManyToOne
	private Department department;

	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idUser")
	private List<Contact> contactList;

	@JoinTable(name = "tb_user_System", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "system_id"))
	@ManyToMany
	private Set<System> systemList = new HashSet<System>();

	@JoinTable(name = "tb_user_Function", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "function_id"))
	@ManyToMany
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
	
	public boolean addContact(Contact contact) {
		return this.getContactList().add(contact);
	}

	public boolean removeContact(Contact contact) {
		return this.getContactList().remove(contact);
	}

	public boolean isNew() {
		return this.getId() == null;
	}
	
	public void activate() {
		setIsActive(true);
	}
	
	public void inactivate() {
		setIsActive(false);
	}

}
