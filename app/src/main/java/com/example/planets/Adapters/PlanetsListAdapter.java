package com.example.planets.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planets.Models.Planets;
import com.example.planets.PlanetsClickListener;
import com.example.planets.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlanetsListAdapter extends RecyclerView.Adapter<PlanetsViewHolder> {
    Context context;
    List<Planets> list;
    PlanetsClickListener listener;
    private Planets selectedPlanet;

    public PlanetsListAdapter(Context context, List<Planets> list, PlanetsClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }
    public void setSelectedPlanet(Planets planet) {
        selectedPlanet = planet;
    }

    @NonNull
    @Override
    public PlanetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlanetsViewHolder(LayoutInflater.from(context).inflate(R.layout.planets_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetsViewHolder holder, int position) {
        holder.textViewName.setText(list.get(position).getName());
        holder.textViewName.setSelected(true);

        holder.textViewSize.setText(list.get(position).getSize());

        String planetDescription = list.get(position).getDescription();

        if(planetDescription.length() > 15){
            String limitedText = planetDescription.substring(0, 15) + "...";
            holder.textViewDescription.setText(limitedText);
        }else{
            holder.textViewDescription.setText(planetDescription);
        }


        int color_code = getRandomColor();
        holder.planets_cards_container.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code, null));

        holder.planets_cards_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(list.get(holder.getAdapterPosition()));
            }
        });
        holder.planets_cards_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongClick(list.get(holder.getAdapterPosition()), holder.planets_cards_container);
                return true;
            }
        });
    }

    private int getRandomColor(){
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.randColor1);
        colorCode.add(R.color.randColor2);
        colorCode.add(R.color.randColor3);
        colorCode.add(R.color.randColor4);
        colorCode.add(R.color.randColor5);
        colorCode.add(R.color.randColor6);
        colorCode.add(R.color.randColor7);
        colorCode.add(R.color.randColor8);
        colorCode.add(R.color.randColor9);
        colorCode.add(R.color.randColor10);
        colorCode.add(R.color.randColor11);
        colorCode.add(R.color.randColor12);

        Random rand = new Random();
        int random_color = rand.nextInt(colorCode.size());
        return colorCode.get(random_color);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(List<Planets> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }

}
class PlanetsViewHolder extends RecyclerView.ViewHolder{

    CardView planets_cards_container;
    TextView textViewName,textViewSize,textViewDescription;


    public PlanetsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.planets_cards_container = itemView.findViewById(R.id.planets_cards_container);
        this.textViewName = itemView.findViewById(R.id.textViewName);
        this.textViewSize = itemView.findViewById(R.id.textViewSize);
        this.textViewDescription = itemView.findViewById(R.id.textViewDescription);

    }
}
