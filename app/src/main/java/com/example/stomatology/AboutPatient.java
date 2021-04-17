package com.example.stomatology;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AboutPatient extends AppCompatActivity {
    public static final String TAG = "AboutPatient";
    private  Button btnSave, btnDelete;
    DatabaseHelper mDatabaseHelper;
    private String selectedName;
    private int selectedID;
    private EditText editable_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_patient);
        btnSave = (Button) findViewById(R.id.save_button);
        btnDelete = (Button) findViewById(R.id.editTextField_name);
        editable_item = (EditText) findViewById(R.id.name_Editor);
        mDatabaseHelper = new DatabaseHelper(this);
        Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id", -1);
        selectedName = receivedIntent.getStringExtra("name");
        editable_item.setText(selectedName);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                if(!item.equals("")) {
                    mDatabaseHelper.updateName(item, selectedID, selectedName);
                }else{
                    //you must enter a name
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID, selectedName);
                editable_item.setText("");
                //removed from database
            }
        });
    }
}
