package com.tomas.ua.airquality.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="cities")
@Table(name="city")
class Cities {
    // Cidade
    private @Id Long idx; //Unique ID for the city monitoring station.

    @Column(nullable=false, unique=false)
    private String name; //Name of the monitoring station.


    // Localizacao
    @Column(nullable=false, unique=false)
    private Double longi; //Longitude

    @Column(nullable=false, unique=false)
    private Double lati; //Latitude


    // Valores
    @Column(nullable=false, unique=false)
    private Double aqi; //Real-time air quality information.


    // Valores gases
    // Valores podem ser null ou optional ou wtv pq ha cidades q n tem
    @Column(nullable=true, unique=false)
    private Double pm25;  //PM 2.5

    @Column(nullable=true, unique=false)
    private Double pm10;  //PM 10

    @Column(nullable=true, unique=false)
    private Double o3;    //Ozono

    @Column(nullable=true, unique=false)
    private Double no2; //Dióxido de nitrogénio

    @Column(nullable=true, unique=false)
    private Double so2; //Dióxido de enxofre


    // Valores temperatura
    @Column(nullable=true, unique=false)
    private Long t;     //Temperature

    @Column(nullable=true, unique=false)
    private Long p;     //Pressure

    @Column(nullable=true, unique=false)
    private Long h;     //Humidity

    @Column(nullable=true, unique=false)
    private Long w;     //Wind


    public Cities(Long idx, String name, Double longi, Double lati, Double aqi, Double pm25, Double pm10, Double o3, Double no2, Double so2, Long t, Long p, Long h, Long w) {
        this.idx = idx;
        this.name = name;
        this.longi = longi;
        this.lati = lati;
        this.aqi = aqi;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.o3 = o3;
        this.no2 = no2;
        this.so2 = so2;
        this.t = t;
        this.p = p;
        this.h = h;
        this.w = w;
    }

    public Cities() {}

    public Long getIdx() {
        return idx;
    }

    public String getName() {
        return name;
    }

    public Double getLongi() {
        return longi;
    }

    public Double getLati() {
        return lati;
    }

    public Double getAqi() {
        return aqi;
    }

    public Double getPm25() {
        return pm25;
    }

    public Double getPm10() {
        return pm10;
    }

    public Double getO3() {
        return o3;
    }

    public Double getNo2() {
        return no2;
    }

    public Double getSo2() {
        return so2;
    }

    public Long getT() {
        return t;
    }

    public Long getP() {
        return p;
    }

    public Long getH() {
        return h;
    }

    public Long getW() {
        return w;
    }

    @Override
    public String toString() {
        return "Models{" +
                "idx=" + idx +
                ", name='" + name + '\'' +
                ", longi=" + longi +
                ", lati=" + lati +
                ", aqi=" + aqi +
                ", pm25=" + pm25 +
                ", pm10=" + pm10 +
                ", o3=" + o3 +
                ", no2=" + no2 +
                ", so2=" + so2 +
                ", t=" + t +
                ", p=" + p +
                ", h=" + h +
                ", w=" + w +
                '}';
    }
}
