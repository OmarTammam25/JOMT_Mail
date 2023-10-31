package com.accursed.mailserver.services.userService.authintications;

import com.accursed.mailserver.dtos.UserDTO;


public class baseHandler implements Handler{
    private Handler nextHandler = null;

    public baseHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void setNext(Handler handler) {
        nextHandler = handler;
    }

    @Override
    public boolean handle(UserDTO userDTO) {
        if(nextHandler == null){
            userDTO.requestState = true;
            return true;
        }
        else{
            return nextHandler.handle(userDTO);
        }
    }
}
