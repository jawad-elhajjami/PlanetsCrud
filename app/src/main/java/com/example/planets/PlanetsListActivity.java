package com.example.planets;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planets.Adapters.PlanetsListAdapter;
import com.example.planets.Database.RoomDB;
import com.example.planets.Models.Planets;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class PlanetsListActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    RecyclerView recyclerView;
    PlanetsListAdapter planetsListAdapter;
    List<Planets> planets = new ArrayList<>();

    RoomDB database;
    FloatingActionButton fab;
    SearchView search_value;
    Planets selectedPlanet;

    LinearLayout noPlanetsLayout;
    ImageView planetImage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planets_list);



        recyclerView = findViewById(R.id.planetsRecyclerView);
        fab = findViewById(R.id.add_planet_btn);
        search_value = findViewById(R.id.search_value);
        noPlanetsLayout = findViewById(R.id.NoPlanetsMessageContainer);
        planetImage = findViewById(R.id.planetImage);

        // init database object
        database = RoomDB.getInstance(this);
        planets = database.mainDAO().getAllPlanets();



        updateRecyclerViewAndLayout(planets);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlanetsListActivity.this, AddPlanetActivity.class);
                startActivityForResult(intent, 101);
            }
        });

        search_value.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return true;
            }
        });


    }


    private void updateRecyclerViewAndLayout(List<Planets> planets) {
        if (planets.isEmpty()) {
            // No planets found, show the alternative layout
            recyclerView.setVisibility(View.GONE);
            noPlanetsLayout.setVisibility(View.VISIBLE);
        } else {
            // Planets found, show the RecyclerView
            recyclerView.setVisibility(View.VISIBLE);
            noPlanetsLayout.setVisibility(View.GONE);
            updateRecycler(planets);
        }
    }
    private void showDeleteConfirmationDialog(Planets planetToDelete) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Delete");
        builder.setMessage("Are you sure you want to delete the planet " + planetToDelete.getName() + "?");
        builder.setPositiveButton("Delete", (dialog, which) -> {
            // User confirmed deletion, delete the planet and update the UI
            database.mainDAO().delete(planetToDelete);
            planets.remove(planetToDelete);
            if(planetsListAdapter != null) {
                planetsListAdapter.notifyDataSetChanged();
            }
            updateRecyclerViewAndLayout(planets);
            Toast.makeText(this, "Planet deleted!", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
    private void filter(String s) {

        List<Planets> filteredList = new ArrayList<>();

        for(Planets single_planet: planets){
            if(single_planet.getName().toLowerCase().contains(s.toLowerCase())
            || single_planet.getDescription().toLowerCase().contains(s.toLowerCase())
            || single_planet.getSize().toLowerCase().contains(s.toLowerCase())){
                filteredList.add(single_planet);
            }
        }
        if(planetsListAdapter != null){
            planetsListAdapter.filterList(filteredList);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101){
            if(resultCode == Activity.RESULT_OK){
                Planets newPlanet = (Planets) data.getSerializableExtra("planet");
                database.mainDAO().insert(newPlanet);
                planets.clear();
                planets.addAll(database.mainDAO().getAllPlanets());
                if(planetsListAdapter != null){
                    planetsListAdapter.notifyDataSetChanged();
                }

                updateRecyclerViewAndLayout(planets);

            }
        }else if(requestCode == 102){
            if(resultCode == Activity.RESULT_OK){
                Planets newPlanet = (Planets) data.getSerializableExtra("planet");
                database.mainDAO().update(newPlanet.getID(), newPlanet.getName(), newPlanet.getSize(), newPlanet.getDescription());
                planets.clear();
                planets.addAll(database.mainDAO().getAllPlanets());
                if(planetsListAdapter != null){
                    planetsListAdapter.notifyDataSetChanged();
                }
                updateRecyclerViewAndLayout(planets);
            }
        }
    }


    private void updateRecycler(List<Planets> planets) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        planetsListAdapter = new PlanetsListAdapter(PlanetsListActivity.this, planets, planetsClickListener);
        recyclerView.setAdapter(planetsListAdapter);

    }
    private final PlanetsClickListener planetsClickListener = new PlanetsClickListener() {
        @Override
        public void onClick(Planets planets) {
            Intent intent = new Intent(PlanetsListActivity.this, AddPlanetActivity.class);
            intent.putExtra("old_planet", planets);
            startActivityForResult(intent, 102);
        }

        @Override
        public void onLongClick(Planets planets, CardView cardView) {
            selectedPlanet = planets;
            planetsListAdapter.setSelectedPlanet(planets);
            showPopup(cardView);
        }


    };
    private void showPopup(CardView cardView) {
        PopupMenu popupMenu = new PopupMenu(this, cardView);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.delete:
                showDeleteConfirmationDialog(selectedPlanet);
                return true;
            case R.id.details:
                String planetDetails = selectedPlanet.getDescription();
                String planetName = selectedPlanet.getName();
                MyBottomSheetFragment bottomSheetFragment = MyBottomSheetFragment.newInstance(planetName, planetDetails);
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                return true;
            default:
                return false;
        }
    }


}