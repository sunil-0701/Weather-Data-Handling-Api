package com.kce.weatherdatasystem.service;

import com.kce.weatherdatasystem.dto.TemperatureStatsDto;
import com.kce.weatherdatasystem.entity.WeatherData;
import com.kce.weatherdatasystem.repository.WeatherDataRepository;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.kce.weatherdatasystem.dto.WeatherSummaryDto;
import java.util.stream.Collectors;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class WeatherDataService {

    private final WeatherDataRepository repository;

    public WeatherDataService(WeatherDataRepository repository) {
        this.repository = repository;
    }

    public void importCsv(MultipartFile file) throws Exception {

        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVReader csvReader = new CSVReader(reader)) {

            String[] row;
            csvReader.readNext();

            List<WeatherData> batch = new ArrayList<>();
            int batchSize = 1000;

            while ((row = csvReader.readNext()) != null) {

                WeatherData data = new WeatherData();

                data.setDatetime(row[0]);
                data.setConds(row[1]);
                data.setDewptm(parseDouble(row[2]));
                data.setFog(parseInt(row[3]));
                data.setHail(parseInt(row[4]));
                data.setHeatindexm(parseDouble(row[5]));
                data.setHum(parseDouble(row[6]));
                data.setPressurem(cleanPressure(parseDouble(row[8])));
                data.setTempm(parseDouble(row[11]));
                data.setWspdm(parseDouble(row[19]));

                batch.add(data);

                if (batch.size() == batchSize) {
                    repository.saveAll(batch);
                    batch.clear();
                }
            }

            if (!batch.isEmpty()) {
                repository.saveAll(batch);
            }
        }
    }

    public List<WeatherData> getAll() {
        return repository.findAll();
    }

    public List<WeatherData> getByCondition(String conds) {
        return repository.findByCondsIgnoreCase(conds);
    }

    public List<WeatherSummaryDto> getWeatherByMonth(int year, int month) {

        String monthStr = String.format("%02d", month);
        String prefix = year + monthStr;

        return repository.findByDatetimeStartingWith(prefix)
                .stream()
                .map(w -> new WeatherSummaryDto(
                        w.getConds(),
                        w.getTempm(),
                        w.getHum(),
                        w.getPressurem()))
                .collect(Collectors.toList());
    }

    public List<WeatherSummaryDto> getWeatherByDate(int year, int month, int day) {

        String monthStr = String.format("%02d", month);
        String dayStr = String.format("%02d", day);
        String prefix = year + monthStr + dayStr;

        return repository.findByDatetimeStartingWith(prefix)
                .stream()
                .map(w -> new WeatherSummaryDto(
                        w.getConds(),
                        w.getTempm(),
                        w.getHum(),
                        w.getPressurem()))
                .collect(Collectors.toList());
    }
    public List<TemperatureStatsDto> getTemperatureStatsByYear(int year) {

        String prefix = String.valueOf(year);
        List<WeatherData> yearData =
                repository.findByDatetimeStartingWith(prefix);

        Map<Integer, List<Double>> monthMap = new HashMap<>();

        for (WeatherData w : yearData) {

            if (w.getTempm() == null) 
            	continue;
            
            int month = Integer.parseInt(w.getDatetime().substring(4, 6));

            monthMap.computeIfAbsent(month, k -> new ArrayList<>())
                    .add(w.getTempm());
        }

        List<TemperatureStatsDto> result = new ArrayList<>();

        for (Map.Entry<Integer, List<Double>> entry : monthMap.entrySet()) {

            int month = entry.getKey();
            List<Double> temps = entry.getValue();

            Collections.sort(temps);

            double min = temps.get(0);
            double max = temps.get(temps.size() - 1);
            double median = temps.get(temps.size() / 2);

            result.add(new TemperatureStatsDto(month, max, min, median));
        }

        return result;
    }



    private Double parseDouble(String val) {
        try {
            if (val == null || val.isBlank()
                    || val.equalsIgnoreCase("NA")
                    || val.equalsIgnoreCase("N/A")) {
                return null;
            }
            return Double.parseDouble(val);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Integer parseInt(String val) {
        try {
            if (val == null || val.isBlank()
                    || val.equalsIgnoreCase("NA")
                    || val.equalsIgnoreCase("N/A")) {
                return null;
            }
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Double cleanPressure(Double val) {
        if (val != null && val == -9999) return null;
        return val;
    }
}