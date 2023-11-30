package com.assessment2.imperialmetricconverter;

public class WeatherData {
    private String location;
    private double tempC;
    private double tempF;
    private String type;

    public WeatherData(String location, double tempC, double tempF, String type) {
        this.location = location;
        this.tempC = tempC;
        this.tempF = tempF;
        this.type = type;
    }

    public String getLocation() {
        return location;
    }
    public double getTempC() {
        return tempC;
    }
    public double getTempF() {
        return tempF;
    }
    public String getType(){
        return type;
    }
}
