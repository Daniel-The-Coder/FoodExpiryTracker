package com.hfad.foodexpirytracker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<FoodItem> items;
    private ArrayList<FoodItem> current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //access the database and store everything in an ArrayList
    }

    public void addElement(View view){
        Intent intent = new Intent(this, AddElementActivity.class);
        startActivity(intent);
    }

    public void viewExpired(View view){
        //change border color
        LinearLayout border = (LinearLayout)findViewById(R.id.border);
        border.setBackgroundColor(Color.RED);
    }

    public void viewToday(View view){
        //change border color
        LinearLayout border = (LinearLayout)findViewById(R.id.border);
        border.setBackgroundColor(Color.parseColor("#ff6103"));
    }

    public void viewTmrw(View view){
        //change border color
        LinearLayout border = (LinearLayout)findViewById(R.id.border);
        border.setBackgroundColor(Color.parseColor("#1e90ff"));
    }

    public void viewFuture(View view){
        //change border color
        LinearLayout border = (LinearLayout)findViewById(R.id.border);
        border.setBackgroundColor(Color.GREEN);
    }

    public void sortAlpha(View view){
        Button alpha = (Button)findViewById(R.id.alpha);
        alpha.setBackgroundColor(Color.parseColor("#303030"));

        Button date = (Button)findViewById(R.id.date);
        date.setBackgroundColor(Color.parseColor("#000000"));
    }

    public void sortDate(View view){
        Button alpha = (Button)findViewById(R.id.alpha);
        alpha.setBackgroundColor(Color.parseColor("#000000"));

        Button date = (Button)findViewById(R.id.date);
        date.setBackgroundColor(Color.parseColor("#303030"));
    }
}
