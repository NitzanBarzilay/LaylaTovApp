package com.example.safetogether.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.safetogether.MainLoginActivity;
import com.example.safetogether.R;

public class SplashActivity extends AppCompatActivity {

//    private static final int DELAY_IN_MILISEC = 2500;
    private static final int DELAY_IN_MILISEC = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainLoginActivity.class);
//                Intent intent = new Intent(SplashActivity.this, YourActivityHere!.class);
                startActivity(intent);
                finish();
            }
        }, DELAY_IN_MILISEC);
    }
}
