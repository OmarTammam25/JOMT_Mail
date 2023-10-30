package com.accursed.mailserver.repositories;

import com.accursed.mailserver.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByuserName(String name);
    List<User> findByEmail(String email);
}
