package com.example.planets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button getStartedBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // navigate to Planets List on Button Click
        getStartedBtn = findViewById(R.id.getStartedBtn);
        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create the intent to navigate to target activity
                Intent intent = new Intent(MainActivity.this, PlanetsListActivity.class);
                startActivity(intent);
            }
        });
    }

}