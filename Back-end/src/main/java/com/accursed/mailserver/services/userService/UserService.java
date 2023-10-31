package com.accursed.mailserver.services.userService;

import com.accursed.mailserver.dtos.MailMapper;
import com.accursed.mailserver.dtos.UserDTO;
import com.accursed.mailserver.models.Folder;
import com.accursed.mailserver.models.User;
import com.accursed.mailserver.database.MailRepository;
import com.accursed.mailserver.database.UserRepository;
import com.accursed.mailserver.services.folderService.FolderService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private MailRepository mailRepo;
    @Autowired
    private FolderService folderService;
    private MailMapper userMapper = Mappers.getMapper(MailMapper.class);
    public void register(UserDTO userDTO) throws Exception {
        User user = new User();
        userRepo.save(userMapper.updateUserFromDto(userDTO, user));
        folderService.initializeUserFolders(user.getId());
    }
    public UserDTO login(UserDTO userDTO) throws Exception {
            User user = userRepo.findByEmail(userDTO.email).get(0);
            List<Folder> folders = user.getFolders();
            HashMap<String, String> folderNames = new HashMap<>();
            for(Folder it: folders) {
                folderNames.put(it.getFolderName(), it.getId());
            }
            userDTO.id = user.getId();
            userDTO.folderNames = folderNames;
            return userDTO;
    }


//
//    public List<Mail> getinbox(UserDTO userDTO){
//        List<Mail> inbox = new ArrayList<>();
//        inbox.addAll(userRepo.findById(userDTO.id).get().getSentMails());
//        inbox.addAll(userRepo.findById(userDTO.id).get().getReceivedMails());
//        return inbox;
//    }
//    public Set<Mail> getSentMails(UserDTO userDTO){
//        return userRepo.findById(userDTO.id).get().getSentMails();
//    }
//    // TODO rename to camelCase
//    public Set<Mail> getreceivedMails(UserDTO userDTO){
//        return userRepo.findById(userDTO.id).get().getReceivedMails();
//    }
//
//    public String deletemail(UserDTO userDTO){
//        User user = userRepo.findById(userDTO.id).get();
//        Mail mail = mailRepo.findById(userDTO.mailId).get();
//        user.removeMail(mail);
//        return "done";
//    }
//
//    public Set<Folder> getFolders(UserDTO userDTO) {
//        User user = userRepo.findById(userDTO.id).get();
//        return user.getFolders();
//    }
}
