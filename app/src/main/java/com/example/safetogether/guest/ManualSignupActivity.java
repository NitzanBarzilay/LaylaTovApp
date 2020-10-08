package com.example.safetogether.guest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.safetogether.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ManualSignupActivity extends AppCompatActivity {

    private static final String TAG = "ManualSignupActivity";

    private FirebaseAuth auth;
    private DatabaseReference reference;

    private EditText inputEmail;
    private EditText inputUsername;
    private EditText inputPassword;
    private Button signupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_manual_signup_activity);
        initVars();
        setOnClickListener();
    }

    private void initVars() {
        auth = FirebaseAuth.getInstance();
        inputEmail = findViewById(R.id.input_email);
        inputUsername = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_password);
        signupButton = findViewById(R.id.btn_signup);
    }

    private void setOnClickListener() {
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = inputUsername.getText().toString();
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                register(username, email, password);
            }
        });
    }


    /**
     * Registers the user to our database of - guess what - guest users.
     */
    private void register(final String username, String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()) {
                         FirebaseUser firebaseUser = auth.getCurrentUser();
                         if (firebaseUser == null) {
                             Log.d(TAG, "register - onComplete - 1: null firebaseUser ERROR");
                             return;
                         }
                         String userID = firebaseUser.getUid();
                         reference = FirebaseDatabase.getInstance().getReference("Users").child(userID);

                         HashMap<String, String> hashMap = new HashMap<>();
                         hashMap.put("id", userID);
                         hashMap.put("username", username);
                         hashMap.put("imageURL", "default");
                         hashMap.put("Type", "guest");

                         reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                             @Override
                             public void onComplete(@NonNull Task<Void> task) {
                                 if (task.isSuccessful()) {
                                     Intent intent = new Intent(ManualSignupActivity.this, SelfieActivity.class);
                                     startActivity(intent);
                                     finish();
                                 } else {
                                     Log.d(TAG, "register - onComplete - 2: task failed");
                                 }
                             }
                         });
                     } else {
                         Log.d(TAG, "register - onComplete - 1: task failed");
                     }
                    }
                });
    }

}