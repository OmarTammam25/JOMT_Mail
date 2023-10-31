package com.accursed.mailserver.services.userService.authintications;

import com.accursed.mailserver.dtos.UserDTO;


public class HasSpecialChar extends baseHandler{
    public HasSpecialChar(Handler nextHandler) {
        super(nextHandler);
    }
    public boolean handle(UserDTO userDTO){
        boolean hasSpecialChar = false;
        for(char i: userDTO.password.toCharArray()){
            hasSpecialChar = !(Character.isSpaceChar(i)||Character.isLetterOrDigit(i));
        }
        if(hasSpecialChar){
            return super.handle(userDTO);
        }
        else{
            userDTO.requestState = false;
            userDTO.requestMessage = "The password must contain at least one special character";
            return false;
        }
    }
}
