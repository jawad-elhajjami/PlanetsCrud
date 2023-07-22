package com.example.planets;

import androidx.cardview.widget.CardView;

import com.example.planets.Models.Planets;

public interface PlanetsClickListener {
    void onClick(Planets planets);
    void onLongClick(Planets planets, CardView cardView);
}
