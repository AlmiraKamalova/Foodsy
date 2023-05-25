package com.example.foodsy.ui.dailymeal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodsy.R;
import com.example.foodsy.adapters.DailyMealAdapter;
import com.example.foodsy.models.DailyMealModel;

import java.util.ArrayList;
import java.util.List;

public class DailyMealFragment extends Fragment {

    RecyclerView recyclerView;
    List<DailyMealModel> dailyMealModels;
    DailyMealAdapter dailyMealAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.daily_meal_fragment, container, false);


        recyclerView = root.findViewById(R.id.daily_meal_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dailyMealModels = new ArrayList<>();

        dailyMealModels.add(new DailyMealModel(R.drawable.breakfast, "Breakfast", "30%", "breakfast", "Healthy breakfasts" ));
        dailyMealModels.add(new DailyMealModel(R.drawable.lunch, "Lunch", "20%", "Lunch", "Best time to eat"));
        dailyMealModels.add(new DailyMealModel(R.drawable.dinner, "Dinner", "20%", "Dinner", "Delicious dinners"));
        dailyMealModels.add(new DailyMealModel(R.drawable.sweets, "Dessert", "15%", "Sweets", "Low-calorie sweets"));
        dailyMealModels.add(new DailyMealModel(R.drawable.coffe, "Coffe", "10%", "Coffe", "Freshly ground coffee"));

        dailyMealAdapter = new DailyMealAdapter(getContext(),dailyMealModels);
        recyclerView.setAdapter(dailyMealAdapter);
        dailyMealAdapter.notifyDataSetChanged();

        return root;
    }


}

