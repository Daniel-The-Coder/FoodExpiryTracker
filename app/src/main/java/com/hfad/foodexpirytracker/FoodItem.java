package com.hfad.foodexpirytracker;

/**
 * Created by Lord Daniel on 7/27/2016.
 */
public class FoodItem {
    private String name;
    private int id;
    private int year;
    private int month;
    private int day;

    public FoodItem(String name, int id, int year, int month, int day){
        this.name = name;
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public int getYear(){
        return this.year;
    }

    public int getMonth(){
        return this.month;
    }

    public int getDay(){
        return this.day;
    }
}
