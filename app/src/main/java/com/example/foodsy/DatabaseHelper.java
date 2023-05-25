package com.example.foodsy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.foodsy.models.CartModel;
import com.example.foodsy.models.DailyMealModel;
import com.example.foodsy.models.DetailedDailyModel;
import com.example.foodsy.models.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "foodsy.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    private static final String TABLE_CART = "cart";
    private static final String COLUMN_CART_ID = "id";
    private static final String COLUMN_CART_IMAGE = "image";
    private static final String COLUMN_CART_NAME = "name";
    private static final String COLUMN_CART_PRICE = "price";
    private static final String COLUMN_CART_RATING = "rating";


    // Имя таблицы и столбцов для таблицы "CartItems"
    private static final String TABLE_CART_ITEMS = "CartItems";
    private static final String COLUMN_CART_ITEM_IMAGE = "image";
    private static final String COLUMN_CART_ITEM_NAME = "name";
    private static final String COLUMN_CART_ITEM_PRICE = "price";
    private static final String COLUMN_CART_ITEM_RATING = "rating";

    private static final String TABLE_DAILY_MEAL = "daily_meal";
    private static final String COLUMN_DAILY_MEAL_ID = "id";
    private static final String COLUMN_DAILY_MEAL_IMAGE = "image";
    private static final String COLUMN_DAILY_MEAL_NAME = "name";
    private static final String COLUMN_DAILY_MEAL_DISCOUNT = "discount";
    private static final String COLUMN_DAILY_MEAL_TYPE = "type";
    private static final String COLUMN_DAILY_MEAL_DESCRIPTION = "description";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(query);
        String createCartTableQuery = "CREATE TABLE " + TABLE_CART + "(" +
                COLUMN_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CART_IMAGE + " INTEGER, " +
                COLUMN_CART_NAME + " TEXT, " +
                COLUMN_CART_PRICE + " TEXT, " +
                COLUMN_CART_RATING + " TEXT)";
        db.execSQL(createCartTableQuery);

        String createDailyMealTableQuery = "CREATE TABLE " + TABLE_DAILY_MEAL + "(" +
                COLUMN_DAILY_MEAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DAILY_MEAL_IMAGE + " INTEGER, " +
                COLUMN_DAILY_MEAL_NAME + " TEXT, " +
                COLUMN_DAILY_MEAL_DISCOUNT + " TEXT, " +
                COLUMN_DAILY_MEAL_TYPE + " TEXT, " +
                COLUMN_DAILY_MEAL_DESCRIPTION + " TEXT)";
        db.execSQL(createDailyMealTableQuery);
        // Создание таблицы CartItems
        String createTableQuery = "CREATE TABLE " + TABLE_CART_ITEMS + " ("
                + COLUMN_CART_ITEM_IMAGE + " INTEGER, "
                + COLUMN_CART_ITEM_NAME + " TEXT, "
                + COLUMN_CART_ITEM_PRICE + " TEXT, "
                + COLUMN_CART_ITEM_RATING + " TEXT"
                + ")";
        db.execSQL(createTableQuery);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAILY_MEAL);
        onCreate(db);
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { COLUMN_ID };
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = { email, password };
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }
    public boolean addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public User getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        if (cursor.moveToFirst()) {
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow (COLUMN_ID)));
            user.setName(cursor.getString(cursor.getColumnIndexOrThrow (COLUMN_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow (COLUMN_EMAIL)));
            user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow (COLUMN_PASSWORD)));
            cursor.close();
            return user;
        }
        cursor.close();
        return null;
    }
    public void addCartItem(CartModel cartItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CART_IMAGE, cartItem.getImage());
        values.put(COLUMN_CART_NAME, cartItem.getName());
        values.put(COLUMN_CART_PRICE, cartItem.getPrice());
        values.put(COLUMN_CART_RATING, cartItem.getRating());
        db.insert(TABLE_CART, null, values);
        db.close();
    }

    public void addDailyMeal(DailyMealModel dailyMeal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DAILY_MEAL_IMAGE, dailyMeal.getImage());
        values.put(COLUMN_DAILY_MEAL_NAME, dailyMeal.getName());
        values.put(COLUMN_DAILY_MEAL_DISCOUNT, dailyMeal.getDiscount());
        values.put(COLUMN_DAILY_MEAL_TYPE, dailyMeal.getType());
        values.put(COLUMN_DAILY_MEAL_DESCRIPTION, dailyMeal.getDescription());
        db.insert(TABLE_DAILY_MEAL, null, values);
        db.close();
    }

    public List<CartModel> getAllCartItems() {
        List<CartModel> cartItems = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CART;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int image = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CART_IMAGE));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CART_NAME));
                String price = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CART_PRICE));
                String rating = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CART_RATING));
                CartModel cartItem = new CartModel(image, name, price, rating);
                cartItems.add(cartItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cartItems;
    }

    public List<DailyMealModel> getAllDailyMeals() {
        List<DailyMealModel> dailyMeals = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_DAILY_MEAL;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int image = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DAILY_MEAL_IMAGE));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DAILY_MEAL_NAME));
                String discount = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DAILY_MEAL_DISCOUNT));
                String type = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DAILY_MEAL_TYPE));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DAILY_MEAL_DESCRIPTION));
                DailyMealModel dailyMeal = new DailyMealModel(image, name, discount, type, description);
                dailyMeals.add(dailyMeal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dailyMeals;
    }
    public void addToCart(DetailedDailyModel detailedDailyModel) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("image", detailedDailyModel.getImage());
        values.put("name", detailedDailyModel.getName());
        values.put("price", detailedDailyModel.getPrice());
        values.put("rating", detailedDailyModel.getRating());

        db.insert("CartItems", null, values);

        db.close();
    }
    public void deleteCartItem(int itemId) {
        SQLiteDatabase db = getWritableDatabase();

        // Указываем таблицу, из которой нужно удалить элемент
        String tableName = "CartItems";

        // Указываем условие для удаления конкретного элемента по его идентификатору
        String selection = COLUMN_CART_ITEM_NAME + " = ?";


        String[] selectionArgs = {String.valueOf(itemId)};

        // Удаляем элемент из базы данных
        db.delete(tableName, selection, selectionArgs);

        db.close();
    }

}


