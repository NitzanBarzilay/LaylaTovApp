package com.example.safetogether.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.safetogether.MainLoginActivity;

public class SplashActivity extends AppCompatActivity {

//    private static final int DELAY_IN_MILISEC = 2500;
    private static final int DELAY_IN_MILISEC = 500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainLoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, DELAY_IN_MILISEC);
    }
}
