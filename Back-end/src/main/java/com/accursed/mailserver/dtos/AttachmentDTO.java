package com.accursed.mailserver.dtos;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AttachmentDTO {
    public String id;
    public String draftId;
    public String name;
    public String url;
    public String type;
    public long size;
}
