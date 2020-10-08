package com.example.safetogether;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.safetogether.R;
import com.example.safetogether.guest.CallHelpActivity;
import com.example.safetogether.guest.LoginActivity;
import com.example.safetogether.guest.MainActivity;

public class MainLoginActivity extends AppCompatActivity {

    private ImageView image;
    private Button crewButton;
    private Button guestButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVars();
        int height = getScreenHeight();
        double imageRelativeSize = height * 0.12;
        image.getLayoutParams().height = 20;
        image.getLayoutParams().height = (int) imageRelativeSize;
        image.getLayoutParams().width= (int) imageRelativeSize;

        setOnClickListeners();
    }

    private void initVars() {
        image = findViewById(R.id.mainPageLogo);
        guestButton = findViewById(R.id.guestLoginButton);
        crewButton = findViewById(R.id.CrewLoginButton);
    }

    private void setOnClickListeners() {
        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLoginActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        crewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainLoginActivity.this, com.example.safetogether.crew.LoginActivity.class);
                Intent intent = new Intent(MainLoginActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

}

