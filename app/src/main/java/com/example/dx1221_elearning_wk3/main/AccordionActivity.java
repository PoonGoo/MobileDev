package com.example.dx1221_elearning_wk3.main;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dx1221_elearning_wk3.R;

public class AccordionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helppage); // Ensure the XML is named `activity_accordion`

        // Find views for Accordion Item 1
        TextView header1 = findViewById(R.id.accordion_header_1);
        TextView description1 = findViewById(R.id.accordion_description_1);

        // Find views for Accordion Item 2
        TextView header2 = findViewById(R.id.accordion_header_2);
        TextView description2 = findViewById(R.id.accordion_description_2);

        // Set onClickListener for Header 1
        header1.setOnClickListener(v -> toggleVisibility(description1));

        // Set onClickListener for Header 2
        header2.setOnClickListener(v -> toggleVisibility(description2));
    }

    // Helper method to toggle visibility
    private void toggleVisibility(View view) {
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}