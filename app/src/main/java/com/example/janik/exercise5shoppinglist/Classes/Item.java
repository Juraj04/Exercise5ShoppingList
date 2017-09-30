package com.example.janik.exercise5shoppinglist.Classes;

/**
 * Created by janik on 28.09.2017.
 */

public class Item {

    private long id;
    private String name;
    private int count;
    private double price;


    public Item(long id,String name, int count, double price) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
