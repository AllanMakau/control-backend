package br.com.mksoftware.control.dtos.resquest;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.mksoftware.control.enums.ContactTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactRequest {

	@JsonProperty(value = "codigo")
	private Long id;
	
	@NotBlank(message = "Tipo do contato não pode ser nulo")
	@JsonProperty(value = "tipo")
	private ContactTypeEnum type;
	
	@NotBlank(message = "ddd do contato não pode ser nulo")
	@JsonProperty(value = "ddd")
	private String ddd;
	
	@NotBlank(message = "numero do contato não pode ser nulo")
	@JsonProperty(value = "numero")
	private String number;

}
