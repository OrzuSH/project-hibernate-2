package com.javarush.shosafoev.model.entity;

import com.javarush.shosafoev.convertes.RatingAttributeConverter;
import com.javarush.shosafoev.convertes.YearAttributeConvertor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@NoArgsConstructor
@Data
@Entity
@Table(name = "film")
public class Film {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "film_id", columnDefinition = "smallint")
    private Short id;
    @Column(name = "title")
    private String title;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "release_year", columnDefinition = "year")
    @Convert(converter = YearAttributeConvertor.class)
    private Year releaseYear;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language language;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;
    @Column(name = "rental_duration")
    private Byte rentalDuration;
    @Column(name = "rental_rate")
    private BigDecimal rentalRate;
    @Column(name = "length")
    private Short length;
    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;
    @Column(name = "rating", columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
    @Convert(converter = RatingAttributeConverter.class)
    private Rating rating;
    @Column(name = "special_features",
            columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;
    @Column(name = "last_update", columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDate lastUpdate;
    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id")
    )
    private List<Actor> actors;
    @ManyToMany
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    )
    private List<Category> categories;
    
    @SuppressWarnings("unused")
    public Set<Feature> getSpecialFeatures() {
        if (isNull(specialFeatures) || specialFeatures.isEmpty()) {
            return null;
        }
        Set<Feature> result = new HashSet<>();
        return Arrays.stream(specialFeatures.split(","))
                .flatMap(s -> {
                    result.add(Feature.fromCode(s));
                    return result.stream();
                }).filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
    
    public void setSpecialFeatures(Set<Feature> features) {
        if (isNull(features)) {
            specialFeatures = null;
        } else {
            specialFeatures = features
                    .stream()
                    .map(Feature::getValue)
                    .collect(Collectors.joining(","));
        }
    }
    
    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + id +
                ", title='" + title + '\'' +
                ", categories=" + categories.size() +
                ", actors=" + actors.size() +
                ", description='" + description + '\'' +
                ", releaseYear=" + releaseYear +
                ", language=" + language +
                ", originalLanguage=" + originalLanguage +
                ", rentalDuration=" + rentalDuration +
                ", rentalRate=" + rentalRate +
                ", length=" + length +
                ", replacementCost=" + replacementCost +
                ", rating=" + rating +
                ", specialFeatures='" + specialFeatures + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}