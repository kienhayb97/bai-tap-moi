package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "country")
    private Country country;

    private String name;
    private Double area;
    private Double population;
    private Long gdp;
    private String introduction;
    private String image;

    public City() {
    }

    public City(Country country, String name, Double area, Double population, Long gdp, String introduction, String image) {
        this.country = country;
        this.name = name;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
        this.introduction = introduction;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getPopulation() {
        return population;
    }

    public void setPopulation(Double population) {
        this.population = population;
    }

    public Long getGdp() {
        return gdp;
    }

    public void setGdp(Long gdp) {
        this.gdp = gdp;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}