package com.example.stomatology;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private Repository rep;
    private LiveData<List<Entity>> allEntities;
    public ViewModel(@NonNull Application application) {
        super(application);
        rep = new Repository(application);
        allEntities = rep.getAllEntities();
    }

    public void insert(Entity entity) {
        rep.insert(entity);
    }
    public void update(Entity entity) {
        rep.update(entity);
    }
    public void delete(Entity entity) {
        rep.delete(entity);
    }
    public void deleteAllEntities() {
        rep.deleteAll();
    }

    public LiveData<List<Entity>> getAllEntities() {
        return allEntities;
    }
}
