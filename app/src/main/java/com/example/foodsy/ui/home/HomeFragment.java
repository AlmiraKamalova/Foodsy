package com.example.foodsy.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodsy.DatabaseHelper;
import com.example.foodsy.R;
import com.example.foodsy.adapters.HomeHorAdapter;
import com.example.foodsy.adapters.HomeVerAdapter;
import com.example.foodsy.adapters.UpdateVerticalRec;
import com.example.foodsy.models.CartModel;
import com.example.foodsy.models.HomeHorModel;
import com.example.foodsy.models.HomeVerModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements UpdateVerticalRec {

    RecyclerView homeHorizontalRec, homeVerticalRec;
    ArrayList<HomeHorModel> homeHorModelList;
    HomeHorAdapter homeHorAdapter;

    ////////////////////////////Vertical
    ArrayList<HomeVerModel> homeVerModelList;
    HomeVerAdapter homeVerAdapter;


    ///////////
    DatabaseHelper databaseHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


       ///////////
        databaseHelper = new DatabaseHelper(getActivity());


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeHorizontalRec = root.findViewById(R.id.home_hor_rec);
        homeVerticalRec = root.findViewById(R.id.home_ver_rec);

        /////////////////////////////////Horizontal RecycleView
        homeHorModelList = new ArrayList<>();

        homeHorModelList.add(new HomeHorModel(R.drawable.brearfastt,"Breakfast"));
        homeHorModelList.add(new HomeHorModel(R.drawable.soup,"Soup"));
        homeHorModelList.add(new HomeHorModel(R.drawable.main_dish,"Main dish"));
        homeHorModelList.add(new HomeHorModel(R.drawable.dessert,"Dessert"));
        homeHorModelList.add(new HomeHorModel(R.drawable.salad,"Salad"));


        homeHorAdapter = new HomeHorAdapter(this,getActivity(),homeHorModelList);
        homeHorizontalRec.setAdapter(homeHorAdapter);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);


        /////////////////////////////////Vertical RecycleView
        homeVerModelList = new ArrayList<>();



        homeVerAdapter = new HomeVerAdapter(getActivity(),homeVerModelList);
        homeVerticalRec.setAdapter(homeVerAdapter);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));

        return root;
    }

    @Override
    public void callBack(int position, ArrayList<HomeVerModel> list) {

        ////////////////
        HomeVerModel selectedItem = list.get(position);
        databaseHelper.addCartItem(new CartModel(selectedItem.getImage(), selectedItem.getName(), selectedItem.getPrice(), selectedItem.getRating()));



        homeVerAdapter = new HomeVerAdapter(getContext(),list);
        homeVerAdapter.notifyDataSetChanged();
        homeVerticalRec.setAdapter(homeVerAdapter);

    }

    /////////
    // Переменная для хранения выбранных товаров
    List<HomeVerModel> selectedItems = new ArrayList<>();

}