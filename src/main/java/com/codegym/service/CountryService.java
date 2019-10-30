package com.codegym.service;

import com.codegym.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {
    Page<Country> findAll(Pageable pageable);

    Country findById(Long id);

    void save(Country country);

    void remove(Long id);
}
