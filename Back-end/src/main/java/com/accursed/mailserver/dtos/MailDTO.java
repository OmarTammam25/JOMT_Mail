package com.accursed.mailserver.dtos;

import java.sql.Timestamp;

public class MailDTO {
    public String id;
    public String from;
    public String to;
    public String subject;
    public String content;
    public Timestamp timestamp;
    public String state;
    public Boolean isStarred;
    public Integer priority;
}
