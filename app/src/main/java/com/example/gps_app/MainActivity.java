package com.example.gps_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        Button button_signup = findViewById(R.id.signup);
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        EditText et_email, et_password;
        et_email = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        Button b = findViewById(R.id.button_signin);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    et_email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    et_password.setError("password is required");
                    return;
                }
                if (password.length() < 6) {
                    et_password.setError("password Must be 6 characters");
                    return;
                }
                //b.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "logged in Successfully", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                                } else {
                                    Toast.makeText(MainActivity.this, "Error !", Toast.LENGTH_LONG).show();
                                    // b.setVisibility(View.GONE);
                                }
                            }

                        });
            }
        });
        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(t);
            }
        });




    }
}