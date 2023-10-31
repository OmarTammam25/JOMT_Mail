package com.accursed.mailserver.services.mailService.searching;

import com.accursed.mailserver.models.Contact;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.services.mailService.Filter.ContactNameCriteria;
import com.accursed.mailserver.services.mailService.Filter.SubjectCriteria;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SearchService {
    SubjectCriteria subjectCriteria = new SubjectCriteria();
    ContactNameCriteria contactCriteria = new ContactNameCriteria();

    public Set<Mail> searchBySubject(Set<Mail> mails, String searched){
        return subjectCriteria.meet(mails, searched);
    }

    public Set<Contact> searchContactByName(Set<Contact> contacts, String name){
        return contactCriteria.meet(contacts, name);
    }
}
