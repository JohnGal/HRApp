package com.afse.academy.services;

import java.util.List;

public interface CountryService {
    List<String> getAllCountries();

    List<String> getCities(String country);
}
