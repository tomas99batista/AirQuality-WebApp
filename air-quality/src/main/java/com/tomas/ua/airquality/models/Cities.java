package com.tomas.ua.airquality.models;

import javax.persistence.*;

@Entity(name="cities")
@Table(name="city")
public class Cities {
    @Id
    @GeneratedValue
    private Long idgerated;

    // Cidade
    private Long idx; //Unique ID for the city monitoring station.

    @Column(nullable=false, unique=false)
    private String name; //Name of the monitoring station.

    @Column(nullable=false, unique=false)
    private String timestamp; //Tempo da leitura

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
    private Double t;     //Temperature

    @Column(nullable=true, unique=false)
    private Double p;     //Pressure

    @Column(nullable=true, unique=false)
    private Double h;     //Humidity

    @Column(nullable=true, unique=false)
    private Double w;     //Wind

    public Cities(Long idx, String name, String timestamp, Double aqi, Double pm25, Double pm10, Double o3, Double no2, Double so2, Double t, Double p, Double h, Double w) {
        this.idx = idx;
        this.name = name;
        this.timestamp = timestamp;
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



    public Long getIdgerated() {
        return idgerated;
    }

    public Long getIdx() {
        return idx;
    }

    public String getName() {
        return name;
    }

    public String getTimestamp() {
        return timestamp;
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

    public Double getT() {
        return t;
    }

    public Double getP() {
        return p;
    }

    public Double getH() {
        return h;
    }

    public Double getW() {
        return w;
    }

    @Override
    public String toString() {
        return "Cities{" +
                "idgerated=" + idgerated +
                ", idx=" + idx +
                ", name='" + name + '\'' +
                ", timestamp='" + timestamp + '\'' +
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
