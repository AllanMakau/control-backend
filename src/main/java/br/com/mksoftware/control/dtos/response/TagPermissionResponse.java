package br.com.mksoftware.control.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class TagPermissionResponse {
	
	@JsonProperty(value = "codigo")
	private String id;
	
	@JsonProperty(value = "nome")
	private String name;
	
	@JsonProperty(value = "descricao")
	private String description;
	
	@JsonProperty(value = "ativo")
	private Boolean isActive;

}
