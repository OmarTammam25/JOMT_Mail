package com.accursed.mailserver.services.userService.authintications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChainFactory {
    @Autowired
    private UniqueEmail uniqueEmail;
    @Autowired
    private EmailExist emailExist;
    @Autowired
    private CorrectPassword correctPassword;

    public Handler getChain(String chain){
        uniqueEmail.setNext(new HasNumber(new LongEnough(null)));
        correctPassword.setNext(null);
        emailExist.setNext(correctPassword);
        switch (chain){
            case "registration": return new baseHandler(new ValidEmailString(uniqueEmail));
            case "login": return new baseHandler(emailExist);
            default: return null;
        }
    }
}
