package com.example.stomatology;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAO {
    @Insert
    void insert(Entity entity);

    @Update
    void update(Entity entity);

    @Query("DELETE FROM patients_table WHERE id = :id")
    void delete(int id);

    @Query("DELETE FROM patients_table")
    void deleteAllEntities();

    @Query("SELECT * FROM patients_table WHERE id = :id")
    LiveData<Entity> getById (int id);

    @Query("SELECT * FROM patients_table WHERE name = :name")
    Cursor getId (String name);

    @Query("SELECT * FROM patients_table")
    LiveData<List<Entity>> getAll();
}
