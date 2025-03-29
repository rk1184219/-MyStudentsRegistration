package com.example.mystudent;

import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    TextView tvDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvDetails = findViewById(R.id.tvDetails);

        // Change screen color to black
        View rootView = findViewById(android.R.id.content);
        rootView.setBackgroundColor(android.graphics.Color.YELLOW);

        // Retrieve data passed from MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String details = extras.getString("details");
            tvDetails.setText(details);
        }
        Toast.makeText(this, "Thank you!rahul kumar", Toast.LENGTH_SHORT).show();
    }
}