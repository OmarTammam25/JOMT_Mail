package com.accursed.mailserver.services.attachmentService;

import com.accursed.mailserver.models.Attachment;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.database.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;

    public Optional<Attachment> getAttachment(String id){
        return attachmentRepository.findById(id);
    }

    public List<Attachment> getAllFiles() {
        return (List<Attachment>) attachmentRepository.findAll();
    }

    public Attachment setAttachmentToMail(MultipartFile file, Mail mail) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Attachment attachment = new Attachment(fileName, file.getContentType(), file.getBytes(), mail);
        return attachment;
    }

}
