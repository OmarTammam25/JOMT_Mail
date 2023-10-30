package com.accursed.mailserver.authintications;

import com.accursed.mailserver.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Configurable;


public class ValidEmailString extends baseHandler{
    public ValidEmailString(Handler nextHandler) {
        super(nextHandler);
    }
    public boolean handle(UserDTO userDTO){
        String email = userDTO.email;
        if(email.length() > 10 && email.substring(email.length()-10).equals("@gmail.com")){
            return super.handle(userDTO);
        }
        else{
            userDTO.requestState = false;
            userDTO.requestMessage = "invalid Email";
            return false;
        }
    }
}
