package com.accursed.mailserver.models;

import jakarta.persistence.*;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Setter
public class DraftMail extends Mail {

    public DraftMail(String from,
                     String to,
                     String subject,
                     String content,
                     Timestamp timestamp,
                     String state,
                     boolean isStarred,
                     int priority,
                     Set<Attachment> attachment) {
        super(from, to, subject, content, timestamp, state, isStarred, priority, attachment);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public void setMailTo(String mailTo) {
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

    public void setAttachments(Set<Attachment> attachments){
        this.attachments = attachments;
    }

    public  void setFolders(Set<Folder> folders){
        this.folders = folders;
    }

    public DraftMail() {
        super();

    }
}
