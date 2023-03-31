package br.com.mksoftware.control.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.mksoftware.control.enums.ContactTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Table(name = "TB_CONTACT")
public class Contact implements Serializable {

	/**
	 * O
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private ContactTypeEnum type;
	private String ddd;
	private String number;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private User user;

	public Contact(Long id, ContactTypeEnum type, String ddd, String number) {
		super();
		this.id = id;
		this.type = type;
		this.ddd = ddd;
		this.number = number;
	}
	
	public Contact() {
		super();
	}
	
	
}
