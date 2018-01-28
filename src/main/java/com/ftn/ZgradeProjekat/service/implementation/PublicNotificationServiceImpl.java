package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.DTO.PublicNotificationDTO;
import com.ftn.ZgradeProjekat.domain.PublicNotification;
import com.ftn.ZgradeProjekat.repository.PublicNotificationRepository;
import com.ftn.ZgradeProjekat.service.PublicNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EmulatE on 28-Jan-18.
 */
@Service
public class PublicNotificationServiceImpl implements PublicNotificationService {

    @Autowired
    PublicNotificationRepository publicNotificationRepository;

    @Override
    public PublicNotificationDTO save(PublicNotificationDTO publicNotificationdto) {
        publicNotificationRepository.save(new PublicNotification(publicNotificationdto));
        return publicNotificationdto;
    }

    @Override
    public List<PublicNotificationDTO> getAllPublicNotifications() {
        List<PublicNotification> s =  publicNotificationRepository.findAll();
        List<PublicNotificationDTO> ret = new ArrayList<>();

        for (PublicNotification s1 : s)
        {
            ret.add(new PublicNotificationDTO(s1));
        }

        return ret;
    }
}
