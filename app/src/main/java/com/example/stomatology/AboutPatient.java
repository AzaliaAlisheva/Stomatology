package com.example.stomatology;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutPatient extends AppCompatActivity {
    public static final String TAG = "AboutPatient";
    public static final int EDIT_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_patient);

        Button btnEdit = (Button) findViewById(R.id.edit_button);
        Button btnDelete = (Button) findViewById(R.id.delete_button);
        TextView name = (TextView) findViewById(R.id.name_Editor);
        TextView phone = (TextView) findViewById(R.id.phone_Editor);
        TextView age = (TextView) findViewById(R.id.age_Editor);
        TextView address = (TextView) findViewById(R.id.address_Editor);
        TextView diagnostics = (TextView) findViewById(R.id.diagnostics_Editor);
        TextView date = (TextView) findViewById(R.id.editionDate);
        TextView time = (TextView) findViewById(R.id.editionTime);

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
                intent.putExtra(Form.EXTRA_NAME, selectedName);
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
}
