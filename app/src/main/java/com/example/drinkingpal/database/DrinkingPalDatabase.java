package com.example.drinkingpal.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.drinkingpal.dao.AlcoholDAO;
import com.example.drinkingpal.entity.Alcohol;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Alcohol.class}, version = 5, exportSchema = false)
public abstract class DrinkingPalDatabase extends RoomDatabase {

    //For initial database use
    public abstract AlcoholDAO alcoholDao();
    private static DrinkingPalDatabase INSTANCE;

    //Declare how many threads would be handle for this database
    private static final int NUMBER_OF_THREADS = 4;

    //Following part are used to create database
    public static final ExecutorService drinkingPalDatabaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized DrinkingPalDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DrinkingPalDatabase.class, "DrinkingPalDatabase").
                    fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
