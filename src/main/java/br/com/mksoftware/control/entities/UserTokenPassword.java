package br.com.mksoftware.control.entities;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

//@Builder
@Entity
@Data
@Table(name = "TB_USER_TOKEN_PASSWORD")
public class UserTokenPassword  implements Serializable {
	
	
	public UserTokenPassword() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserTokenPassword(Long id, OffsetDateTime dateRegister, Long token, String mailUser, String identify) {
		super();
		this.id = id;
		this.dateRegister = dateRegister;
		this.token = token;
		this.mailUser = mailUser;
		this.identify = identify;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private OffsetDateTime dateRegister;
	
	private Long token;

	private String mailUser;
	
	private String identify;

}
