package com.example.dx1221_elearning_wk3.main;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dx1221_elearning_wk3.R;

public class AccordionActivity extends AppCompatActivity implements View.OnClickListener {
    private Button _backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helppage);

        TextView header1 = findViewById(R.id.accordion_header_1);
        TextView description1 = findViewById(R.id.accordion_description_1);

        TextView header2 = findViewById(R.id.accordion_header_2);
        TextView description2 = findViewById(R.id.accordion_description_2);

        _backButton = findViewById(R.id.back_btn);
        _backButton.setOnClickListener(this);

        description1.setVisibility(View.GONE);
        description2.setVisibility(View.GONE);

        header1.setOnClickListener(v -> toggleVisibility(description1));
        header2.setOnClickListener(v -> toggleVisibility(description2));
    }

    @Override
    public void onClick(View v) {
        if (v == _backButton) {
            startActivity(new Intent(this, MainMenu.class));
        }
    }

    private void toggleVisibility(View view) {
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).setDuration(300).start();
        } else {
            ObjectAnimator.ofFloat(view, "alpha", 1f, 0f).setDuration(300).start();
            view.postDelayed(() -> view.setVisibility(View.GONE), 300);
        }
    }
}
