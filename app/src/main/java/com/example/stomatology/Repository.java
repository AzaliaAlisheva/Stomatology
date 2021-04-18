package com.example.stomatology;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private DAO dao;
    private LiveData<List<Entity>> allEntities;

    public Repository(Application application) {
        Database db = Database.getInstance(application);
        dao =  db.dao();
        allEntities = dao.getAllPatients();

    }
    public void insert(Entity entity) {
        new InsertAsyncTask(dao).execute(entity);
    }
    public void update(Entity entity) {
        new UpdateAsyncTask(dao).execute(entity);
    }
    public void delete(Entity entity) {
        new DeleteAsyncTask(dao).execute(entity);
    }
    public void deleteAll() {
        new DeleteAllAT(dao).execute();
    }
    public LiveData<List<Entity>> getAllEntities() {
        return allEntities;
    }
    private static class InsertAsyncTask extends AsyncTask<Entity, Void, Void> {
        private DAO dao;
        private InsertAsyncTask(DAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Entity... entities) {
            dao.insert(entities[0]);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<Entity, Void, Void> {
        private DAO dao;
        private UpdateAsyncTask(DAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Entity... entities) {
            dao.update(entities[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<Entity, Void, Void> {
        private DAO dao;
        private DeleteAsyncTask(DAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Entity... entities) {
            dao.delete(entities[0]);
            return null;
        }
    }
    private static class DeleteAllAT extends AsyncTask<Void, Void, Void> {
        private DAO dao;
        private DeleteAllAT (DAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllEntities();
            return null;
        }
    }
}
