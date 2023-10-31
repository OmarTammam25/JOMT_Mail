package com.accursed.mailserver.services.userService.authintications;

import com.accursed.mailserver.dtos.UserDTO;
import com.accursed.mailserver.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class EmailExist extends baseHandler{
    @Autowired
    private UserRepository userRepo;
    public EmailExist(@Lazy Handler nextHandler) {
        super(nextHandler);
    }
    public boolean handle(UserDTO userDTO){
        if(!userRepo.findByEmail(userDTO.email).isEmpty()){
            return super.handle(userDTO);
        }
        else{
            userDTO.requestState = false;
            userDTO.requestMessage = "Email is not exist";
            return false;
        }
    }
}
