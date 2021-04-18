package com.example.stomatology;
import android.app.Application;
import android.database.Cursor;
import android.os.AsyncTask;

public class myRepository {
    private DAO dao;

    public myRepository(Application application) {
        myDatabase db = myDatabase.getInstance(application);
        dao =  db.dao();
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

    public Entity getByID(int ID) {
        return dao.getById(ID);
    }

    public Cursor getId(String name) {
        return dao.getId(name);
    }

    public Cursor getAll() {
        return dao.getAll();
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
