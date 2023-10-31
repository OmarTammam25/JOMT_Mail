package com.accursed.mailserver.controllers;

import com.accursed.mailserver.dtos.MailDTO;
import com.accursed.mailserver.models.*;
import com.accursed.mailserver.services.mailService.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/draft")
public class DraftController {

    @Autowired
    MailService mailService;

    @PostMapping("/post")
    public ResponseEntity<Object> postDraft(@RequestParam("mail") String jsonRequest, @RequestParam(value = "file", required = false) MultipartFile[] files){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MailDTO mailDTO = objectMapper.readValue(jsonRequest, MailDTO.class);
            DraftMail mail =  mailService.postDraft(mailDTO, files);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mail.getId()).toUri();
            mailDTO.mailId = mail.getId();
            return ResponseEntity.created(location).body(mailDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    /**
     * gets draft from database, converts it to an immutable mail, and then deletes draft from database
     */
    @PostMapping("/send")
    public ResponseEntity<Object> sendDraft(@RequestBody MailDTO mailDTO){
        try {
            ImmutableMail mail =  mailService.sendDraft(mailDTO);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mail.getId()).toUri();
            mailDTO.mailId = mail.getId();
            return ResponseEntity.created(location).body(mailDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> UpdateDraft(@RequestParam("mail") String jsonRequest, @RequestParam("file")MultipartFile[] files){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            MailDTO mailDTO = objectMapper.readValue(jsonRequest, MailDTO.class);
            mailService.updateDraft(mailDTO, files);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getDraft(@RequestBody MailDTO mailDTO) {
        Optional<Mail> mail = mailService.getDraft(mailDTO);
        if (mail.isPresent())
            return ResponseEntity.ok(mail);
        else
            return ResponseEntity.badRequest().body("Couldn't find draft with this id");
    }

    @DeleteMapping("/delete")
    public void deleteDraft(@RequestBody MailDTO mailDTO) {
        mailService.deleteDraft(mailDTO);
    }
}
