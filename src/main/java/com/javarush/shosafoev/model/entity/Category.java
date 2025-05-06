package com.javarush.shosafoev.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_id", columnDefinition = "tinyint")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_update", columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDate lastUpdate;
    @ManyToMany
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    )
    private List<Film> films;
}