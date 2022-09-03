package br.com.mksoftware.control.dtos.resquest;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordUpdateRequest {
	
	
	@JsonProperty("codigo_usuario")
	private String idUser;
	
	@JsonProperty("email")
	private String mail;
	
	@JsonProperty("token")
	private Long token;
	
	@JsonProperty("nova_senha")
	@NotBlank
	private String newPassword;
	
	@JsonProperty("re_nova_senha")
	@NotBlank
	private String reNewPawword;
}
