package com.ruenzuo.weatherapp.pojos;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinate implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6606312772708884804L;
    private float lon;
    private float lat;

    public float getLon() {
        return lon;
    }
    public void setLon(float lon) {
        this.lon = lon;
    }
    public float getLat() {
        return lat;
    }
    public void setLat(float lat) {
        this.lat = lat;
    }

}