package com.accursed.mailserver.dtos;

import java.util.HashMap;

public class UserDTO {
    public boolean requestState;
    public String id;

    public String mailId;
    public String userName;
    public String email;
    public String password;
    public String requestMessage;

    public HashMap<String, String> folderNames;
}