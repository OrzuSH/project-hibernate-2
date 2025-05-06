package com.javarush.shosafoev.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "actor")
public class Actor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "actor_id")
    private Short actorId;
    @Column(name = "first_name")
    private String firstName;
    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    )
    private List<Film> films;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "last_update", columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDate lastUpdate;
    
}