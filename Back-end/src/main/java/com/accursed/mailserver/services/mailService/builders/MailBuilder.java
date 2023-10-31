package com.accursed.mailserver.services.mailService.builders;

import com.accursed.mailserver.models.*;

import java.sql.Timestamp;
import java.util.Set;

public abstract class MailBuilder implements Builder{
    protected String mailFrom;
    protected String mailTo;
    protected String subject;
    protected String content;
    protected Timestamp date;
    protected String state;
    protected boolean isStarred;
    protected int priority;
    protected Set<Attachment> attachments;



    @Override
    public abstract void reset();

    @Override
    public MailBuilder setMailFrom(String from) {
        mailFrom = from;
        return this;
    }

    @Override
    public MailBuilder setMailTo(String to) {
        mailTo = to;
        return this;
    }

    @Override
    public MailBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    @Override
    public MailBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public MailBuilder setDate() {
        this.date = new Timestamp(System.currentTimeMillis());
        return this;
    }

    @Override
    public MailBuilder setState(String state) {
        this.state = state;
        return this;
    }

    @Override
    public MailBuilder setIsStarred(boolean isStarred) {
        this.isStarred = isStarred;
        return this;
    }

    @Override
    public MailBuilder setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public MailBuilder setAttachments(Set<Attachment> attachments){
        this.attachments = attachments;
        return this;
    }


    @Override
    public abstract Mail getResult();

}
