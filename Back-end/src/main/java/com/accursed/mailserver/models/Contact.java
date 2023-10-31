package com.accursed.mailserver.models;
import com.accursed.mailserver.models.User;
import com.accursed.mailserver.dtos.ContactDTO;
import com.accursed.mailserver.dtos.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @Column(name = "Name")
    private String name;

    @Column(name = "Mails")
    private String mails;

    public Contact(String name, String mails){
        this.name = name;
        this.mails = mails;
    }

    public static Contact getInstance(ContactDTO contactDTO){
        return new Contact(contactDTO.name, contactDTO.mails);
    }
}