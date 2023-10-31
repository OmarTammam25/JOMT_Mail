package com.accursed.mailserver.dtos;

import com.accursed.mailserver.models.Contact;
import com.accursed.mailserver.models.DraftMail;
import com.accursed.mailserver.models.Folder;
import com.accursed.mailserver.models.Mail;
import com.accursed.mailserver.models.User;
import org.mapstruct.*;

// TODO mapping isStarred not working
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MailMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DraftMail updateMailFromDto(MailDTO mailDTO, @MappingTarget DraftMail entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUserFromDto(UserDTO userDTO, @MappingTarget User entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Contact updateContactFromDto(ContactDTO contactDTO, @MappingTarget Contact entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Folder updateFolderFromDto(FolderDTO folderDTO, @MappingTarget Folder folder);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MailDTO getMailDtoFromMail(Mail mail, @MappingTarget MailDTO mailDTO);

}
