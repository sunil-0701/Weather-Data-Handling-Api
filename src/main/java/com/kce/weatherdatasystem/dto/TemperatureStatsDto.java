package com.kce.weatherdatasystem.dto;

public class TemperatureStatsDto {

    private int month;
    private Double maxTemp;
    private Double minTemp;
    private Double medianTemp;

    public TemperatureStatsDto(int month, Double maxTemp,
                               Double minTemp, Double medianTemp) {
        this.month = month;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.medianTemp = medianTemp;
    }

    public int getMonth() { return month; }
    public Double getMaxTemp() { return maxTemp; }
    public Double getMinTemp() { return minTemp; }
    public Double getMedianTemp() { return medianTemp; }
}