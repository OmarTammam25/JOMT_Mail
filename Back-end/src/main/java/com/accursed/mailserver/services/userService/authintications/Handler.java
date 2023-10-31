package com.accursed.mailserver.services.userService.authintications;

import com.accursed.mailserver.dtos.UserDTO;


public interface Handler {
    void setNext(Handler handler);
    boolean handle(UserDTO userDTO);
}