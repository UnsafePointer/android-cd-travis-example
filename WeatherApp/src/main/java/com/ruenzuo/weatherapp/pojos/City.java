package com.ruenzuo.weatherapp.pojos;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class City implements Serializable, Cloneable {

    /**
     *
     */
    private static final long serialVersionUID = 1468585156078081034L;
    private String name;
    private Coordinate coord;
    private Forecast main;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getCoord() {
        return coord;
    }

    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    public Forecast getMain() {
        return main;
    }

    public void setMain(Forecast main) {
        this.main = main;
    }

}