package com.example.foodsy.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodsy.R;
import com.example.foodsy.models.HomeHorModel;
import com.example.foodsy.models.HomeVerModel;

import java.util.ArrayList;

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.ViewHolder> {


    UpdateVerticalRec updateVerticalRec;

    Activity activity;

    ArrayList<HomeHorModel> list;

    boolean check = true;
    boolean select = true;
    int row_index = -1;


    public HomeHorAdapter(UpdateVerticalRec updateVerticalRec, Activity activity, ArrayList<HomeHorModel> list) {
        this.updateVerticalRec = updateVerticalRec;
        this.activity = activity;
        this.list = list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());

        if (check) {
            ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
            homeVerModels.add(new HomeVerModel(R.drawable.break1, "Pancakes with berries", "7:00 - 11:00", "4.5", "2"));
            homeVerModels.add(new HomeVerModel(R.drawable.break2, "Muesli with berries", "7:00 - 11:00", "4.9", "3"));
            homeVerModels.add(new HomeVerModel(R.drawable.break3, "Sandwiches", "7:00 - 11:00", "4.6", "2.9"));
            homeVerModels.add(new HomeVerModel(R.drawable.break4, "Croissants", "7:00 - 11:00", "4.8", "2"));

            updateVerticalRec.callBack(position, homeVerModels);
            check = false;
        }

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View V) {

                    row_index = position;
                    notifyDataSetChanged();


                    if (position == 0) {

                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new HomeVerModel(R.drawable.break1, "Pancakes with berries", "7:00 - 11:00", "4.5", "2"));
                        homeVerModels.add(new HomeVerModel(R.drawable.break2, "Muesli with berries", "7:00 - 11:00", "4.9", "3"));
                        homeVerModels.add(new HomeVerModel(R.drawable.break3, "Sandwiches", "7:00 - 11:00", "4.6", "2.9"));
                        homeVerModels.add(new HomeVerModel(R.drawable.break4, "Croissants", "7:00 - 11:00", "4.8", "2"));


                        updateVerticalRec.callBack(position, homeVerModels);
                    } else if (position == 1) {

                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new HomeVerModel(R.drawable.soup1, "Chicken Noodle Soup", "11:00 - 16:00", "4.9", "2.4"));
                        homeVerModels.add(new HomeVerModel(R.drawable.soup2, "Tomato Soup", "11:00 - 16:00", "4.9", "2.3"));
                        homeVerModels.add(new HomeVerModel(R.drawable.soup3, "Chicken and Rice Soup", "11:00 - 16:00", "4.9", "2.5"));


                        updateVerticalRec.callBack(position, homeVerModels);

                    } else if (position == 2) {

                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new HomeVerModel(R.drawable.maind1, "Fried chicken fillets", "10:00 - 23:00", "4.9", "3"));
                        homeVerModels.add(new HomeVerModel(R.drawable.maind2, "Barbecued salmon", "10:00 - 23:00", "4.9", "3.2"));
                        homeVerModels.add(new HomeVerModel(R.drawable.maind3, "Spanish seafood paella", "10:00 - 23:00", "4.9", "3.1"));
                        homeVerModels.add(new HomeVerModel(R.drawable.maind4, "Plated chicken roast", "10:00 - 23:00", "4.9", "3"));
                        homeVerModels.add(new HomeVerModel(R.drawable.maind5, "Baked vegetables", "10:00 - 23:00", "4.9", "3.5"));
                        homeVerModels.add(new HomeVerModel(R.drawable.maind6, "Meat pie", "10:00 - 23:00", "4.9", "2.9"));

                        updateVerticalRec.callBack(position, homeVerModels);

                    } else if (position == 3) {

                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new HomeVerModel(R.drawable.dessert1, "Tiramisu cake with mint", "10:00 - 23:00", "4.9", "1.5"));
                        homeVerModels.add(new HomeVerModel(R.drawable.dessert2, "Panna cotta with berries", "10:00 - 23:00", "4.9", "2"));
                        homeVerModels.add(new HomeVerModel(R.drawable.dessert3, "Vanilla Pudding", "10:00 - 23:00", "4.9", "1.9"));
                        homeVerModels.add(new HomeVerModel(R.drawable.dessert4, "Chocolate fondant", "10:00 - 23:00", "4.9", "1.8"));
                        homeVerModels.add(new HomeVerModel(R.drawable.dessert5, "Blueberry ice cream", "10:00 - 23:00", "4.9", "1.8"));

                        updateVerticalRec.callBack(position, homeVerModels);

                    } else if (position == 4) {

                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new HomeVerModel(R.drawable.icecream1, "Ice Cream 1", "10:00 - 23:00", "4.9", "1.5"));
                        homeVerModels.add(new HomeVerModel(R.drawable.icecream2, "Ice Cream 2", "10:00 - 23:00", "4.9", "2"));
                        homeVerModels.add(new HomeVerModel(R.drawable.icecream3, "Ice Cream 3", "10:00 - 23:00", "4.9", "1.9"));
                        homeVerModels.add(new HomeVerModel(R.drawable.icecream4, "Ice Cream 4", "10:00 - 23:00", "4.9", "1.8"));

                        updateVerticalRec.callBack(position, homeVerModels);

                    }



                }

            });

            if (select){
                if (position ==0){
                    holder.cardView.setBackgroundResource(R.drawable.change_bg);
                    select = false;
                }

            }

            else {
                if (row_index == position){
                    holder.cardView.setBackgroundResource(R.drawable.change_bg);
                }else {
                    holder.cardView.setBackgroundResource(R.drawable.default_bg);
                }
            }
        }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.hor_img);
            name = itemView.findViewById(R.id.hor_text);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
