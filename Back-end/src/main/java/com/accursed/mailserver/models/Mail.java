package com.accursed.mailserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
public abstract class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected String id;
    protected String mailFrom; // email
    protected String mailTo; // email
    @ManyToMany(mappedBy = "mails")
    @JsonIgnore
    protected Set<Folder> folders;
    @OneToMany(mappedBy = "mail")
    protected Set<Attachment> attachments;
    protected String subject;
    protected String content;
    protected Timestamp timestamp;
    protected String state;
    protected Boolean isStarred;
    protected Integer priority;

    public Mail(String mailFrom, String mailTo, String subject, String content,
                Timestamp timestamp, String state, boolean isStarred,
                int priority, Set<Attachment> attachments) {
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.subject = subject;
        this.content = content;
        this.timestamp = timestamp;
        this.state = state;
        this.isStarred = isStarred;
        this.priority = priority;
        this.attachments = attachments;
    }
    public void deleteFromFolder(Folder folder){
        folders.remove(folder);
    }

    public Mail() {}

//    public void addFolder(Folder folder) {
//        folders.add(folder);
//    }

//    public Mail() {
//
//    }

// private String[] attachments;
}
