package com.example.drinkingpal.repository;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.drinkingpal.dao.AlcoholDAO;
import com.example.drinkingpal.database.DrinkingPalDatabase;
import com.example.drinkingpal.entity.Alcohol;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

;

public class AlcoholRepository {
    private AlcoholDAO alcoholDao;
    private LiveData<List<Alcohol>> alcohols;

    public AlcoholRepository(Application application) {
        DrinkingPalDatabase db = DrinkingPalDatabase.getInstance(application);
        alcoholDao = db.alcoholDao();
        alcohols = alcoholDao.getAll();
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
        DrinkingPalDatabase.drinkingPalDatabaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                alcoholDao.insert(alcohol);
            }
        });
    }

    /**
     * Delete a existing alcohol
     *
     * @param alcohol The alcohol want to delete
     */
    public void delete(final Alcohol alcohol) {
        DrinkingPalDatabase.drinkingPalDatabaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                alcoholDao.delete(alcohol);
            }
        });
    }

    /**
     * Update a existing alcohol
     *
     * @param alcohol The new information about targeted alcohol
     */
    public void updateAlcohol(final Alcohol alcohol) {
        DrinkingPalDatabase.drinkingPalDatabaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                alcoholDao.updateAlcohol(alcohol);
            }
        });
    }

    /**
     * Delete all alcohols in the database
     */
    public void deleteAll() {
        DrinkingPalDatabase.drinkingPalDatabaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                alcoholDao.deleteAll();
            }
        });
    }

    /**
     * Find a alcohol based on specific aid
     *
     * @param alcoholAid The aid for target alcohol
     * @return The found alcohol
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Alcohol> findByAidFuture(final int alcoholAid) {
        return CompletableFuture.supplyAsync(new Supplier<Alcohol>() {
            @Override
            public Alcohol get() {
                return alcoholDao.findByAid(alcoholAid);
            }
        }, DrinkingPalDatabase.drinkingPalDatabaseWriteExecutor);
    }
}
