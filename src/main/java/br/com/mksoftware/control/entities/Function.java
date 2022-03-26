package br.com.mksoftware.control.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "TB_FUNCTION")
public class Function implements Serializable{
	
	
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
	private Boolean isActive = true;
	
	
	@ManyToOne()
	private System system;
	
	

	@ManyToMany
	@JoinTable(name = "function_tag", joinColumns = @JoinColumn(name = "function_id"),
			inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<TagPermission> taglist = new HashSet<TagPermission>();
	


	public boolean addTag(TagPermission tag) {
		return getTaglist().add(tag);
	}
	
	public boolean removeTag(TagPermission tag) {
		return getTaglist().remove(tag);
	}

	
	public void activate() {
		setIsActive(true);
	}
	
	public void inactivate() {
		setIsActive(false);
	}
 

	

}
