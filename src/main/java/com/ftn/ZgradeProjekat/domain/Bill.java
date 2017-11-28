package com.ftn.ZgradeProjekat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Momir on 14.11.2017.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" , unique = true, nullable = false)
    private Integer id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date")
    private Date date;
}
