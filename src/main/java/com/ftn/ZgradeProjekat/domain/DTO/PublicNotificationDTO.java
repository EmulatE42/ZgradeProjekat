package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.PublicNotification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by EmulatE on 28-Jan-18.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicNotificationDTO {

    private Long id;
    private String dateOfPublicNotification;
    private String username;
    private String text;

    public PublicNotificationDTO(PublicNotification publicNotification)
    {
        this.id = publicNotification.getId();
        this.dateOfPublicNotification = publicNotification.getDateOfPublicNotification();
        this.username = publicNotification.getUsername();
        this.text = publicNotification.getText();

    }

}
