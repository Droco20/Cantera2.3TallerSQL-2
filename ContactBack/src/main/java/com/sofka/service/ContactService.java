package com.sofka.service;

import com.sofka.entity.Contact;
import com.sofka.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
/**
 establece las operaciones en la base de datos
 */
@Service
@Transactional
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public List<Contact> list(){
        return contactRepository.findAll();
    }
     public Optional<Contact> getOne(int id){
        return contactRepository.findById(id);
     }

    public Optional<Contact> getByName(String name){
        return contactRepository.findByName(name);
    }

    public void  save(Contact contact){
        contactRepository.save(contact);
    }

    public void delete(int id){
        contactRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return contactRepository.existsById(id);
    }

    public boolean existsByName(String name){
        return contactRepository.existsByName(name);
    }

}
