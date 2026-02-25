package com.kce.weatherdatasystem.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kce.weatherdatasystem.entity.WeatherData;

@Repository
public interface WeatherDataRepository extends MongoRepository<WeatherData, String> {

    
    List<WeatherData> findByCondsIgnoreCase(String conds);

   
    List<WeatherData> findByDatetimeStartingWith(String prefix);

	
}