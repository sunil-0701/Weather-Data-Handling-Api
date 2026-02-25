package com.kce.weatherdatasystem.controller;

import com.kce.weatherdatasystem.dto.TemperatureStatsDto;
import com.kce.weatherdatasystem.entity.WeatherData;
import com.kce.weatherdatasystem.service.WeatherDataService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.kce.weatherdatasystem.dto.WeatherSummaryDto;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherDataController {

    private final WeatherDataService service;

    public WeatherDataController(WeatherDataService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public String uploadCsv(@RequestParam("file") MultipartFile file) throws Exception {
        service.importCsv(file);
        return "CSV imported successfully";
    }

    @GetMapping
    public List<WeatherData> getAll() {
        return service.getAll();
    }
    
    @GetMapping("/condition/{conds}")
    public List<WeatherData> getByCondition(@PathVariable String conds) {
        return service.getByCondition(conds);
    }

    @GetMapping("/month")
    public List<WeatherSummaryDto> getByMonth(
            @RequestParam int year,
            @RequestParam int month) {

        return service.getWeatherByMonth(year, month);
    }

    @GetMapping("/date")
    public List<WeatherSummaryDto> getByDate(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int day) {

        return service.getWeatherByDate(year, month, day);
    }
    
    @GetMapping("/temperature-stats/{year}")
    public List<TemperatureStatsDto> getTempStats(@PathVariable int year) {
        return service.getTemperatureStatsByYear(year);
    }
}