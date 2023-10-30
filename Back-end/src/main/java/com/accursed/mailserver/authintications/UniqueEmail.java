package com.accursed.mailserver.authintications;

import com.accursed.mailserver.dtos.UserDTO;
import com.accursed.mailserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmail extends baseHandler{
    @Autowired
    private UserService userService;
    public UniqueEmail(@Lazy Handler nextHandler) {
        super(nextHandler);
    }

    public boolean handle(UserDTO userDTO){
        if(!userService.emailExists(userDTO.email)){
            return super.handle(userDTO);
        }
        else{
            userDTO.requestState = false;
            userDTO.requestMessage = "the Email is already exist";
            return false;
        }
    }
}
