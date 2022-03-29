package br.com.mksoftware.control.dtos.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailRequest {
	
	
	@JsonProperty(value = "email")
	private String mail;

}
