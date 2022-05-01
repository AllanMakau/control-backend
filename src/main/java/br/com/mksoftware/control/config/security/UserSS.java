package br.com.mksoftware.control.config.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserSS implements  UserDetails{ 

	
private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;

	

	
	public UserSS(br.com.mksoftware.control.entities.User user) {
		super();
		this.id = user.getId();
		this.email = user.getMail();
		this.senha = user.getPassword();
		
		this.authorities = user.getFunctionList().stream()
				.flatMap(function -> function.getTaglist().stream())
				.map(tag -> new SimpleGrantedAuthority(tag.getName().toUpperCase()))
				.collect(Collectors.toSet());
		
	}




	@Override
	public String getPassword() {
		return senha;
	}




	@Override
	public String getUsername() {
		return email;
	}




	@Override
	public boolean isAccountNonExpired() {
		return true;
	}




	@Override
	public boolean isAccountNonLocked() {
		return true;
	}




	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}




	@Override
	public boolean isEnabled() {
		return true;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}





}
