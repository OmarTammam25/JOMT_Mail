package com.accursed.mailserver.authintications;

import com.accursed.mailserver.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Configurable;


public interface Handler {
    void setNext(Handler handler);
    boolean handle(UserDTO userDTO);
}
