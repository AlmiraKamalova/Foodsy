package com.example.foodsy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import com.example.foodsy.R;
import com.example.foodsy.adapters.DetailedDailyAdapter;
import com.example.foodsy.models.DetailedDailyModel;

import java.util.ArrayList;
import java.util.List;
///мое прил
public class DetailedDailyMealActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DetailedDailyModel> detailedDailyModelList;
    DetailedDailyAdapter dailyAdapter;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_daily_meal);


        String type = getIntent().getStringExtra("type");


        recyclerView = findViewById(R.id.detailed_rec);
        imageView = findViewById(R.id.detailed_img);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailedDailyModelList = new ArrayList<>();
        //dailyAdapter = new DetailedDailyAdapter(detailedDailyModelList);
        dailyAdapter = new DetailedDailyAdapter(detailedDailyModelList, this);

        recyclerView.setAdapter(dailyAdapter);

        if (type != null && type.equalsIgnoreCase("breakfast")) {

            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.fav1, "Breakfast Porridge", "Delicious and healthy", "4.4", "2", "10 am to 9 pm"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.fav2, "Breakfast sandwich", "Delicious and healthy", "4.4", "3", "10 am to 9 pm"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.fav3, "Breakfast pasta", "Delicious and healthy", "4.4", "1", "10 am to 9 pm"));

            dailyAdapter.notifyDataSetChanged();
        }

        if (type != null && type.equalsIgnoreCase("sweets")) {


            imageView.setImageResource(R.drawable.sweets);

            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s1, "Sweet", "Delicious and healthy", "4.4", "1", "10 am to 9 pm"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s2, "Donuts", "Delicious and healthy", "4.4", "1", "10 am to 9 pm"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s3, "Ice cream", "Delicious and healthy", "4.4", "2", "10 am to 9 pm"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s4, "Cake", "Delicious and healthy", "4.4", "1", "10 am to 9 pm"));
        }
        if (type != null && type.equalsIgnoreCase("lunch")) {


            imageView.setImageResource(R.drawable.sweets);

            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s1, "Sweet", "Delicious and healthy", "4.4", "1", "10 am to 9 pm"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s2, "Donuts", "Delicious and healthy", "4.4", "1", "10 am to 9 pm"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s3, "Ice cream", "Delicious and healthy", "4.4", "2", "10 am to 9 pm"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s4, "Cake", "Delicious and healthy", "4.4", "1", "10 am to 9 pm"));
            dailyAdapter.notifyDataSetChanged();
        }
        if (type != null && type.equalsIgnoreCase("Dinner")) {


            imageView.setImageResource(R.drawable.sweets);

            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s1, "Sweet", "Delicious and healthy", "4.4", "40", "10 am to 9 pm"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s2, "Sweet", "Delicious and healthy", "4.4", "40", "10 am to 9 pm"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s3, "Sweet", "Delicious and healthy", "4.4", "40", "10 am to 9 pm"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s4, "Sweet", "Delicious and healthy", "4.4", "40", "10 am to 9 pm"));
            dailyAdapter.notifyDataSetChanged();
        }
        if (type != null && type.equalsIgnoreCase("Coffe")) {


            imageView.setImageResource(R.drawable.sweets);

            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s1, "Sweet", "Delicious and healthy", "4.4", "40", "10 am to 9 pm"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s2, "Sweet", "Delicious and healthy", "4.4", "40", "10 am to 9 pm"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s3, "Sweet", "Delicious and healthy", "4.4", "40", "10 am to 9 pm"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s4, "Sweet", "Delicious and healthy", "4.4", "40", "10 am to 9 pm"));
            dailyAdapter.notifyDataSetChanged();
        }

    }
}