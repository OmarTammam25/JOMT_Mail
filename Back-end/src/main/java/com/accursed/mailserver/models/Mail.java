package com.accursed.mailserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
public abstract class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected String id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    protected User mailFrom;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    protected User mailTo;
    protected String subject;
    protected String content;
    protected Timestamp timestamp;
    protected String state;
    protected Boolean isStarred;
    protected Integer priority;

    public Mail(User mailFrom, User mailTo, String subject, String content, Timestamp timestamp, String state, boolean isStarred, int priority) {
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.subject = subject;
        this.content = content;
        this.timestamp = timestamp;
        this.state = state;
        this.isStarred = isStarred;
        this.priority = priority;
    }

    public Mail() {

    }

//    public Mail() {
//
//    }

// private String[] attachments;
}
