package com.example.gps_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        TextView t=findViewById(R.id.GO);
        TextView t2=findViewById(R.id.GO2);
        TextView t3=findViewById(R.id.GO3);

        ImageView x1=findViewById(R.id.logout);


        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(IndexActivity .this, MapsActivity.class);
                startActivity(t);
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(IndexActivity .this, MapsActivity.class);
                startActivity(t);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(IndexActivity .this, MapsActivity.class);
                startActivity(t);
            }
        });

        x1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent t=new Intent(IndexActivity.this , MainActivity.class) ;
                startActivity(t);
                finish();
            }
        });



    }
}