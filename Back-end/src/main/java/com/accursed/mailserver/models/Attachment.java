package com.accursed.mailserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String type;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    private Mail mail;

    public Attachment(String name, String type, byte[] data, Mail mail) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.mail = mail;
    }
}
