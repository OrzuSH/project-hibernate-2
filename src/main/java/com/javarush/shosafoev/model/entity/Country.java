package com.javarush.shosafoev.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
@Data
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Short countryId;
    @Column(name = "country")
    private String country;
    @Column(name = "last_update", columnDefinition = "timestamp")
    @UpdateTimestamp
    private Timestamp lastUpdate;
}