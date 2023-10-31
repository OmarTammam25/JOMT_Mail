package com.accursed.mailserver.database;

import com.accursed.mailserver.models.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {
}
