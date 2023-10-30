package com.accursed.mailserver.authintications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

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
