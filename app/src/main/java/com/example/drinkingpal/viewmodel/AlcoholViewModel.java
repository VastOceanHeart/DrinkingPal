package com.example.drinkingpal.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.drinkingpal.entity.Alcohol;
import com.example.drinkingpal.repository.AlcoholRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AlcoholViewModel extends AndroidViewModel {
    private AlcoholRepository alcoholRepository;
    private LiveData<List<Alcohol>> alcohols;

    public AlcoholViewModel(Application application) {
        super(application);
        alcoholRepository = new AlcoholRepository(application);
        alcohols = alcoholRepository.getAllAlcohols();
    }


    /**
     * Get all alcohol from database
     */
    public LiveData<List<Alcohol>> getAllAlcohols() {
        return alcohols;
    }

    /**
     * Insert a new alcohol
     *
     * @param alcohol The new alcohol want to insert
     */
    public void insert(final Alcohol alcohol) {
        alcoholRepository.insert(alcohol);
    }

    /**
     * Delete a existing alcohol
     *
     * @param alcohol The appUser want to delete
     */
    public void delete(final Alcohol alcohol) {
        alcoholRepository.delete(alcohol);
    }

    /**
     * Update a existing alcohol
     *
     * @param alcohol The new information about targeted alcohol
     */
    public void updateAlcohol(final Alcohol alcohol) {
        alcoholRepository.updateAlcohol(alcohol);
    }

    /**
     * Delete all alcohols in the database
     */
    public void deleteAll() {
        alcoholRepository.deleteAll();
    }

    /**
     * Find a alcohol based on specific aid
     *
     * @param alcoholAid The aid for target alcohol
     * @return The found alcohol
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Alcohol> findByAidFuture(final int alcoholAid) {
        return alcoholRepository.findByAidFuture(alcoholAid);
    }
}
