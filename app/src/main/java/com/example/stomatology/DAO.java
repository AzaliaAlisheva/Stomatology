package com.example.stomatology;

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
    @Delete
    void delete(Entity entity);
    @Query("DELETE FROM patients_table")
    void deleteAllPatients();
    @Query("SELECT * FROM patients_table")
    LiveData<List<Entity>> getAllPatients();
}
