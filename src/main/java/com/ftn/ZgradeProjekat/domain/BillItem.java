package com.ftn.ZgradeProjekat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by djuro on 12/2/2017.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bill_item")
public class BillItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" , unique = true, nullable = false)
    private Integer id;

    @Column(name = "bill_item_name")
    private String name;

    @Column(name = "bill_item_description")
    private String description;

    @Column(name = "bill_item_quantity")
    private Integer quantity;

    @Column(name = "bill_item_amount")
    private double amount;
}
