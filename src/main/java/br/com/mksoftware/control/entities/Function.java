package br.com.mksoftware.control.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Function implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Boolean isActive;
	
	

	@ManyToMany(cascade  = {CascadeType.ALL})
	@JoinTable(name = "function_tag", joinColumns = @JoinColumn(name = "function_id"),
			inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<TagPermission> taglist = new HashSet<TagPermission>();
	


	public boolean addTag(TagPermission tag) {
		return getTaglist().add(tag);
	}
	
	public boolean removeTag(TagPermission tag) {
		return getTaglist().remove(tag);
	}

 

	

}
