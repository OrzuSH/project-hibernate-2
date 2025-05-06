package com.javarush.shosafoev.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "language")
public class Language {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "language_id", columnDefinition = "tinyint unsigned")
    private Byte languageId;
    @Column(name = "name", columnDefinition = "char(20)")
    private String name;
    @Column(name = "last_update", columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDate lastUpdate;
}