package com.example.drinkingpal.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.drinkingpal.entity.Alcohol;

import java.util.List;

@Dao
public interface AlcoholDAO {
    @Query("SELECT * FROM alcohol ORDER BY aid ASC")
    LiveData<List<Alcohol>> getAll();

    @Query("SELECT * FROM alcohol WHERE aid = :alcoholId LIMIT 1")
    Alcohol findByAid(int alcoholId);

    @Insert
    void insert(Alcohol alcohol);

    @Delete
    void delete(Alcohol alcohol);

    @Update
    void updateAlcohol(Alcohol alcohol);

    @Query("DELETE FROM alcohol")
    void deleteAll();
}
