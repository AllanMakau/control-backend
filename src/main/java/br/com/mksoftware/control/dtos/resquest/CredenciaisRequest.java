package br.com.mksoftware.control.dtos.resquest;

import java.io.Serializable;

import lombok.Data;

@Data
public class CredenciaisRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;

}
