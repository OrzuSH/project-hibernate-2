package com.javarush.shosafoev.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "staff")
public class Staff {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "staff_id")
    private Byte id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Lob
    @Column(name = "picture", columnDefinition = "BLOB")
    private byte[] picture;
    @Column(name = "email")
    private String email;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    @Column(name = "active", columnDefinition = "BIT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isActive;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "last_update")
    @UpdateTimestamp
    private Timestamp lastUpdate;
    
    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", active=" + isActive +
                '}';
    }
}