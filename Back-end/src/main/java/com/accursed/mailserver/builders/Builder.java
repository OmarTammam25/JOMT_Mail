package com.accursed.mailserver.builders;

import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.models.User;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import java.sql.Timestamp;

public interface Builder {
    void reset();
    void setMailFrom(User from);
    void setMailTo(User to);
    void setSubject(String subject);
    void setContent(String content);
    void setDate();
    void setState(String state);
    void setIsStarred(boolean isStarred);
    void setPriority(int priority);
    Mail getResult();
}
