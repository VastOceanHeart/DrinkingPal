package com.example.drinkingpal.model;

public class CalculateAlcohol {
    private int aid;
    private String alcoholName;
    private int alcoholVolume;
    private double alcoholDensity;
    private double standardDrinkCount;
    private int quantity;

    public CalculateAlcohol(int aid, String alcoholName, int alcoholVolume, double alcoholDensity, double standardDrinkCount, int quantity) {
        this.aid = aid;
        this.alcoholName = alcoholName;
        this.alcoholVolume = alcoholVolume;
        this.alcoholDensity = alcoholDensity;
        this.standardDrinkCount = standardDrinkCount;
        this.quantity = quantity;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAlcoholName() {
        return alcoholName;
    }

    public void setAlcoholName(String alcoholName) {
        this.alcoholName = alcoholName;
    }

    public int getAlcoholVolume() {
        return alcoholVolume;
    }

    public void setAlcoholVolume(int alcoholVolume) {
        this.alcoholVolume = alcoholVolume;
    }

    public double getAlcoholDensity() {
        return alcoholDensity;
    }

    public void setAlcoholDensity(double alcoholDensity) {
        this.alcoholDensity = alcoholDensity;
    }

    public double getStandardDrinkCount() {
        return standardDrinkCount;
    }

    public void setStandardDrinkCount(double standardDrinkCount) {
        this.standardDrinkCount = standardDrinkCount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
