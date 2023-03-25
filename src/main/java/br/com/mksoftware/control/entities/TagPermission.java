package br.com.mksoftware.control.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Table(name = "TB_TAGPERMISSION")
public class TagPermission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Boolean isActive;
	
	public TagPermission() {
		super();
	}
	
	public void activate() {
		setIsActive(true);
	}
	
	public void inactivate() {
		setIsActive(false);
	}

	

	

}
