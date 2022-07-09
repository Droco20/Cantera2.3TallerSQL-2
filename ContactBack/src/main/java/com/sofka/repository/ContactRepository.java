package com.sofka.repository;

import com.sofka.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/** Permite introducir toda la funcionalidad necesaria en la interfaz sin tener que declarar métodos adicionales*/
@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {
    Optional<Contact> findByName(String name);
    boolean existsByName(String name);



}
