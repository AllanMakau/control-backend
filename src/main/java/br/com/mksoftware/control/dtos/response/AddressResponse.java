package br.com.mksoftware.control.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class AddressResponse {
	
	@JsonProperty(value = "cidade")
	private String city;
	
	@JsonProperty(value = "bairro")
	private String district;
	
	@JsonProperty(value = "estado")
	private String state;
	
	@JsonProperty(value = "cep")
	private String zipCode;
	
	@JsonProperty(value = "rua")
	private String street;
	
	@JsonProperty(value = "pais")
	private String country;
	
	@JsonProperty(value = "numero")
	private String number;
	
	@JsonProperty(value = "complemento")
	private String complement;

}
