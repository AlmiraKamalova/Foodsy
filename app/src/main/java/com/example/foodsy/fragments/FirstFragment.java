package com.example.foodsy.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodsy.R;
import com.example.foodsy.adapters.FeaturedAdapter;
import com.example.foodsy.adapters.FeaturedVerAdapter;
import com.example.foodsy.models.FeaturedModel;
import com.example.foodsy.models.FeaturedVerModel;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment extends Fragment {


    /////////////////////////////////////Featured Hor RecyclerView

    List<FeaturedModel> featuredModelList;
    RecyclerView recyclerView;
    FeaturedAdapter featuredAdapter;

    /////////////////////////////////////Featured Ver RecyclerView
    List<FeaturedVerModel> featuredVerModelList;
    RecyclerView recyclerView2;
    FeaturedVerAdapter featuredVerAdapter;


    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_first, container, false);



        /////////////////////////////////////Featured Hor RecyclerView
        recyclerView =view.findViewById(R.id.featured_hor_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        featuredModelList = new ArrayList<>();

        featuredModelList.add(new FeaturedModel(R.drawable.fav1,"Featured 1", "Description 1"));
        featuredModelList.add(new FeaturedModel(R.drawable.fav2,"Featured 2", "Description 2"));
        featuredModelList.add(new FeaturedModel(R.drawable.fav3,"Featured 3", "Description 3"));

        featuredAdapter = new FeaturedAdapter(featuredModelList);
        recyclerView.setAdapter(featuredAdapter);

        /////////////////////////////////////Featured Ver RecyclerView
        recyclerView2 =view.findViewById(R.id.featured_ver_rec);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        featuredVerModelList = new ArrayList<>();

        featuredVerModelList.add(new FeaturedVerModel(R.drawable.ver1, "Oatmeal with berries", "a classic breakfast recipe that is easy and quick to prepare in the morning", "", "15 min"));
        featuredVerModelList.add(new FeaturedVerModel(R.drawable.ver2, "Egg sandwiches", "This sandwich is great for a quick bite.", "", "10:00 - 8:00"));
        featuredVerModelList.add(new FeaturedVerModel(R.drawable.ver3, "Pancake", "Pancakes with syrup and fruits", "", "10:00 - 8:00"));




        featuredVerAdapter = new FeaturedVerAdapter(featuredVerModelList);
        recyclerView2.setAdapter(featuredVerAdapter);

        return view;
    }
}