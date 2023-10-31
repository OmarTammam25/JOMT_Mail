package com.accursed.mailserver.database;

import com.accursed.mailserver.models.Attachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends CrudRepository<Attachment, String> {
}
