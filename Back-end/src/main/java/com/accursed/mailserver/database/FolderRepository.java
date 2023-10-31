package com.accursed.mailserver.database;

import com.accursed.mailserver.models.Folder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends CrudRepository<Folder,String> {
    List<Folder> findByFolderName(String folderName);
    Folder findByUserIdAndFolderName(String userId, String folderName);
}
