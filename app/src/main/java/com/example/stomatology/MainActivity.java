package com.example.stomatology;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.text.Editable;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity{
    //public static final int ADD_REQUEST = 1;
    private ViewModel newVM;
//    private ListView listview;
//    public ArrayList <String> arrayList;
//    public ArrayAdapter<String> adapter;
//    EditText Search;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final EntityAdapter adapter = new EntityAdapter();
        recyclerView.setAdapter(adapter);

        newVM = ViewModelProviders.of(this).get(ViewModel.class);
        newVM.getAllEntities().observe(this, new Observer<List<Entity>>() {
            @Override
            public void onChanged(@Nullable List<Entity> entities) {
                //update Recycle
                adapter.setEntities(entities);
            }
        });

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, Form.class);
//                startActivityForResult(intent, ADD_REQUEST);
//            }
//        });
//        String[] items = {};
//
//        listview = (ListView) findViewById(R.id.listview);
//        Search = (EditText) findViewById(R.id.input);
//        arrayList = new ArrayList<>(Arrays.asList(items));
//        adapter = new ArrayAdapter<>(this, R.layout.item, R.id.item,
//                arrayList);
//        listview.setAdapter(adapter);
//        Search.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence cs, int arg1, int arg2,
//                                      int arg3) {
//                // When user changed the Text
//                MainActivity.this.adapter.getFilter().filter(cs);
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence arg0, int arg1,
//                                          int arg2, int arg3) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable arg0) {
//                // TODO Auto-generated method stub
//            }
//        });
//
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Object listItem = listview.getItemAtPosition(position);
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == ADD_REQUEST) {
//            if (resultCode == RESULT_OK) {
//                String new_name = data.getStringExtra("EXTRA_NAME");
//                String new_phone = data.getStringExtra("EXTRA_PHONE");
//                String new_age = data.getStringExtra("EXTRA_AGE");
//                String new_address = data.getStringExtra("EXTRA_ADDRESS");
//                String new_diagnostics = data.getStringExtra("EXTRA_DIAGNOSTICS");
//                String new_date = data.getStringExtra("EXTRA_DATE");
//                String new_time = data.getStringExtra("EXTRA_TIME");
//                Entity entity = new Entity(new_name, new_phone, new_age, new_address, new_diagnostics, new_date, new_time);
//                newVM.insert(entity);
//                arrayList.add(new_name);
//                Collections.sort(arrayList, new Comparator<String>() {
//                    @Override
//                    public int compare(String o1, String o2) {
//                        return o1.compareTo(o2);
//                    }
//                });
//                adapter.notifyDataSetChanged();
//            }
//        }
    }
}
