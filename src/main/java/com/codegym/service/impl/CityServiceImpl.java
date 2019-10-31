package com.codegym.service.impl;

import com.codegym.model.City;
import com.codegym.repository.CityRepository;
import com.codegym.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;
    @Override
    public Page<City> findAllByNameContaining(String name, Pageable pageable) {
        return cityRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Page<City> findByOrderByAreaAsc(Pageable pageable) {
        return cityRepository.findByOrderByAreaAsc(pageable);
    }

    @Override
    public Page<City> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findOne(id);
    }

    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

    @Override
    public void remove(Long id) {
        cityRepository.delete(id);
    }
}