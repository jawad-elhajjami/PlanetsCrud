package com.example.planets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planets.Adapters.PlanetsListAdapter;
import com.example.planets.Models.Planets;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddPlanetActivity extends AppCompatActivity {

    TextInputEditText planet_description_value, planet_name_value, planet_size_value;
    TextInputLayout planet_name_input, planet_size_input, planet_desc_input;
    Button createPlanetBtn;
    Planets planet;
    Boolean isOldPlanet = false;
    TextView formTitleTxt;
    PlanetsListAdapter planetsListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_planet);

        createPlanetBtn = findViewById(R.id.createPlanetBtn);
        planet_name_value = findViewById(R.id.planet_name_value);
        planet_size_value = findViewById(R.id.planet_size_value);
        planet_description_value = findViewById(R.id.planet_description_value);
        formTitleTxt = findViewById(R.id.formTitleTxt);

        planet_name_value.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                planet_size_value.requestFocus();
                return true;
            }
            return false;
        });

        planet_size_value.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                planet_description_value.requestFocus();
                return true;
            }
            return false;
        });

        planet =  new Planets();
        try {
            planet = (Planets) getIntent().getSerializableExtra("old_planet");
            formTitleTxt.setText("Update planet (" + planet.getName() + ")");
            createPlanetBtn.setText("Update planet");
            planet_name_value.setText(planet.getName());
            planet_size_value.setText(planet.getSize());
            planet_description_value.setText(planet.getDescription());
            isOldPlanet = true;

        }catch (Exception e){
            e.printStackTrace();
        }

        createPlanetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = planet_name_value.getText().toString();
                String size = planet_size_value.getText().toString();
                String description = planet_description_value.getText().toString();

                planet_name_input = findViewById(R.id.planet_name_input);
                planet_size_input = findViewById(R.id.planet_size_input);
                planet_desc_input = findViewById(R.id.planet_desc_input);



                Boolean isFormValid = true;

                if(description.isEmpty()){
                    planet_desc_input.setError("Description cannot be empty !");
                    isFormValid = false;
                }
                if(size.isEmpty()){
                    planet_size_input.setError("Planet size cannot be empty !");
                    isFormValid = false;
                }
                if(name.isEmpty()){
                    planet_name_input.setError("Planet name cannot be empty !");
                    isFormValid = false;
                }

                if(isFormValid){
                    if(!isOldPlanet) {
                        planet = new Planets();
                    }
                    planet.setName(name);
                    planet.setSize(size);
                    planet.setDescription(description);

                    Intent intent = new Intent();
                    intent.putExtra("planet", planet);
                    setResult(Activity.RESULT_OK, intent);

                    finish();
                }
                else{
                    Toast.makeText(AddPlanetActivity.this, "Please make sure all fields are correct !", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
