package com.sofka.controller;

import com.sofka.dto.ContactDto;
import com.sofka.dto.Message;
import com.sofka.entity.Contact;
import com.sofka.service.ContactService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**esta clase permite darle la funcionalidad a el programa*/

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {
    @Autowired
    ContactService contactService;
    @GetMapping("/list")
    public ResponseEntity<List<Contact>>list(){
        List<Contact>list=contactService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    /**Permite buscar por id*/

    @GetMapping("/detail/{id}")
    public ResponseEntity<Contact> getById(@PathVariable("id") int id){
        if(!contactService.existsById(id))
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        Contact contact = contactService.getOne(id).get();
        return new ResponseEntity(contact, HttpStatus.OK);
    }
    /**Permite buscar por nombre*/
    @GetMapping("/detailname/{name}")
    public ResponseEntity<Contact> getById(@PathVariable("name") String name){
        if(!contactService.existsByName(name))
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        Contact contact = contactService.getByName(name).get();
        return new ResponseEntity(contact, HttpStatus.OK);
    }
    /**Permite crear un contacto*/
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ContactDto contactDto){
        if (StringUtils.isBlank(contactDto.getName()))
            return new ResponseEntity<>(new Message("El nombre es requerido"), HttpStatus.BAD_REQUEST);
        Contact contact= new Contact(contactDto.getName(), contactDto.getPhone(),contactDto.getEmail(),contactDto.getDateBirth());
        contactService.save(contact);
        return new ResponseEntity(new Message("Contacto creado"), HttpStatus.OK);
    }
    /**Permite Actualizar los contactos*/
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id,@RequestBody ContactDto contactDto){
        if(!contactService.existsById(id))
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        if(contactService.existsByName(contactDto.getName()) && contactService.getByName(contactDto.getName()).get().getId()!= id)
            return new ResponseEntity(new Message("el nombre ya existe"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(contactDto.getName()))
            return new ResponseEntity<>(new Message("El nombre es requerido"), HttpStatus.BAD_REQUEST);
        Contact contact= contactService.getOne(id).get();
        contact.setName(contactDto.getName());
        contact.setPhone(contactDto.getPhone());
        contact.setEmail(contactDto.getEmail());
        contact.setDateBirth(contactDto.getDateBirth());
        contactService.save(contact);
        return new ResponseEntity(new Message("Contacto Actualizado"), HttpStatus.OK);
    }
    /**Permite Eliminar los contactos*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!contactService.existsById(id))
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        contactService.delete(id);
        return new ResponseEntity(new Message("Contacto eliminado"), HttpStatus.OK);
    }


}
