package com.example.stomatology;
import android.app.Application;
import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewMyModel extends AndroidViewModel {
    private myRepository rep;
    private LiveData<List<Entity>> entities;

    public ViewMyModel(@NonNull Application application) {
        super(application);
        rep = new myRepository(application);
        entities = updateEntityList();
    }

    public void insert(Entity entity) throws InterruptedException {
        rep.insert(entity);
    }

    public void update(Entity entity) {
        rep.update(entity);
    }

    public void delete(int id) {
        rep.delete(id);
    }

    public void deleteAllEntities() {
        rep.deleteAll();
    }

    public LiveData<Entity> getById(int ID) {
        return rep.getByID(ID);
    }

    public Cursor getId(String name) {
        return rep.getId(name);
    }

    public LiveData<List<Entity>> getAllEntity() {
        if (entities == null){
            entities = updateEntityList();
        }
        return entities;
    }

    public LiveData<List<Entity>> updateEntityList(){
        return rep.getAllEntities();
    }
}
