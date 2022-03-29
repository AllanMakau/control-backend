package br.com.mksoftware.control.entities;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Builder
@Entity
@Data
@Table(name = "TB_USER_TOKEN_PASSWORD")
public class UserTokenPassword {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private OffsetDateTime dateRegister;
	
	private String token;

	private String mailUser;
	
	private String identify;

}
