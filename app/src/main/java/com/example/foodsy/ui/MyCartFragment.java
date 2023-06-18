/*package com.example.foodsy.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodsy.R;
import com.example.foodsy.adapters.CartAdapter;
import com.example.foodsy.models.CartModel;

import java.util.ArrayList;
import java.util.List;


public class MyCartFragment extends Fragment {

    List<CartModel> list;
    CartAdapter cartAdapter;
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        recyclerView = view.findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        list.add(new CartModel(R.drawable.s1, "Order 1", "30", "4.3"));
        list.add(new CartModel(R.drawable.s2, "Order 1", "$20", "4.6"));
        list.add(new CartModel(R.drawable.fav1, "Order 1", "40", "4.4"));
        list.add(new CartModel(R.drawable.s1, "Order 1", "$30", "4.3"));
        list.add(new CartModel(R.drawable.s2, "Order 1", "$20", "4.3"));
        list.add(new CartModel(R.drawable.fav1, "Order 1", "40", "4.3"));

        cartAdapter = new CartAdapter(list);
        recyclerView.setAdapter(cartAdapter);


        return view;
    }
}*/
package com.example.foodsy.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodsy.Map;
import com.example.foodsy.R;
import com.example.foodsy.ReceiptActivity;
import com.example.foodsy.adapters.CartAdapter;
import com.example.foodsy.models.CartModel;
import com.example.foodsy.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyCartFragment extends Fragment {

    private List<CartModel> cartItems;
    private CartAdapter cartAdapter;
    private RecyclerView recyclerView;

    private TextView totalCostTextView;

    public MyCartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        recyclerView = view.findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cartItems = new ArrayList<>();

        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CartItems", null);

        if (cursor.moveToFirst()) {
            do {
                int image = cursor.getInt(cursor.getColumnIndexOrThrow("image"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String price = cursor.getString(cursor.getColumnIndexOrThrow("price"));
                String rating = cursor.getString(cursor.getColumnIndexOrThrow("rating"));

                CartModel cartItem = new CartModel(image, name, price, rating);
                cartItems.add(cartItem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        cartAdapter = new CartAdapter(cartItems);
        recyclerView.setAdapter(cartAdapter);

        // Обновление общей стоимости
        updateTotalCost(view);

        Button makeOrderButton = view.findViewById(R.id.button);
        makeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработка нажатия на кнопку "Make Order"
                startActivity(new Intent(getActivity(), Map.class));
            }
        });


        return view;
    }

    // Метод для обновления общей стоимости
    private void updateTotalCost(View view) {
        TextView totalCostTextView = view.findViewById(R.id.total_cost_text_view);
        double totalCost = 0;

        for (CartModel cartItem : cartItems) {
            double price = Double.parseDouble(cartItem.getPrice());
            totalCost += price;
        }

        // Отобразить общую стоимость
        totalCostTextView.setText(String.format(Locale.getDefault(), "Total cost: %.2f", totalCost));
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Additional operations when the fragment's view is created
    }

    public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

        private List<CartModel> list;

        public CartAdapter(List<CartModel> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            CartModel cartItem = list.get(position);
            holder.imageView.setImageResource(cartItem.getImage());
            holder.name.setText(cartItem.getName());
            holder.rating.setText(cartItem.getRating());
            holder.price.setText(cartItem.getPrice());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageButton deleteButton;
            ImageView imageView;
            TextView name, rating, price;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                deleteButton = itemView.findViewById(R.id.delete_button);
                imageView = itemView.findViewById(R.id.detailed_img);
                name = itemView.findViewById(R.id.detailed_name);
                rating = itemView.findViewById(R.id.detailed_rating);
                price = itemView.findViewById(R.id.textView12);
            }
        }

    }
}


