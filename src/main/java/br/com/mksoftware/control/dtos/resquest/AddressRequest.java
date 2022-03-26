package br.com.mksoftware.control.dtos.resquest;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressRequest {
	

	@JsonProperty(value = "cidade")
	private String city;
	

	@JsonProperty(value = "bairro")
	private String district;
	

	@JsonProperty(value = "estado")
	private String state;
	
	@NotBlank(message = "cep do endereço não pode ser nulo")
	@JsonProperty(value = "cep")
	private String zipCode;
	
	@JsonProperty(value = "rua")
	private String street;
	

	@JsonProperty(value = "pais")
	private String country;
	
	@NotBlank(message = "numero do endereço não pode ser nulo")
	@JsonProperty(value = "numero")
	private String number;
	
	@JsonProperty(value = "complemento")
	private String complement;

}
