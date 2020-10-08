package com.example.safetogether;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.safetogether.R;

public class MainLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView image = findViewById(R.id.mainPageLogo);
        int height = getScreenHeight();
        double imageRelativeSize = height * 0.12;
        image.getLayoutParams().height = 20;
        image.getLayoutParams().height = (int) imageRelativeSize;
        image.getLayoutParams().width= (int) imageRelativeSize;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

}

