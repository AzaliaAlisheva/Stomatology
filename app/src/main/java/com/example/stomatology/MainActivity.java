package com.example.stomatology;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    public static final String TAG = "MainActivity";
    public static final int ADD_REQUEST = 1;
    public static final int EDIT_REQUEST = 2;
    private ViewMyModel newVM;
    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar);
        //FLOATING BUTTON
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Form.class);
                startActivityForResult(intent, ADD_REQUEST);
            }
        });
        mListView = (ListView) findViewById(R.id.listview);
        newVM = ViewModelProviders.of(this).get(ViewMyModel.class);
        // ELEMENTS FROM DATABASE
        populateListView();
    }
    // GET NEW ELEMENT
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // ADDING
        if (requestCode == ADD_REQUEST && resultCode == RESULT_OK) {
            String new_name = data.getStringExtra(Form.EXTRA_NAME);
            String new_phone = data.getStringExtra(Form.EXTRA_PHONE);
            String new_age = data.getStringExtra(Form.EXTRA_AGE);
            String new_address = data.getStringExtra(Form.EXTRA_ADDRESS);
            String new_diagnostics = data.getStringExtra(Form.EXTRA_DIAGNOSTICS);
            String new_date = data.getStringExtra(Form.EXTRA_DATE);
            String new_time = data.getStringExtra(Form.EXTRA_TIME);
            Entity entity = new Entity(new_name, new_phone, new_age, new_address, new_diagnostics, new_date, new_time);
            newVM.insert(entity);
            Toast.makeText(this, "Клиент добавлен", Toast.LENGTH_SHORT).show();
        // EDITING
        }else if (requestCode == EDIT_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(Form.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Отклонено", Toast.LENGTH_SHORT).show();
                return;
            }
            String new_name = data.getStringExtra(Form.EXTRA_NAME);
            String new_phone = data.getStringExtra(Form.EXTRA_PHONE);
            String new_age = data.getStringExtra(Form.EXTRA_AGE);
            String new_address = data.getStringExtra(Form.EXTRA_ADDRESS);
            String new_diagnostics = data.getStringExtra(Form.EXTRA_DIAGNOSTICS);
            String new_date = data.getStringExtra(Form.EXTRA_DATE);
            String new_time = data.getStringExtra(Form.EXTRA_TIME);

            Entity entity = new Entity(new_name, new_phone, new_age, new_address, new_diagnostics, new_date, new_time);
            entity.setId(id);
            newVM.update(entity);
            Toast.makeText(this, "Изменения сохранены", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Изменения не сохранены", Toast.LENGTH_SHORT).show();
        }
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");
        Cursor data = newVM.getAll();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            listData.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.item, listData);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on" + name);
                Cursor data = newVM.getId(name);
                int entityID = -1;
                while(data.moveToNext()) {
                    entityID = data.getInt(0);
                }
                if (entityID > -1) {
                    Log.d(TAG, "onItemClick: The ID is: " + entityID);
                    Entity entity = newVM.getById(entityID);
                    Intent intent = new Intent(MainActivity.this, AboutPatient.class);
                    intent.putExtra(Form.EXTRA_ID, entity.getId());
                    intent.putExtra(Form.EXTRA_NAME, entity.getName());
                    intent.putExtra(Form.EXTRA_PHONE, entity.getPhone());
                    intent.putExtra(Form.EXTRA_AGE, entity.getAge());
                    intent.putExtra(Form.EXTRA_ADDRESS, entity.getAddress());
                    intent.putExtra(Form.EXTRA_DIAGNOSTICS, entity.getDiagnostics());
                    intent.putExtra(Form.EXTRA_DATE, entity.getDate());
                    intent.putExtra(Form.EXTRA_TIME, entity.getTime());
                    startActivityForResult(intent, EDIT_REQUEST);
                }else{
                    Toast.makeText(MainActivity.this, "В базе клиента нет", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
