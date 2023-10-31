package com.accursed.mailserver.database;

import com.accursed.mailserver.models.Mail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends CrudRepository<Mail, String> {
}
