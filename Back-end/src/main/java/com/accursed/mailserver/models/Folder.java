package com.accursed.mailserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "folders")
//@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "folderName")
    private String folderName;
    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    @JsonIgnore
    private User user;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "mails_folders",
            joinColumns = @JoinColumn(name = "folder_id"),
            inverseJoinColumns = @JoinColumn(name = "mail_id"))

    @JsonIgnore
    private Set<Mail> mails;
    public void addMail(Mail mail){
        mails.add(mail);
    }
    public void deleteMail(Mail mail){
        mails.remove(mail);
    }

}
