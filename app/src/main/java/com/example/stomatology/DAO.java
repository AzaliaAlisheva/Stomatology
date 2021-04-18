package com.example.stomatology;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DAO {
    @Insert
    void insert(Entity entity);
    @Update
    void update(Entity entity);
    @Delete
    void delete(Entity entity);
    @Query("DELETE FROM patients_table")
    void deleteAllEntities();
    @Query("SELECT * FROM patients_table WHERE ID = :id")
    Entity getById (int id);
    @Query("SELECT * FROM patients_table WHERE NAME = :name")
    Cursor getId (String name);
    @Query("SELECT * FROM patients_table")
    Cursor getAll();
}
