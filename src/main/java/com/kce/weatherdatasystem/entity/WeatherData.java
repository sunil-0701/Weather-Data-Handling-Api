package com.kce.weatherdatasystem.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "weatherdata")
public class WeatherData {
	@Id
	private String id;
	
	    private String datetime;
	    private String conds;
	    private Double dewptm;
	    private Integer fog;
	    private Integer hail;
	    private Double heatindexm;
	    private Double hum;
	    private Double precipm;
	    private Double pressurem;
	    private Integer rain;
	    private Integer snow;
	    private Double tempm;
	    private Integer thunder;
	    private Integer tornado;
	    private Double vism;
	    private Double wspdm;
	    
	    
		public WeatherData(String id, String datetime, String conds, Double dewptm, Integer fog, Integer hail,
				Double heatindexm, Double hum, Double precipm, Double pressurem, Integer rain, Integer snow,
				Double tempm, Integer thunder, Integer tornado, Double vism, Double wspdm) {
			super();
			this.id = id;
			this.datetime = datetime;
			this.conds = conds;
			this.dewptm = dewptm;
			this.fog = fog;
			this.hail = hail;
			this.heatindexm = heatindexm;
			this.hum = hum;
			this.precipm = precipm;
			this.pressurem = pressurem;
			this.rain = rain;
			this.snow = snow;
			this.tempm = tempm;
			this.thunder = thunder;
			this.tornado = tornado;
			this.vism = vism;
			this.wspdm = wspdm;
		}
		public WeatherData() {}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getDatetime() {
			return datetime;
		}
		public void setDatetime(String datetime) {
			this.datetime = datetime;
		}
		public String getConds() {
			return conds;
		}
		public void setConds(String conds) {
			this.conds = conds;
		}
		public Double getDewptm() {
			return dewptm;
		}
		public void setDewptm(Double dewptm) {
			this.dewptm = dewptm;
		}
		public Integer getFog() {
			return fog;
		}
		public void setFog(Integer fog) {
			this.fog = fog;
		}
		public Integer getHail() {
			return hail;
		}
		public void setHail(Integer hail) {
			this.hail = hail;
		}
		public Double getHeatindexm() {
			return heatindexm;
		}
		public void setHeatindexm(Double heatindexm) {
			this.heatindexm = heatindexm;
		}
		public Double getHum() {
			return hum;
		}
		public void setHum(Double hum) {
			this.hum = hum;
		}
		public Double getPrecipm() {
			return precipm;
		}
		public void setPrecipm(Double precipm) {
			this.precipm = precipm;
		}
		public Double getPressurem() {
			return pressurem;
		}
		public void setPressurem(Double pressurem) {
			this.pressurem = pressurem;
		}
		public Integer getRain() {
			return rain;
		}
		public void setRain(Integer rain) {
			this.rain = rain;
		}
		public Integer getSnow() {
			return snow;
		}
		public void setSnow(Integer snow) {
			this.snow = snow;
		}
		public Double getTempm() {
			return tempm;
		}
		public void setTempm(Double tempm) {
			this.tempm = tempm;
		}
		public Integer getThunder() {
			return thunder;
		}
		public void setThunder(Integer thunder) {
			this.thunder = thunder;
		}
		public Integer getTornado() {
			return tornado;
		}
		public void setTornado(Integer tornado) {
			this.tornado = tornado;
		}
		public Double getVism() {
			return vism;
		}
		public void setVism(Double vism) {
			this.vism = vism;
		}
		public Double getWspdm() {
			return wspdm;
		}
		public void setWspdm(Double wspdm) {
			this.wspdm = wspdm;
		}
	
}
