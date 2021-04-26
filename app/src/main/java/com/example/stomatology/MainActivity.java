package com.example.stomatology;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
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
import java.util.List;

public class MainActivity extends AppCompatActivity{
    public static final String TAG = "MainActivity";
    public static final int ADD_REQUEST = 1;
    public static final int EDIT_REQUEST = 2;
    private ViewMyModel newVM;
    private ListView mListView;
    private EntityAdapter adapter;
    static ArrayList<Entity> list = new ArrayList<>();

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
        mListView = findViewById(R.id.listview);
        newVM = ViewModelProviders.of(this).get(ViewMyModel.class);
        // ELEMENTS FROM DATABASE
        populateListView();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("RRR",position+"");
                loadFirst(position+1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MainActivity.ADD_REQUEST && resultCode == MainActivity.RESULT_OK) {
            String new_name = data.getStringExtra(Form.EXTRA_NAME);
            String new_phone = data.getStringExtra(Form.EXTRA_PHONE);
            String new_age = data.getStringExtra(Form.EXTRA_AGE);
            String new_address = data.getStringExtra(Form.EXTRA_ADDRESS);
            String new_diagnostics = data.getStringExtra(Form.EXTRA_DIAGNOSTICS);
            String new_date = data.getStringExtra(Form.EXTRA_DATE);
            String new_time = data.getStringExtra(Form.EXTRA_TIME);
            Entity entity = new Entity(new_name, new_phone, new_age, new_address, new_diagnostics, new_date, new_time);
            try {
                newVM.insert(entity);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Клиент добавлен", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == MainActivity.EDIT_REQUEST) {
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

        }

    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");
        newVM.getAllEntity().observe(this, new Observer<List<Entity>>() {
            @Override
            public void onChanged(@Nullable List<Entity> entities) {
                if(entities != null) {
                    adapter = new EntityAdapter(getApplicationContext(),  entities);
                    mListView.setAdapter(adapter);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
    private void loadFirst(int i) {
        newVM.getById(i).observe(this, new Observer<Entity>() {
            @Override
            public void onChanged(Entity entity) {
                Intent intent = new Intent(MainActivity.this, AboutPatient.class);
                intent.putExtra(Form.EXTRA_ID, entity.getId());
                intent.putExtra(Form.EXTRA_NAME, entity.getName());
                intent.putExtra(Form.EXTRA_PHONE, entity.getPhone());
                intent.putExtra(Form.EXTRA_AGE, entity.getAge());
                intent.putExtra(Form.EXTRA_ADDRESS, entity.getAddress());
                intent.putExtra(Form.EXTRA_DIAGNOSTICS, entity.getDiagnostics());
                intent.putExtra(Form.EXTRA_DATE, entity.getDate());
                intent.putExtra(Form.EXTRA_TIME, entity.getTime());
                startActivityForResult(intent, MainActivity.EDIT_REQUEST);
            }
        });

    }
}
