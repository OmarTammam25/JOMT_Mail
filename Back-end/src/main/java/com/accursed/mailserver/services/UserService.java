package com.accursed.mailserver.services;

import com.accursed.mailserver.dtos.MailDTO;
import com.accursed.mailserver.dtos.MailMapper;
import com.accursed.mailserver.dtos.UserDTO;
import com.accursed.mailserver.models.DraftMail;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.models.User;
import com.accursed.mailserver.repositories.MailRepository;
import com.accursed.mailserver.repositories.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Set;

@RestController
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private MailRepository mailRepo;
    //TODO that is weird i don't know why should it be mailMapper can you change it to user mapper
    private MailMapper userMapper = Mappers.getMapper(MailMapper.class);
    public void register(UserDTO userDTO) throws Exception {
            userRepo.save(userMapper.updateUserFromDto(userDTO, (User) new User()));
    }
    public UserDTO login(UserDTO userDTO) throws Exception {
            userDTO.id = userRepo.findByEmail(userDTO.email).get(0).getId();
            return userDTO;
    }
    public boolean emailExists(String email){
        List<User> users = userRepo.findByEmail(email);
        return !users.isEmpty();
    }
    public List<User> getByEmail(String email){
        return userRepo.findByEmail(email);
    }
    //TODO :This for testing you can remove it and do it better
    public List<Mail> getinbox(UserDTO userDTO){
        List<Mail> inbox = new ArrayList<>();
        inbox.addAll(userRepo.findById(userDTO.id).get().getSentMails());
        inbox.addAll(userRepo.findById(userDTO.id).get().getReceivedMails());
        return inbox;
    }
    public Set<Mail> getSentMails(UserDTO userDTO){
        return userRepo.findById(userDTO.id).get().getSentMails();
    }
    // TODO rename to camelCase
    public Set<Mail> getreceivedMails(UserDTO userDTO){
        return userRepo.findById(userDTO.id).get().getReceivedMails();
    }

    public String deletemail(UserDTO userDTO){
        User user = userRepo.findById(userDTO.id).get();
        Mail mail = mailRepo.findById(userDTO.mailId).get();
        user.removeMail(mail);
        return "done";
    }
}
