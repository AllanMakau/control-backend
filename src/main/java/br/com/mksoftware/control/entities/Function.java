package br.com.mksoftware.control.entities;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
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
	private Boolean isActive;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_function_tag", joinColumns = @JoinColumn(name = "function_id"),
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
