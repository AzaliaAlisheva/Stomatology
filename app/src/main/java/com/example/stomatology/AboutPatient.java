package com.example.stomatology;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutPatient extends AppCompatActivity {
    public static final String TAG = "AboutPatient";
    public static final int EDIT_REQUEST = 2;
    private TextView name, phone, age, address, diagnostics, date, time;
    private ViewMyModel newVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_patient);
        Button btnEdit = findViewById(R.id.edit_button);
        Button btnDelete = findViewById(R.id.delete_button);
        name = findViewById(R.id.name_Editor);
        phone = findViewById(R.id.phone_Editor);
        age = findViewById(R.id.age_Editor);
        address = findViewById(R.id.address_Editor);
        diagnostics = findViewById(R.id.diagnostics_Editor);
        date = findViewById(R.id.editionDate);
        time = findViewById(R.id.editionTime);
        newVM = ViewModelProviders.of(this).get(ViewMyModel.class);

        Intent receivedIntent = getIntent();
        final int selectedID = receivedIntent.getIntExtra(Form.EXTRA_ID, -1);
        final String selectedName = receivedIntent.getStringExtra(Form.EXTRA_NAME);
        final String selectedPhone = receivedIntent.getStringExtra(Form.EXTRA_PHONE);
        final String selectedAge = receivedIntent.getStringExtra(Form.EXTRA_AGE);
        final String selectedAddress = receivedIntent.getStringExtra(Form.EXTRA_ADDRESS);
        final String selectedDiagnostics = receivedIntent.getStringExtra(Form.EXTRA_DIAGNOSTICS);
        final String selectedDate = receivedIntent.getStringExtra(Form.EXTRA_DATE);
        final String selectedTime = receivedIntent.getStringExtra(Form.EXTRA_TIME);

        name.setText(selectedName);
        phone.setText(selectedPhone);
        age.setText(selectedAge);
        address.setText(selectedAddress);
        diagnostics.setText(selectedDiagnostics);
        date.setText(selectedDate);
        time.setText(selectedTime);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutPatient.this, Form.class);
                intent.putExtra(Form.EXTRA_ID, selectedID);
                intent.putExtra(Form.EXTRA_NAME, name.getText().toString());
                intent.putExtra(Form.EXTRA_PHONE, selectedPhone);
                intent.putExtra(Form.EXTRA_AGE, selectedAge);
                intent.putExtra(Form.EXTRA_ADDRESS, selectedAddress);
                intent.putExtra(Form.EXTRA_DIAGNOSTICS, selectedDiagnostics);
                intent.putExtra(Form.EXTRA_DATE, selectedDate);
                intent.putExtra(Form.EXTRA_TIME, selectedTime);
                startActivityForResult(intent, EDIT_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if (resultCode == RESULT_OK) {
                String name_from_edit = data.getStringExtra(Form.EXTRA_NAME);
                name.setText(name_from_edit);
                Log.d("RRR",name_from_edit);

            }

    }
}
