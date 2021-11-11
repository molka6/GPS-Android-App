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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ListView vehicule;
        String[] mvehicule={"voiture1","voiture2","voiture3"};
            vehicule=(ListView) findViewById(R.id.vehicule);
            ArrayAdapter<String> listAdapter=new ArrayAdapter<String>(
                    this, android.R.layout.simple_list_item_1,mvehicule);
            vehicule.setAdapter(listAdapter);

            vehicule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Intent t = new Intent(IndexActivity.this, MapsActivity.class);
                    startActivity(t);
                }
            });
        }@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item2: {
                FirebaseAuth.getInstance().signOut();
                Intent t=new Intent(IndexActivity.this , MainActivity.class) ;
                startActivity(t);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }





}