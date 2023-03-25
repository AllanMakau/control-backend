package br.com.mksoftware.control.dtos.resquest;


import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartmentRequest {
	
	@JsonProperty(value = "codigo")
	private String id;
	
	@NotBlank(message = "descrição não pode ser nulo")
	@JsonProperty(value = "nome")
	private String name;
	
	@NotBlank(message = "descrição não pode ser nulo")
	@JsonProperty(value = "descricao")
	private String description;
	
	@NotBlank(message = "ativo")
	@JsonProperty(value = "ativo")
	private String isActive;


}
