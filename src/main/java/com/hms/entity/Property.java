package com.hms.entity;

import jakarta.persistence.*;

@Entity
@Table(name="property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="name",nullable = false,length=255)
    private String name;
    @Column(name="no_of_guests",nullable = false,length=255)
    private int no_of_guests;
    @Column(name="no_of_bedrooms",nullable = false,length=255)
    private int no_of_bedrooms;
    @Column(name="no_of_bathrooms",nullable = false,length=255)
    private int no_of_bathrooms;
    @Column(name="no_of_beds",nullable = false,length=255)
    private int no_of_beds;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo_of_guests() {
        return no_of_guests;
    }

    public void setNo_of_guests(int no_of_guests) {
        this.no_of_guests = no_of_guests;
    }

    public int getNo_of_bedrooms() {
        return no_of_bedrooms;
    }

    public void setNo_of_bedrooms(int no_of_bedrooms) {
        this.no_of_bedrooms = no_of_bedrooms;
    }

    public int getNo_of_bathrooms() {
        return no_of_bathrooms;
    }

    public void setNo_of_bathrooms(int no_of_bathrooms) {
        this.no_of_bathrooms = no_of_bathrooms;
    }

    public int getNo_of_beds() {
        return no_of_beds;
    }

    public void setNo_of_beds(int no_of_beds) {
        this.no_of_beds = no_of_beds;
    }
}
