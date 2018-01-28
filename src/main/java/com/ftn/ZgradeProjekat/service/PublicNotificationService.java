package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.DTO.PublicNotificationDTO;

import java.util.List;

/**
 * Created by EmulatE on 28-Jan-18.
 */
public interface PublicNotificationService {
    PublicNotificationDTO save(PublicNotificationDTO publicNotificationDTO);
    List<PublicNotificationDTO> getAllPublicNotifications();
}
