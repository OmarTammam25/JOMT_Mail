package com.accursed.mailserver.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Setter
public class DraftMail extends Mail {

    public DraftMail(User from,
                     User to,
                     String subject,
                     String content,
                     Timestamp timestamp,
                     String state,
                     boolean isStarred,
                     int priority,
                     String senderID,
                     String receiverID) {
        super(from, to, subject, content, timestamp, state, isStarred, priority);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMailFrom(User mailFrom) {
        this.mailFrom = mailFrom;
    }

    public void setMailTo(User mailTo) {
        this.mailTo = mailTo;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStarred(boolean starred) {
        isStarred = starred;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    public DraftMail() {
        super();

    }
}
