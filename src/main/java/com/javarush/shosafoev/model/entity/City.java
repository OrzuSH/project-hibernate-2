package com.javarush.shosafoev.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "city")
public class City {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "city_id")
    private Short Id;
    @Column(name = "city")
    private String name;
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    private Country country;
    @Column(name = "last_update")
    @UpdateTimestamp
    private Timestamp lastUpdate;
}