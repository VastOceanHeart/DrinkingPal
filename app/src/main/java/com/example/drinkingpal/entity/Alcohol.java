package com.example.drinkingpal.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Alcohol {
    @PrimaryKey (autoGenerate = true)
    @NotNull
    public int aid;

    @NotNull
    public String alcoholName;

    @NotNull
    public int alcoholVolume;

    @NotNull
    public double alcoholDensity;

    @NotNull
    public double standardDrinkCount;

    public Alcohol(int aid, @NotNull String alcoholName, int alcoholVolume, double alcoholDensity, double standardDrinkCount) {
        this.aid = aid;
        this.alcoholName = alcoholName;
        this.alcoholVolume = alcoholVolume;
        this.alcoholDensity = alcoholDensity;
        this.standardDrinkCount = standardDrinkCount;
    }
}
