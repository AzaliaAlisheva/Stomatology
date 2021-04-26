package com.example.stomatology;
import android.app.Application;
import android.database.Cursor;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class myRepository {
    private DAO dao;
    private LiveData<List<Entity>> entities;


    public myRepository(Application application) {
        myDatabase db = myDatabase.getInstance(application);
        dao =  db.dao();
        entities = dao.getAll();

    }

    LiveData<List<Entity>> getAllEntities() {
        return entities;
    }

    public void insert(final Entity entity) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                dao.insert(entity);
            }
        });
        executorService.shutdown();
    }

    public void update(final Entity entity) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                dao.update(entity);
            }
        });
        executorService.shutdown();
    }

    public void delete(final Entity entity) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                dao.delete(entity);
            }
        });
        executorService.shutdown();
    }

    public void deleteAll() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                dao.deleteAllEntities();
            }
        });
        executorService.shutdown();
    }

    public LiveData<Entity> getByID(int ID) {
        return dao.getById(ID);
    }

    public Cursor getId(String name) {
        return dao.getId(name);
    }

}
