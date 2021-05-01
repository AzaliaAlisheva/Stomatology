package com.example.stomatology;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    public static final String TAG = "MainActivity";
    public static final int ADD_REQUEST = 1;
    public static final int EDIT_REQUEST = 2;
    public static final int RESULT_DELETE = 2;
    private ViewMyModel newVM;
    private RecyclerView mRecyclerView;
    private EntityAdapter adapter;
    EditText Search;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        newVM.deleteAllEntities();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Form.class);
                startActivityForResult(intent, ADD_REQUEST);
            }
        });
        //LIST VIEW
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        newVM = ViewModelProviders.of(this).get(ViewMyModel.class);
        Search = (EditText) findViewById(R.id.input);
        populateListView();
        Search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2,
                                      int arg3) {
                // When user changed the Text
                MainActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MainActivity.ADD_REQUEST && resultCode == MainActivity.RESULT_OK) {
            //Зачем так много считывать? Нужно только имя, а entity там добавим
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
        if (requestCode == MainActivity.EDIT_REQUEST && resultCode == MainActivity.RESULT_OK) {
            int id = data.getIntExtra(Form.EXTRA_ID, -1);
            Log.d("ID_MAIN", id+"");
            if (id != -1) {
                String new_name = data.getStringExtra(Form.EXTRA_NAME);
                Log.d("RRR", new_name);
                //Зачем так много считывать? Нужно только имя, а entity там в db добавим
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
                Toast.makeText(this, "Отклонено", Toast.LENGTH_SHORT).show();
            }
        }
        if (resultCode == RESULT_DELETE) {
            int id = data.getIntExtra(Form.EXTRA_ID, -1);
            Log.d("ID_MAIN", id+"");
            if (id != -1) {
                newVM.delete(id);
            }
        }
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");
        //ArrayList<String> listData = new ArrayList<>();
        newVM.getAllEntity().observe(this, new Observer<List<Entity>>() {
            @Override
            public void onChanged(@Nullable List<Entity> entities) {
                if(entities != null) {
                    adapter = new EntityAdapter(getApplicationContext(),  entities, MainActivity.this);
                    mRecyclerView.setAdapter(adapter);
                    adapter.sort();
                }
                //adapter.notifyDataSetChanged();
            }
        });
    }
    public void loadFirst(Entity entity) {
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
}
