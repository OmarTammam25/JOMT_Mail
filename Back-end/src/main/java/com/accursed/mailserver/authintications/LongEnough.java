package com.accursed.mailserver.authintications;

import com.accursed.mailserver.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Configurable;


public class LongEnough extends baseHandler{
    public LongEnough(Handler nextHandler) {
        super(nextHandler);
    }
    public boolean handle(UserDTO userDTO){
        if(userDTO.password.length()>=8){
            return super.handle(userDTO);
        }
        else{
            userDTO.requestState = false;
            userDTO.requestMessage = "the password is too short, it must be more than 8 characters";
            return false;
        }
    }
}
