package com.accursed.mailserver.controllers;

import com.accursed.mailserver.authintications.ChainFactory;
import com.accursed.mailserver.dtos.UserDTO;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ChainFactory chainFactory;
    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO userDTO) throws Exception {
         if(chainFactory.getChain("registration").handle(userDTO)) {
             userService.register(userDTO);
         }
        return userDTO;
    }
    @PostMapping("/login")
    public UserDTO login(@RequestBody UserDTO userDTO) throws Exception {
        if(chainFactory.getChain("login").handle(userDTO)) {
            userService.login(userDTO);
        }
        return userDTO;
    }
    @PostMapping("/inbox")
    public List<Mail> getinbox(@RequestBody UserDTO userDTO){
        return userService.getinbox(userDTO);
    }
    @PostMapping("/sentmails")
    public Set<Mail> getSentMails(@RequestBody UserDTO userDTO){
        return userService.getSentMails(userDTO);
    }
    @PostMapping("/receivedmails")
    public Set<Mail> getreceivedMails(@RequestBody UserDTO userDTO){
        return userService.getreceivedMails(userDTO);
    }
    @PostMapping("/deletemail")
    public String deletemail(@RequestBody UserDTO userDTO){
        return userService.deletemail(userDTO);
    }
}
