package com.accursed.mailserver.services.userService.authintications;

import com.accursed.mailserver.dtos.UserDTO;
import com.accursed.mailserver.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CorrectPassword extends baseHandler{
    @Autowired
    private UserRepository userRepo;

    public CorrectPassword(@Lazy Handler nextHandler) {
        super(nextHandler);
    }
    public boolean handle(UserDTO userDTO){
        if(userRepo.findByEmail(userDTO.email).get(0).getPassword().equals(userDTO.password)){
            return super.handle(userDTO);
        }
        else{
            userDTO.requestState = false;
            userDTO.requestMessage = "the password is incorrect";
            return false;
        }
    }
}
