package br.com.mksoftware.control.services;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mksoftware.control.dtos.resquest.MailRequest;
import br.com.mksoftware.control.dtos.resquest.PasswordUpdateRequest;
import br.com.mksoftware.control.entities.Contact;
import br.com.mksoftware.control.entities.Function;
import br.com.mksoftware.control.entities.System;
import br.com.mksoftware.control.entities.User;
import br.com.mksoftware.control.entities.UserTokenPassword;
import br.com.mksoftware.control.exceptions.BusinessException;
import br.com.mksoftware.control.exceptions.UserNotFoundException;
import br.com.mksoftware.control.repository.FunctionRepository;
import br.com.mksoftware.control.repository.SystemRepository;
import br.com.mksoftware.control.repository.UserRespository;
import br.com.mksoftware.control.repository.UserTokenPasswordRespository;

@Service
public class UserService {

	
	@Autowired
	private UserRespository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private FunctionRepository functionRepository;
	
	@Autowired
	private SystemRepository systemRepository;
	
	@Autowired
	private UserTokenPasswordRespository userTokenPasswordRespository;
	

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
		
		
		if (this.emailExists(user.getMail()) || this.usernameExists(user.getUsername())) {
			throw new BusinessException(
					String.format("Já existe um usuário cadastrado com o e-mail: %s ou username: %s", user.getMail(), user.getUsername()));
		}
		
		if (user.isNew()) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		
		return userRepository.save(user);
	}

	@Transactional
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	@Transactional
	public User updatePasswordUser(PasswordUpdateRequest newPassword) {
		
		var user = this.getUserById(Long.parseLong(newPassword.getIdUser()));
		
		if(!newPassword.getNewPassword().equals(newPassword.getReNewPawword())) {
			throw new BusinessException(
					String.format("As senhas não coincidem."));
		}
		
		user.setPassword(newPassword.getNewPassword());
		
		return userRepository.save(user);
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
	
	public Boolean emailExists(String email) {
		return userRepository.existsByMail(email);
	}
	
	public Boolean usernameExists(String login) {
		return userRepository.existsByUsername(login);
	}

	public void generateTokenUpdatePassword(MailRequest mailRequest) {
		
		var user = userRepository.findByMail(mailRequest.getMail()).orElseThrow(() -> new UserNotFoundException(mailRequest.getMail().toString()));
		UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
		
		var userToken = UserTokenPassword.builder()
				.mailUser(user.getMail())
				.dateRegister(OffsetDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()))
				.identify(uuidAsString)
				.token("876009")
				.build();
		
		userTokenPasswordRespository.save(userToken);

	}

}
