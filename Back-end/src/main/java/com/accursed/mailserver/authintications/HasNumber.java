package com.accursed.mailserver.authintications;

import com.accursed.mailserver.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Configurable;


public class HasNumber extends baseHandler{
    public HasNumber(Handler nextHandler) {
        super(nextHandler);
    }
    public boolean handle(UserDTO userDTO){
        boolean hasNumber = false;
        for(char i: userDTO.password.toCharArray()){
            hasNumber = Character.isDigit(i);
        }
        if(hasNumber){
            return super.handle(userDTO);
        }
        else{
            userDTO.requestState = false;
            userDTO.requestMessage = "The password must contain at least one number";
            return false;
        }
//        return true;
    }
}
