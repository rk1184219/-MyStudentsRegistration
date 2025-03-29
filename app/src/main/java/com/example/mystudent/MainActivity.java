package com.example.mystudent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editName, editPhone, editEmail, editDOB, editLoginID;
    Button buttonSubmit, buttonReset;

    // SharedPreferences for storing data
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);
        editDOB = findViewById(R.id.editDOB);
        editLoginID = findViewById(R.id.editLoginID);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonReset = findViewById(R.id.buttonReset);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE);

        // Submit Button Click Listener
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();
                String email = editEmail.getText().toString().trim();
                String dob = editDOB.getText().toString().trim();
                String loginID = editLoginID.getText().toString().trim();

                // Check if any field is empty
                if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || dob.isEmpty() || loginID.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all details!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Retrieve previously stored details
                String previousDetails = sharedPreferences.getString("AllDetails", "");

                // Add new details to the previous details
                String newDetails = "Name: " + name +
                        "\nPhone: " + phone +
                        "\nEmail: " + email +
                        "\nDOB: " + dob +
                        "\nLogin ID: " + loginID + "\n\n";

                String updatedDetails =  newDetails;

                // Save updated details to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("AllDetails", updatedDetails);
                editor.apply();

                // Navigate to DetailsActivity with updated details
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("details", updatedDetails);
                startActivity(intent);
            }
        });

        // Reset Button Click Listener
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editName.setText("");
                editPhone.setText("");
                editEmail.setText("");
                editDOB.setText("");
                editLoginID.setText("");

                // Clear SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
            }
        });
    }
}