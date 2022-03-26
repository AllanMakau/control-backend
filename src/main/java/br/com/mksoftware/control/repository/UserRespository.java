package br.com.mksoftware.control.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mksoftware.control.entities.User;

@Repository
public interface UserRespository extends JpaRepository<User, Long>{

	Optional<User> findByMail(String mail);

}
