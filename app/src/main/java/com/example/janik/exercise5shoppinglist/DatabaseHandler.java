package com.example.janik.exercise5shoppinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.janik.exercise5shoppinglist.Classes.Item;

import java.util.ArrayList;

/**
 * Created by janik on 29.09.2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "shoppingList";

    private static final String TABLE_ITEMS = "items";

    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_COUNT = "count";
    private static final String COL_PRICE = "price";


    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_NAME + " TEXT,"
                + COL_COUNT + " INTEGER,"
                + COL_PRICE + " REAL" + ")";
        db.execSQL(CREATE_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }

    public long addItem(String name, int count, double price){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME,name);
        values.put(COL_COUNT,count);
        values.put(COL_PRICE,price);

        long id = db.insert(TABLE_ITEMS,null,values);

        db.close();
        return id;

    }

    public Item getItem(int id){
        return null;
    }

    public ArrayList<Item> getAllItems(){
        ArrayList<Item> items = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_ITEMS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int count = cursor.getInt(2);
                double price = cursor.getDouble(3);
                Item item = new Item(id,name,count, price);

                items.add(item);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return items;
    }

    public int getItemsCount(){
        return 0;
    }

    public int updateItem(Item item){
        return 0;
    }

    public void deleteItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ITEMS,COL_ID + " = ?",
                new String[] {String.valueOf(item.getId())});
        db.close();
    }


}
