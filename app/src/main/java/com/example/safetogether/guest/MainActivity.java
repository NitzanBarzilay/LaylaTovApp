package com.example.safetogether.guest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.safetogether.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button resourcesButton;
    private Button crewFeedbackButton;
    private Button helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_activity_main);
        initVars();
        setOnClickListeners();
    }

    private void initVars() {
        resourcesButton = findViewById(R.id.resourcesButton);
        crewFeedbackButton = findViewById(R.id.crewFeedbackButton);
        helpButton = findViewById(R.id.helpButton);
    }

    private void setOnClickListeners() {
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CallHelpActivity.class);
                startActivity(intent);
            }
        });

        resourcesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResourcesActivity.class);
                startActivity(intent);
            }
        });

        crewFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotifyActivity.class);
                startActivity(intent);
            }
        });
    }

}
