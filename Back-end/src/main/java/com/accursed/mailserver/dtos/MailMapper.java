package com.accursed.mailserver.dtos;

import com.accursed.mailserver.models.DraftMail;
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
}
