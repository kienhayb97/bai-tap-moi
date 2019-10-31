package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class CityForm {
    private long id;
   private Country country;
   private String name;
   private Double area;
   private Double population;
   private Long gdp;
   private String introduction;
   private MultipartFile image;

    public CityForm() {
    }

    public CityForm(long id, Country country, String name, Double area, Double population, Long gdp, String introduction, MultipartFile image) {
        this.id = id;
        this.country = country;
        this.name = name;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
        this.introduction = introduction;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
