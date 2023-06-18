package com.example.foodsy;

import android.os.Bundle;
import android.widget.TextView;
import java.util.TimeZone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodsy.adapters.CartAdapter;
import com.example.foodsy.models.CartModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReceiptActivity extends AppCompatActivity {

    private List<CartModel> cartItems;
    private TextView receiptTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        cartItems = getCartItemsFromDatabase(); // Получаем товары из базы данных корзины

        receiptTextView = findViewById(R.id.receipt_title);

        String receipt = generateReceipt(); // Генерируем чек на основе товаров из корзины
        receiptTextView.setText(receipt);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date = dateFormat.format(new Date());
        TextView receiptDateTextView = findViewById(R.id.receipt_date);
        receiptDateTextView.setText("Date: " + date);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        timeFormat.setTimeZone(TimeZone.getTimeZone("Europe/Kiev"));
        String time = timeFormat.format(new Date());
        TextView receiptTimeTextView = findViewById(R.id.receipt_time);
        receiptTimeTextView.setText("Time: " + time);


    }

    private List<CartModel> getCartItemsFromDatabase() {
        // Получаем товары из базы данных корзины
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        return dbHelper.getAllCartItems();
    }

    private String generateReceipt() {
        StringBuilder sb = new StringBuilder();
        double totalCost = 0;

        // Добавляем заголовок чека
        sb.append("========= Receipt =========\n\n");

        // Добавляем информацию о каждом товаре
        for (CartModel cartItem : cartItems) {
            double price = Double.parseDouble(cartItem.getPrice());
            totalCost += price;

            sb.append("- ").append(cartItem.getName()).append("\n");
            sb.append("Price: ").append(cartItem.getPrice()).append("\n");
            sb.append("Rating: ").append(cartItem.getRating()).append("\n\n");
        }

        // Добавляем общую стоимость
        sb.append("Total cost: ").append(String.format(Locale.getDefault(), "%.2f", totalCost)).append("\n");

        // Добавляем нижний разделитель
        sb.append("=========================");

        return sb.toString();
    }
}
