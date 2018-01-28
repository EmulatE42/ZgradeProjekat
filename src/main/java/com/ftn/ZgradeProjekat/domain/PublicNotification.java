package com.ftn.ZgradeProjekat.domain;

import com.ftn.ZgradeProjekat.domain.DTO.PublicNotificationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "publicNotification")

/**
 * Created by EmulatE on 26-Jan-18.
 */
public class PublicNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "publicNotificationID", unique = true, nullable = false)
    private Long id;

    @Column(name = "datePublicNotification")
    private String dateOfPublicNotification;

    @Column(name = "username")
    private String username;

    @Column(name = "text")
    private String text;

    public PublicNotification(PublicNotificationDTO publicNotificationDTO)
    {
        this.id = publicNotificationDTO.getId();
        this.dateOfPublicNotification = publicNotificationDTO.getDateOfPublicNotification();
        this.username = publicNotificationDTO.getUsername();
        this.text = publicNotificationDTO.getText();
    }
}
