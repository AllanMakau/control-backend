package br.com.mksoftware.control.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.mksoftware.control.enums.ContactTypeEnum;
import lombok.Data;


@Data
@Entity
public class Contact implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private ContactTypeEnum type;
	private String ddd;
	private String number;
	
	
	
	

}
