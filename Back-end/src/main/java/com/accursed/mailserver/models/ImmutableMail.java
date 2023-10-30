package com.accursed.mailserver.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
public class ImmutableMail extends Mail {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
    public ImmutableMail(User mailFrom,
                         User mailTo,
                         String subject,
                         String content,
                         Timestamp timestamp,
                         String state,
                         boolean isStarred,
                         int priority) {
        super(mailFrom, mailTo, subject, content, timestamp, state, isStarred, priority);
    }

    public ImmutableMail() {
        super();
    }
}
