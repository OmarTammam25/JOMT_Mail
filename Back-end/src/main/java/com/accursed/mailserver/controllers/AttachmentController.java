package com.accursed.mailserver.controllers;

import com.accursed.mailserver.database.DataHandler;
import com.accursed.mailserver.dtos.AttachmentDTO;
import com.accursed.mailserver.dtos.ResponseFile;
import com.accursed.mailserver.models.Attachment;
import com.accursed.mailserver.database.MailRepository;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.services.attachmentService.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private MailRepository mailRepository;
    @Autowired
    private DataHandler dataHandler;

//    @GetMapping("/attachment/get/{id}")
//    public Optional<Attachment> getAttachment(@PathVariable String id){
//        return attachmentService.getAttachment(id);
//    }

    @GetMapping("/files/{mailId}")
    public ResponseEntity<Set<ResponseFile>> getListFiles(@PathVariable String mailId) {
        Mail mail = dataHandler.getMailByMailId(mailId);
        Set<Attachment> files = mail.getAttachments();
        Set<ResponseFile> responseFiles = new HashSet<>();
        for(Attachment i : files){
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/attachment/get/")
                    .path(i.getId())
                    .toUriString();
            responseFiles.add(new ResponseFile(
                    i.getName(),
                    fileDownloadUri,
                    i.getType(),
                    i.getData().length));
        }

//                .map(dbFile -> {
//            String fileDownloadUri = ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path("/files/")
//                    .path(dbFile.getId())
//                    .toUriString();
//
//            return new ResponseFile(
//                    dbFile.getName(),
//                    fileDownloadUri,
//                    dbFile.getType(),
//                    dbFile.getData().length);
//        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(responseFiles);
    }
    @GetMapping("/attachment/get/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable String id) {
        Optional<Attachment> attachment = attachmentService.getAttachment(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.get().getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.get().getName() + "\"")
                .body(new ByteArrayResource(attachment.get().getData()));
    }

//    @PostMapping("/attachment/post")
//    public void postAttachment(@RequestParam("mail") String jsonRequest, @RequestParam("file")MultipartFile[] files){
//        try{
//            ObjectMapper objectMapper = new ObjectMapper();
//            AttachmentDTO attachmentDTO = objectMapper.readValue(jsonRequest, AttachmentDTO.class);
//            for(MultipartFile file : files) {
//                attachmentService.setAttachmentToMail(files, mailRepository.findById(attachmentDTO.draftId))
//            }
//            ImmutableMail mail = attachmentService.setAttachmentToMail(files, mailRepository.findById(attachmentDTO.draftId));
//            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mail.getId()).toUri();
//            return ResponseEntity.created(location).build();
//        } catch(Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
}
