package com.example.planets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MyBottomSheetFragment extends BottomSheetDialogFragment {

    public static MyBottomSheetFragment newInstance(String planetName, String planetDetails) {
        MyBottomSheetFragment fragment = new MyBottomSheetFragment();
        Bundle args = new Bundle();
        args.putString("planet_name_arg", planetName);
        args.putString("planet_details_arg", planetDetails);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String planetName = getArguments().getString("planet_name_arg");
        String planetDetails = getArguments().getString("planet_details_arg");

        TextView sideSheetTitle = view.findViewById(R.id.sideSheetTitle);
        sideSheetTitle.setText(planetName);

        TextView sideSheetText = view.findViewById(R.id.sideSheetText);
        sideSheetText.setText(planetDetails);


    }
}
