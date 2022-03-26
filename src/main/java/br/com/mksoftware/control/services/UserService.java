package br.com.mksoftware.control.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mksoftware.control.entities.Contact;
import br.com.mksoftware.control.entities.Function;
import br.com.mksoftware.control.entities.System;
import br.com.mksoftware.control.entities.User;
import br.com.mksoftware.control.exceptions.BusinessException;
import br.com.mksoftware.control.exceptions.UserNotFoundException;
import br.com.mksoftware.control.repository.ContactRepository;
import br.com.mksoftware.control.repository.FunctionRepository;
import br.com.mksoftware.control.repository.SystemRepository;
import br.com.mksoftware.control.repository.UserRespository;

@Service
public class UserService {

	
	@Autowired
	private UserRespository userRepository;
	
	//@Autowired
	//private PasswordEncoder passwordEncoder;
	
	@Autowired
	private FunctionRepository functionRepository;
	
	@Autowired
	private SystemRepository systemRepository;
	
	@Autowired
	private ContactRepository contctRespository;
	

	public List<User> getAll() {
		var users = userRepository.findAll();
		return users;
	}

	@Transactional
	public User getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
	}

	@Transactional
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
	
	@Transactional
	public User saveUser(User user) {
		
		Optional<User> userExist = userRepository.findByMail(user.getMail());
		
		if (userExist.isPresent() && !userExist.get().equals(user)) {
			throw new BusinessException(
					String.format("Já existe um usuário cadastrado com o e-mail %s", user.getMail()));
		}
		
		if (user.isNew()) {
			//user.setSenha(passwordEncoder.encode(usuario.getSenha()));
		}
		
		return userRepository.save(user);
	}

	@Transactional
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	@Transactional
	public void updatePassword(Long userId, String oldPassword, String newPassword) {
		//User user = getUserById(userId);
		
		/*
		 * if (!passwordEncoder.matches(oldPassword, user.getPassword())) { throw new
		 * BusinessException("Senha atual informada não coincide com a senha do usuário."
		 * ); }
		 * 
		 * user.setPassword(passwordEncoder.encode(newPassword));
		 */

	}

	@Transactional
	public User activate(Long id) {
		User user = userRepository.findById(id).get();
		user.activate();
		return user;
	}
	
	@Transactional
	public User inactivate(Long id) {
		User user = userRepository.findById(id).get();
		user.inactivate();
		return user;
	}

	@Transactional
	public User addSystem(Long id, System system) {
		User user = userRepository.findById(id).get();
		System sys = systemRepository.getById(system.getId());
		user.addSystem(sys);
		return user;
	}
	
	@Transactional
	public User removeSystem(Long id, System system) {
		User user = userRepository.findById(id).get();
		System sys = systemRepository.getById(system.getId());
		user.removeSystem(sys);
		return user;
	}

	@Transactional
	public User addFunction(Long id, Function function) {
		User user = userRepository.findById(id).get();
		Function fun = functionRepository.getById(function.getId());
		user.addFunction(fun);
		return user;
	}

	@Transactional
	public User removeFunction(Long id, Function function) {
		User user = userRepository.findById(id).get();
		Function fun = functionRepository.getById(function.getId());
		user.removeFunction(fun);
		return user;
	}
	
	@Transactional
	public User addContact(Long id, Contact contact) {
		User user = userRepository.findById(id).get();
		user.getContactList().add(contact);
		return user;
	}

	@Transactional
	public User removeContact(Long id, Contact contact) {
		User user = userRepository.findById(id).get();
		user.getContactList().remove(contact);
		return user;
	}

}
