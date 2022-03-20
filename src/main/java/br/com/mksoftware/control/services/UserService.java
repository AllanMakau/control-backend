package br.com.mksoftware.control.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mksoftware.control.entities.User;
import br.com.mksoftware.control.repository.UserRespository;

@Service
public class UserService {

	
	@Autowired
	private UserRespository userRepository;
	

	public List<User> getAll() {
		var users = userRepository.findAll();
		return users;
	}

	@Transactional
	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}

	@Transactional
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
	
	@Transactional
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Transactional
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Transactional
	public User activate(Long id, Boolean ativo) {
		User userOld = userRepository.findById(id).get();
		userOld.setIsActive(ativo);
		User userUpdated = userRepository.save(userOld);
		return userUpdated;
	}

}
