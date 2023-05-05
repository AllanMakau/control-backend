package br.com.mksoftware.control.dtos.resquest;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserUpdateRequest {
	

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
	@JsonProperty(value = "registro")
	private String identityDocument;
	
	@NotBlank(message = "descrição não pode ser nulo")
	@JsonProperty(value = "data_aniversario")
	private OffsetDateTime birthDate;
	
	@JsonProperty(value = "ativo")
	private Boolean isActive;

	@JsonProperty(value = "cargo")
	private OfficerRequest officer;

	@JsonProperty(value = "departamento")
	private DepartmentRequest department;

	@JsonProperty(value = "endereco")
	private AddressRequest address;

	@JsonProperty(value = "contatos")
	private List<ContactRequest> contactList;
	
	

}
