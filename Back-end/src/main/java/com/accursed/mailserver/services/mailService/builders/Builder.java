package com.accursed.mailserver.services.mailService.builders;

import com.accursed.mailserver.models.Attachment;
import com.accursed.mailserver.models.Mail;

import java.util.Set;

public interface Builder {
    void reset();
    MailBuilder setMailFrom(String from);
    MailBuilder setMailTo(String to);
    MailBuilder setSubject(String subject);
    MailBuilder setContent(String content);
    MailBuilder setDate();
    MailBuilder setState(String state);
    MailBuilder setIsStarred(boolean isStarred);
    MailBuilder setPriority(int priority);
    MailBuilder setAttachments(Set<Attachment> attachments);
//    MailBuilder setSenderEmail(String senderEmail);
//    MailBuilder setReceiverEmail(String receiverEmail);

    Mail getResult();
}
