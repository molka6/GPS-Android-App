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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView e1 = findViewById(R.id.et_username);
        TextView e2 = findViewById(R.id.et_password);
        TextView e3 = findViewById(R.id.signin);
        TextView button_signup = findViewById(R.id.signup);
        TextView forgetTextLink=findViewById(R.id.button_forgot_password);

        FirebaseAuth fAuth = FirebaseAuth.getInstance();

        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = e1.getText().toString().trim();
                String password = e2.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    e1.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    e2.setError("password is required");
                    return;
                }
                if (password.length() < 6) {
                    e2.setError("password Must be 6 characters");
                    return;
                }
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "logged in Successfully", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(), IndexActivity.class));
                                } else {
                                    Toast.makeText(LoginActivity.this, "Error !", Toast.LENGTH_LONG).show();
                                }
                            }
                        });





            }
        });




        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(LoginActivity.this, RegisterActivity.class);
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
                                Toast.makeText(LoginActivity.this, "Reset Link Sent To Your Email", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Error ! Reset Link is not Sent", Toast.LENGTH_LONG).show();

                            }
                        }); }
                });

                builder.setNegativeButton("No",null);
                builder.show();
            }
        });





    }
}