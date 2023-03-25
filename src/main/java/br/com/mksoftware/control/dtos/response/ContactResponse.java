package br.com.mksoftware.control.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.mksoftware.control.enums.ContactTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactResponse {
	
	
	@JsonProperty(value = "codigo")
	private Long id;
	
	@JsonProperty(value = "tipo")
	private ContactTypeEnum type;
	
	@JsonProperty(value = "ddd")
	private String ddd;
	
	@JsonProperty(value = "numero")
	private String number;

}
