package com.kce.weatherdatasystem.dto;

public class WeatherSummaryDto {

    private String conds;
    private Double tempm;
    private Double hum;
    private Double pressurem;

    public WeatherSummaryDto(String conds, Double tempm,
                             Double hum, Double pressurem) {
        this.conds = conds;
        this.tempm = tempm;
        this.hum = hum;
        this.pressurem = pressurem;
    }

    public String getConds() {
        return conds;
    }

    public Double getTempm() {
        return tempm;
    }

    public Double getHum() {
        return hum;
    }

    public Double getPressurem() {
        return pressurem;
    }
}