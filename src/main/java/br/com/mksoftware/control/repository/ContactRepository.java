package br.com.mksoftware.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mksoftware.control.entities.Contact;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
