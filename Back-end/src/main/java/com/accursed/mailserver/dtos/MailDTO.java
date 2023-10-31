package com.accursed.mailserver.dtos;

import com.accursed.mailserver.models.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Timestamp;
import java.util.Set;

public class MailDTO {
    public String mailId;
    public String folderId;
    public String userId;
    public String id;
    public String from;
    public String to;
    public String subject;
    public String content;
    public Timestamp timestamp;
    public String state;
    public Boolean isStarred;
    public Integer priority;
    public Set<Attachment> attachments;
    public Set<MultipartFile> files;

}
