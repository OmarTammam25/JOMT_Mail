package com.accursed.mailserver.services.contactService;

import com.accursed.mailserver.database.DataHandler;
import com.accursed.mailserver.dtos.ContactDTO;

import com.accursed.mailserver.dtos.MailMapper;
import com.accursed.mailserver.models.Contact;

import com.accursed.mailserver.models.User;
import com.accursed.mailserver.database.ContactRepository;
import com.accursed.mailserver.database.UserRepository;
import com.accursed.mailserver.services.mailService.searching.SearchService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ContactService {

    @Autowired
    private ContactRepository contactRepo;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DataHandler dataHandler;

    @Autowired
    private SearchService searchService;

    private MailMapper mailMapper = Mappers.getMapper(MailMapper.class);

    public void addContact(ContactDTO contactDTO){
        Contact contact = Contact.getInstance(contactDTO);
        Optional<User> user = dataHandler.getUserByUserId(contactDTO.userId);
        contact.setUser(user.get());
        dataHandler.saveContactToTable(contact);
        contactDTO.id = contact.getId();
    }

    public Set<Contact> getContacts(String userId){
        User user = dataHandler.getUserByUserId(userId).get();
        return user.getContacts();
    }

    public void updateContact(ContactDTO contactDTO) {
        Optional<Contact> m = contactRepo.findById(contactDTO.id);
        if(m.isPresent()) {
            mailMapper.updateContactFromDto(contactDTO, (Contact) m.get());
            contactRepo.save(m.get());
        }
    }

    public void deleteContact(String  contactId){
        contactRepo.deleteById(contactId);
    }


    public Set<Contact> searchContactByName(ContactDTO contactDTO){
        Optional<User> user = dataHandler.getUserByUserId(contactDTO.userId);
        Set<Contact> contacts = user.get().getContacts();
        return searchService.searchContactByName(contacts, contactDTO.name);
    }

}
