package com.javarush.shosafoev.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "store")
public class Store {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "store_id")
    private Byte id;
    @OneToOne
    @JoinColumn(name = "manager_staff_id")
    private Staff staff;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "last_update", columnDefinition = "timestamp")
    @UpdateTimestamp
    private Timestamp lastUpdate;
}