package com.example.stomatology;
import android.app.Application;
import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class ViewMyModel extends AndroidViewModel {
    private myRepository rep;

    public ViewMyModel(@NonNull Application application) {
        super(application);
        rep = new myRepository(application);
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

    public Entity getById(int ID) {
        return rep.getByID(ID);
    }

    public Cursor getId(String name) {
        return rep.getId(name);
    }

    public Cursor getAll() {
        return rep.getAll();
    }
}
