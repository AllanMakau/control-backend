package br.com.mksoftware.control.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mksoftware.control.config.security.UserSS;
import br.com.mksoftware.control.entities.User;
import br.com.mksoftware.control.repository.UserRespository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRespository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByMail(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserSS(user.get());

	}

}
