package com.example.stomatology;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Calendar;

public class Form extends AppCompatActivity {
    public static final String TAG = "Form";
    public static final String EXTRA_ID =
            "com.example.stomatology.EXTRA_ID";
    public static final String EXTRA_NAME =
            "com.example.stomatology.EXTRA_NAME";
    public static final String EXTRA_PHONE =
            "com.example.stomatology.EXTRA_PHONE";
    public static final String EXTRA_AGE =
            "com.example.stomatology.EXTRA_AGE";
    public static final String EXTRA_ADDRESS =
            "com.example.stomatology.EXTRA_ADDRESS";
    public static final String EXTRA_DIAGNOSTICS =
            "com.example.stomatology.EXTRA_DIAGNOSTICS";
    public static final String EXTRA_DATE =
            "com.example.stomatology.EXTRA_DATE";
    public static final String EXTRA_TIME =
            "com.example.stomatology.EXTRA_TIME";

    private DatePickerDialog pickerD;
    private TimePickerDialog pickerT;
    private TextInputEditText editD;
    private TextInputEditText editT;
    private TextInputEditText name;
    private TextInputEditText phone;
    private TextInputEditText age;
    private TextInputEditText address;
    private TextInputEditText diagnostics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        TextInputLayout dater = findViewById(R.id.filledTextField_date);
        editD = findViewById(R.id.editionDate);
        dater.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                pickerD = new DatePickerDialog(Form.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editD.setText(String.format("%02d/%02d/%d", dayOfMonth, monthOfYear + 1, year));
                            }
                        }, year, month, day);
                pickerD.show();
            }
        });

        TextInputLayout timer = findViewById(R.id.filledTextField_time);
        editT = findViewById(R.id.editionTime);
        timer.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                pickerT = new TimePickerDialog(Form.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hour, int minute) {
                                editT.setText(String.format("%02d:%02d", hour, minute));
                            }
                        }, hour, minute, android.text.format.DateFormat.is24HourFormat(Form.this));
                pickerT.show();
            }
        });

        name = findViewById(R.id.name_Editor); //INPUT?
        phone = findViewById(R.id.phone_Editor);
        age = findViewById(R.id.age_Editor);
        address = findViewById(R.id.address_Editor);
        diagnostics = findViewById(R.id.diagnostics_Editor);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) { //ЗАЧЕМ ТОГДА EDIT_REQUEST?
            setTitle("Редактирование");
            name.setText(intent.getStringExtra(EXTRA_NAME));
            phone.setText(intent.getStringExtra(EXTRA_PHONE));
            age.setText(intent.getStringExtra(EXTRA_AGE));
            address.setText(intent.getStringExtra(EXTRA_ADDRESS));
            diagnostics.setText(intent.getStringExtra(EXTRA_DIAGNOSTICS));
            editD.setText(intent.getStringExtra(EXTRA_DATE));
            editT.setText(intent.getStringExtra(EXTRA_TIME));
        } else {
            setTitle("Карточка клиента");
        }

        Button saver = (Button) findViewById(R.id.save_button);
        saver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEntity();
            }
        });
    }

    private void saveEntity() {
        String new_name = name.getText().toString();
        String new_phone = phone.getText().toString();
        String new_age = age.getText().toString();
        String new_address = address.getText().toString();
        String new_diagnostics = diagnostics.getText().toString();
        String new_date = editD.getText().toString();
        String new_time = editT.getText().toString();

        if (new_name.trim().isEmpty()) {
            Toast.makeText(getBaseContext(), "Напишите ФИО", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Form.this,AboutPatient.class);
        intent.putExtra(EXTRA_NAME, new_name);
        intent.putExtra(EXTRA_PHONE, new_phone);
        intent.putExtra(EXTRA_AGE, new_age);
        intent.putExtra(EXTRA_ADDRESS, new_address);
        intent.putExtra(EXTRA_DIAGNOSTICS, new_diagnostics);
        intent.putExtra(EXTRA_DATE, new_date);
        intent.putExtra(EXTRA_TIME, new_time);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        Log.d("ID_FORM", id+"");
        if (id != -1) {
            intent.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, intent);
        finish();
    }

}
