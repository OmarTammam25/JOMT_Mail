package com.accursed.mailserver.models;

import com.accursed.mailserver.dtos.UserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String userName;
    @Column(name = "Email")
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Folder> folders;
//    @OneToMany(mappedBy = "mailFrom",orphanRemoval = true)
//    private Set<Mail> sentMails;
//    @OneToMany(mappedBy = "mailTo",orphanRemoval = true)
//    private Set<Mail> receivedMails;

    @OneToMany(mappedBy = "user",orphanRemoval = true)
    private Set<Contact> contacts;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
//    //TODO for testing
//    public void removeMail (Mail mail){
//        sentMails.remove(mail);
//        receivedMails.remove(mail);
//    }
}

