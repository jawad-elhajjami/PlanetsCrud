package com.example.planets.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.planets.Models.Planets;

import java.util.List;

@Dao
public interface MainDAO {
    @Insert
    void insert(Planets planet);

    @Query("SELECT * FROM planets ORDER BY ID DESC")
    List<Planets> getAllPlanets();

    @Query("UPDATE planets SET name = :name, size = :size, description = :description WHERE ID = :id")
    void update(int id, String name,String size, String description);

    @Query("SELECT COUNT(*) FROM planets")
    int countPlanets();

    @Delete
    void delete(Planets planet);



}
