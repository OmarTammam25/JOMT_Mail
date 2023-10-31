package com.accursed.mailserver.services;

import com.accursed.mailserver.database.DataHandler;
import com.accursed.mailserver.database.MailRepository;
import com.accursed.mailserver.models.Folder;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.services.folderService.FolderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Component
@Transactional
public class scheduled {

    @Autowired
    DataHandler dataHandler;
    @Autowired
    private FolderService folderService;
    @Autowired
    private MailRepository mailRepo;
    @Scheduled(cron = "0 0 23 * * ?")
    public void scheduledTrashDelete(){
        System.out.println("cron is working nowwwwwwwww");
        List<Folder> usersTrashes = dataHandler.getFoldersByName("trash");
        for(Folder trash: usersTrashes){
            for(Mail mail : trash.getMails()){
                System.out.println(mail.getId());
                System.out.println(Duration.between(LocalTime.now(),mail.getTimestamp().toLocalDateTime()).compareTo(Duration.ofSeconds(2))<0);
                if(Duration.between(LocalTime.now(),mail.getTimestamp().toLocalDateTime()).compareTo(Duration.ofSeconds(2))<0){
                    folderService.deleteMailFromFolder(mail.getId(), trash.getId());
                    mail.deleteFromFolder(trash);
                    mailRepo.save(mail);
                    if(mail.getFolders().size()==0){
                        mail.getAttachments().clear();
                        dataHandler.deleteMailFromTableByID(mail.getId());
                    }
                }
            }
        }
    }
}
