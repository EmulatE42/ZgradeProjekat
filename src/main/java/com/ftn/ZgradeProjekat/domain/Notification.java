package com.ftn.ZgradeProjekat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by djuro on 11/4/2017.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
@Setter
@Table(name = "notification")
public class Notification
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notification_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "notification_user_id")
    private User creator;

    @Column(name = "notification_description")
    private String description;

    @Column(name = "notification_date")
    private Date notificationDate;
}
