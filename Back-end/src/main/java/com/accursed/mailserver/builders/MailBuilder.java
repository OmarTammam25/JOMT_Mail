package com.accursed.mailserver.builders;

import com.accursed.mailserver.models.DraftMail;
import com.accursed.mailserver.models.ImmutableMail;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.models.User;

import java.sql.Time;
import java.sql.Timestamp;

public abstract class MailBuilder implements Builder{
    protected User mailFrom;
    protected User mailTo;
    protected String subject;
    protected String content;
    protected Timestamp date;
    protected String state;
    protected boolean isStarred;
    protected int priority;
    protected String senderID;
    protected String receiverID;
//    private String[] attachments;



    @Override
    public abstract void reset();

    @Override
    public void setMailFrom(User from) {
        mailFrom = from;
    }

    @Override
    public void setMailTo(User to) {
        mailTo = to;
    }

    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void setDate() {
        this.date = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public void setIsStarred(boolean isStarred) {
        this.isStarred = isStarred;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }


    @Override
    public abstract Mail getResult();

}
