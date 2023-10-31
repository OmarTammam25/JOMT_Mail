package com.accursed.mailserver.services.mailService.Filter;

import com.accursed.mailserver.models.Contact;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class ContactNameCriteria implements ContactCriteria {


    public Set<Contact> meet(Set<Contact> data, String searched) {
        return data.stream().filter(contact -> contact.getName().contains(searched)).collect(toSet());
    }
}
