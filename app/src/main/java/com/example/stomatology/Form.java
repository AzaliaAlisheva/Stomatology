package com.example.stomatology;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class Form extends AppCompatActivity {
    public static final String TAG = "Form";

    private DatePickerDialog pickerD;
    private TimePickerDialog pickerT;
    private TextInputLayout dater;
    private TextInputEditText editD;
    private TextInputLayout timer;
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

        setTitle("Карточка клиента");

        dater = findViewById(R.id.filledTextField_date);
        editD = findViewById(R.id.editionDate);
        dater.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
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

        timer = findViewById(R.id.filledTextField_time);
        editT = findViewById(R.id.editionTime);
        timer.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                // date picker dialog
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
        name = findViewById(R.id.name_Editor);
        phone = findViewById(R.id.phone_Editor);
        age = findViewById(R.id.age_Editor);
        address = findViewById(R.id.address_Editor);
        diagnostics = findViewById(R.id.diagnostics_Editor);
        Button saver = (Button) findViewById(R.id.save_button);
        saver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_name = name.getText().toString();
                String new_phone = phone.getText().toString();
                String new_age = age.getText().toString();
                String new_address = address.getText().toString();
                String new_diagnostics = diagnostics.getText().toString();
                String new_date = editD.getText().toString();
                String new_time = editD.getText().toString();
                if (new_name.equals("")) {
                    Toast.makeText(getBaseContext(), "Напишите ФИО", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getBaseContext(), "Клиент добавлен", Toast.LENGTH_SHORT).show();
                    Intent r_intent = new Intent();
                    r_intent.putExtra("EXTRA_NAME", new_name);
                    r_intent.putExtra("EXTRA_PHONE", new_phone);
                    r_intent.putExtra("EXTRA_AGE", new_age);
                    r_intent.putExtra("EXTRA_ADDRESS", new_address);
                    r_intent.putExtra("EXTRA_DIAGNOSTICS", new_diagnostics);
                    r_intent.putExtra("EXTRA_DATE", new_date);
                    r_intent.putExtra("EXTRA_TIME", new_time);
                    setResult(RESULT_OK, r_intent);
                    finish();
                 }
            }
        });

        
    }

}
