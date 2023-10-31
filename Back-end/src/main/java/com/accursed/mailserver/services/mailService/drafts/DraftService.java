package com.accursed.mailserver.services.mailService.drafts;

import com.accursed.mailserver.database.DataHandler;
import com.accursed.mailserver.dtos.MailDTO;
import com.accursed.mailserver.dtos.MailMapper;
import com.accursed.mailserver.models.*;
import com.accursed.mailserver.services.attachmentService.AttachmentService;
import com.accursed.mailserver.services.folderService.FolderService;
import com.accursed.mailserver.services.mailService.builders.DraftBuilder;
import com.accursed.mailserver.services.mailService.builders.ImmutableMailBuilder;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class DraftService {

    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    DataHandler dataHandler;
    @Autowired
    FolderService folderService;

    private MailMapper mailMapper = Mappers.getMapper(MailMapper.class);


    public DraftMail postDraft(MailDTO dto, MultipartFile[] files) throws IOException{
        DraftBuilder draftBuilder = DraftBuilder.getInstance();
        draftBuilder.reset();
        DraftMail mail =
                (DraftMail) draftBuilder.setDate()
                        .setMailTo(dto.to)
                        .setMailFrom(dto.from)
                        .setContent(dto.content)
                        .setPriority(dto.priority)
                        .setSubject(dto.subject)
                        .setIsStarred(dto.isStarred)
                        .setState(dto.state)
                        .getResult();
        if(files == null){
            dataHandler.saveMailToMailTable(mail);
        }else {
            for (MultipartFile file : files) {
                Attachment attachment = attachmentService.setAttachmentToMail(file, mail);
                dataHandler.saveAttachmentToAttachmentTable(attachment);
            }
        }
        String userId = dataHandler.getUserByEmail(dto.from).getId();
        dataHandler.saveMailToMailTable(mail);
        Folder draftFolder = dataHandler.getFolderByUserIdAndFolderName(userId, "draft");
        folderService.addMailToFolder(mail.getId(), draftFolder.getId());
        return mail;
    }

    public void updateDraft(MailDTO dto, MultipartFile[] files) throws IOException{
        Optional<Mail> mail = Optional.ofNullable(dataHandler.getMailByMailId(dto.mailId));
        if(mail.isPresent()) {
            for(Attachment i : mail.get().getAttachments())
                dataHandler.deleteAttachmentFromTableByID(i.getId());
            ((DraftMail)mail.get()).setAttachments(new HashSet<>());

            mailMapper.updateMailFromDto(dto, (DraftMail) mail.get());
            Set<Attachment> attachmentSet = new HashSet<>();
            if(files != null){
                for(MultipartFile file: files){
                    Attachment attachment = attachmentService.setAttachmentToMail(file, mail.get());
                    attachmentSet.add(attachment);
                    dataHandler.saveAttachmentToAttachmentTable(attachment);
                }
            }
            ((DraftMail) mail.get()).setAttachments(attachmentSet);

            String userId = dataHandler.getUserByEmail(dto.from).getId();
            dataHandler.saveMailToMailTable(mail.get());
            Folder draftFolder = dataHandler.getFolderByUserIdAndFolderName(userId, "draft");
            folderService.addMailToFolder(mail.get().getId(), draftFolder.getId());

        }else
            throw new IOException();
    }


    public ImmutableMail sendDraft(MailDTO dto){
        DraftMail draft = (DraftMail) dataHandler.getMailByMailId(dto.mailId);
        ImmutableMailBuilder mailBuilder = ImmutableMailBuilder.getInstance();
        mailBuilder.reset();
        ImmutableMail mail =
                (ImmutableMail) mailBuilder
                        .setMailTo(draft.getMailTo())
                        .setMailFrom(draft.getMailFrom())
                        .setDate()
                        .setContent(draft.getContent())
                        .setPriority(draft.getPriority())
                        .setSubject(draft.getSubject())
                        .setIsStarred(draft.getIsStarred())
                        .setState(draft.getState())
                        .getResult();

        Set<Attachment> attachmentSet = new HashSet<>();
        if(draft.getAttachments() != null){
            for(Attachment i : draft.getAttachments()){
                attachmentSet.add(i);
                i.setMail(mail);
                dataHandler.saveAttachmentToAttachmentTable(i);
            }
        }
        String receiverId = dataHandler.getUserByEmail(mail.getMailTo()).getId();
        String senderId = dataHandler.getUserByEmail(mail.getMailFrom()).getId();
        dataHandler.saveMailToMailTable(mail);
        Folder receiverInboxFolder = dataHandler.getFolderByUserIdAndFolderName(receiverId, "inbox");
        Folder senderSentFolder = dataHandler.getFolderByUserIdAndFolderName(senderId, "sent");
        Folder senderDraftFolder = dataHandler.getFolderByUserIdAndFolderName(senderId, "draft");
        folderService.addMailToFolder(mail.getId(), receiverInboxFolder.getId());
        folderService.addMailToFolder(mail.getId(), senderSentFolder.getId());
        folderService.deleteMailFromFolder(mail.getId(), senderDraftFolder.getId());
        dataHandler.deleteMailFromTableByID(draft.getId());
        return mail;
    }

    public void deleteDraft(String draftID){
        DraftMail draft = (DraftMail) dataHandler.getMailByMailId(draftID);
        Set<Attachment> attachments = draft.getAttachments();
        Set<Folder> folders = draft.getFolders();
        draft.setAttachments(new HashSet<>());
        draft.setFolders(new HashSet<>());
        for(Attachment it : attachments){
            dataHandler.deleteAttachmentFromTableByID(it.getId());
        }
        for(Folder it : folders){
            dataHandler.deleteMailFromFolder(draftID, it.getId());
        }
            dataHandler.deleteMailFromTableByID(draftID);
    }

    public Optional<Mail> getDraft(String mailId){
        return Optional.ofNullable(dataHandler.getMailByMailId(mailId));
    }
}