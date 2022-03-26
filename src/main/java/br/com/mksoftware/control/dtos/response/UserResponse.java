package br.com.mksoftware.control.dtos.response;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class UserResponse {
	
	@JsonProperty(value = "codigo")
    private Long id;
	
	@JsonProperty(value = "nome")
	private String firstName;
	
	@JsonProperty(value = "sobrenome")
	private String lastName;
	
	@JsonProperty(value = "apelido")
	private String nickname;
	
	@JsonProperty(value = "email")
	private String mail;
	
	@JsonProperty(value = "cpf")
	private String socialNumber;
	
	@JsonProperty(value = "rg")
	private String identityDocument;
	
	@JsonProperty(value = "usuario")
	private String username;
	
	@JsonProperty(value = "data_aniversario")
	private OffsetDateTime birthDate;
	
	@JsonProperty(value = "ativo")
	private Boolean isActive;
	
	@JsonProperty(value = "data_registro")
	private OffsetDateTime dateRegistration;
	
	@JsonProperty(value = "data_atualizado")
	private OffsetDateTime dateUpdated;

	@JsonProperty(value = "cargo")
	private OfficerResponse officer;

	@JsonProperty(value = "departamento")
	private DepartamentoResponse department;

	@JsonProperty(value = "endereco")
	private AddressResponse address;

	@JsonProperty(value = "contatos")
	private List<ContactResponse> contactList;
	
	@JsonProperty(value = "perfis")
	private List<FunctionResponse> functionList;
	
	



}
