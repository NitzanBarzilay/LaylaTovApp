package com.example.safetogether.guest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.safetogether.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private FirebaseAuth auth;
    private DatabaseReference reference;

    private EditText inputEmail;
    private EditText inputPassword;
    private Button loginButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_login_activity);
        initVars();
        setOnClickListener();
    }

    private void initVars() {
        auth = FirebaseAuth.getInstance();
        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);
        loginButton = findViewById(R.id.btn_login);
        signupButton = findViewById(R.id.btn_signup);
    }

    private void setOnClickListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String email = inputEmail.getText().toString();
//                String password = inputPassword.getText().toString();
//                login(email, password);
                Intent intent = new Intent(LoginActivity.this, SelfieActivity.class);
                startActivity(intent);
                finish();

            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ManualSignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void login(String email, String password) {
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            Log.d(TAG, "login: bad email/password");
            Toast.makeText(LoginActivity.this, "Error logging in",
                    Toast.LENGTH_LONG).show();
            return;
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(LoginActivity.this, SelfieActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Error logging in",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
