package com.javarush.shosafoev.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "film_text")
public class FilmText {
    @Id
    @Column(name = "film_id")
    private Short id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "film_id")
    private Film film;
    @Column(name = "title")
    private String title;
    @Column(name = "description", columnDefinition = "text")
    private String description;
}