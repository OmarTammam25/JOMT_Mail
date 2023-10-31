package com.accursed.mailserver.controllers;


import com.accursed.mailserver.dtos.ContactDTO;
import com.accursed.mailserver.models.Contact;
import com.accursed.mailserver.services.contactService.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.Stack;

@RestController
@RequestMapping("/contact")
public class ContactController {
    //TODO CRUD operations for contact
    @Autowired
    ContactService contactService;

    @PostMapping("/add")
    public ContactDTO addContact (@RequestBody ContactDTO contactDTO){
        contactService.addContact(contactDTO);
        return contactDTO;
    }

    @GetMapping("/get/{id}")
    public Set<Contact> getContacts(@PathVariable String id){
        return contactService.getContacts(id);
    }


    @PutMapping("/update")
    public ContactDTO updateContact (@RequestBody ContactDTO contactDTO){
        contactService.updateContact(contactDTO);
        return contactDTO;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteContact (@PathVariable String id){
        contactService.deleteContact(id);
//        return contactDTO;
    }

    @GetMapping("/searchByName")
    public Set<Contact> searchContactByName(@RequestBody ContactDTO contactDTO){
        return contactService.searchContactByName(contactDTO);
    }
}