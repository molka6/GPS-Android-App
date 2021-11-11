package com.example.gps_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
        Button forgetTextLink=findViewById(R.id.button_forgot_password);
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
                                    startActivity(new Intent(getApplicationContext(), IndexActivity.class));
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

        forgetTextLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail=new EditText(v.getContext());
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                builder.setTitle("Reset Password ?");
                builder.setMessage("Enter Your Email To  Reset Link") ;
                builder.setView(resetMail);
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mail=resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(MainActivity.this, "Reset Link Sent To Your Email", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Error ! Reset Link is not Sent", Toast.LENGTH_LONG).show();

                            }
                        }); }
                });

                builder.setNegativeButton("No",null);
                builder.show();
            }
        });

    }
}





