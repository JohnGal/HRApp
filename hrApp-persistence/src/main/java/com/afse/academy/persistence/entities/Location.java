package com.afse.academy.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "Location.findAllCountries", query = "select distinct l.country from Location as l order by l.country"),
        @NamedQuery(name = "Location.findCitiesByCountry", query = "select l.city from Location as l where l.country = :country order by l.city")
})
public class Location implements Serializable {
    private static final long serialVersionUID = 5741525960531027982L;

    @Id
    private Long id;


    private String country;

    private String city;

    @Version
    @Column(name = "DBVERSION", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
