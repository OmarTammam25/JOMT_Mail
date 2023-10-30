package com.accursed.mailserver;

import com.accursed.mailserver.dtos.MailDTO;
import com.accursed.mailserver.dtos.UserDTO;
import com.accursed.mailserver.models.DraftMail;
import com.accursed.mailserver.models.ImmutableMail;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.models.User;
import com.accursed.mailserver.services.MailService;
import com.accursed.mailserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class controller {
    @Autowired
    UserService userService;
    @Autowired
    MailService mailService;
//
//    @PostMapping("/adduser")
//    public void addUser(@RequestBody UserDTO userDTO) {
//        userService.addNewUser(userDTO);
//    }
//
//    @GetMapping("/getuser/{id}")
//    public Optional<User> getuser(@PathVariable Long id) {
//        return userService.getByName(id);
//    }
//
//    @PostMapping("/addMail")
//    public ResponseEntity<Object> addMail(@RequestBody MailDTO mailDTO) {
//        ImmutableMail mail = mailService.sendMail(mailDTO);
//        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mail.getId()).toUri();
//        return ResponseEntity.created(location).build();
//    }
//
//    @PostMapping("/addDraft")
//    public ResponseEntity<Object> addDraft(@RequestBody MailDTO mailDTO){
//        DraftMail mail =  mailService.sendDraft(mailDTO);
//        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mail.getId()).toUri();
//        return ResponseEntity.created(location).build();
//    }
}
