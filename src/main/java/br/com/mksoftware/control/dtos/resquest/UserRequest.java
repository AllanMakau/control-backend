package br.com.mksoftware.control.dtos.resquest;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.mksoftware.control.entities.Address;
import br.com.mksoftware.control.entities.Contact;
import br.com.mksoftware.control.entities.Department;
import br.com.mksoftware.control.entities.Officer;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserRequest {
	

	@JsonProperty(value = "codigo")
    private Long id;
	
	@NotBlank(message = "descrição não pode ser nulo")
	@JsonProperty(value = "nome")
	private String firstName;
	
	@NotBlank(message = "descrição não pode ser nulo")
	@JsonProperty(value = "sobrenome")
	private String lastName;
	
	@JsonProperty(value = "apelido")
	private String nickname;
	
	@NotBlank(message = "descrição não pode ser nulo")
	@JsonProperty(value = "email")
	private String mail;
	
	@NotBlank(message = "descrição não pode ser nulo")
	@JsonProperty(value = "cpf")
	private String socialNumber;
	
	@NotBlank(message = "descrição não pode ser nulo")
	@JsonProperty(value = "rg")
	private String identityDocument;
	
	@NotBlank(message = "descrição não pode ser nulo")
	@JsonProperty(value = "usuario")
	private String username;
	
	@NotBlank(message = "descrição não pode ser nulo")
	@JsonProperty(value = "senha")
	private String password;
	
	@NotBlank(message = "descrição não pode ser nulo")
	@JsonProperty(value = "data_aniversario")
	private OffsetDateTime birthDate;
	
	@JsonProperty(value = "ativo")
	private Boolean isActive;

	@JsonProperty(value = "cargo")
	private Officer officer;

	@JsonProperty(value = "departamento")
	private Department department;

	@JsonProperty(value = "endereco")
	private Address address;

	@JsonProperty(value = "contatos")
	private List<Contact> contactList;

}
