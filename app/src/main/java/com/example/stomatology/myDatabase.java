package com.example.stomatology;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = {Entity.class}, version = 1, exportSchema = false)
public abstract class myDatabase extends RoomDatabase {
    private static myDatabase instance;
    public abstract DAO dao();

    public static synchronized myDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), myDatabase.class,
                    "patients_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsynkTask(instance).execute();
        }
    };

    private static class PopulateDbAsynkTask extends AsyncTask<Void, Void, Void> {
        private DAO dao;
        private PopulateDbAsynkTask(myDatabase db) {
            dao = db.dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dao.insert(new Entity("name 3", "3", "3", "address 2", "2", "2", "2"));
            dao.insert(new Entity("name 4", "1", "1", "address 1", "1", "1", "1"));
            dao.insert(new Entity("name 2", "2", "2", "address 2", "2", "2", "2"));
            return null;
        }
    }
}
