package com.example.safetogether.guest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Scroller;

import androidx.appcompat.app.AppCompatActivity;

import com.example.safetogether.R;

public class NotifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        EditText et = (EditText) findViewById(R.id.et);
        et.setScroller(new Scroller(getApplicationContext()));
        et.setVerticalScrollBarEnabled(true);

    }

    public void sendMessage() {
        Intent intent = new Intent(NotifyActivity.this, FeedbackAcceptedActivity.class);
        startActivity(intent);
        finish();
    }
}
