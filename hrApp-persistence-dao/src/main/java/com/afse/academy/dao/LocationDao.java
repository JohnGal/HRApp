package com.afse.academy.dao;

import java.util.List;

public interface LocationDao {
    List<String> getAllCountries();

    List<String> getCities(String country);

    boolean contains(String country, String city);
}
