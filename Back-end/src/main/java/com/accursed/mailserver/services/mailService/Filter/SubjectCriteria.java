package com.accursed.mailserver.services.mailService.Filter;

import com.accursed.mailserver.models.Mail;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class SubjectCriteria implements MailCriteria {

    public Set<Mail> meet(Set<Mail> mails, String subject) {
        return mails.stream().filter(mail ->mail.getSubject().contains(subject)).collect(toSet());
    }
}
