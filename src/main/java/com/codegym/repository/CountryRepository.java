package com.codegym.repository;

import com.codegym.model.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountryRepository extends PagingAndSortingRepository<Country,Long> {
}
