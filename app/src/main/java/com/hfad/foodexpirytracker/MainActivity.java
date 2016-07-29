package com.hfad.foodexpirytracker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ArrayList<FoodItem> foodItems;
    private ArrayList<FoodItem> current;
    private int sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        current = new ArrayList<>();
        foodItems = new ArrayList<>();
        sort = 0;//alphabetical

        //access the database and store everything in an ArrayList
        Database db = new Database(this);
        foodItems = new ArrayList<>(db.getAllData());

        //iterate through foodItems and store items expiring today in current
        for(FoodItem f:foodItems){
            Date today = new Date();
            if(f.getDate().equals(today)){
                current.add(f);
            }
        }

        //sort current alphabetically
        Collections.sort(current, new AlphaComparator());

        //iterate through current and display
        //clear list
        LinearLayout items = (LinearLayout)findViewById(R.id.items);
        //items.removeAllViews();

        //iterate through current and create an item for every element and ad it to items
        for(FoodItem f:current){
            items.addView(createFoodItem(f.getName(), f.getDate(), f.getId(), 0));
        }

    }

    public void addElement(View view){
        Intent intent = new Intent(this, AddElementActivity.class);
        startActivity(intent);
    }

    public void viewExpired(View view){
        //change border color
        LinearLayout border = (LinearLayout)findViewById(R.id.border);
        border.setBackgroundColor(Color.RED);

        //clear list
        LinearLayout items = (LinearLayout)findViewById(R.id.items);
        items.removeAllViews();

        //change the value of current
        current = new ArrayList<>();
        Date today = new Date();
        for(FoodItem f:foodItems){
            if(f.getDate().compareTo(today)<0){
                current.add(f);
            }
        }
        //sort
        if(sort==0){
            Collections.sort(current,new AlphaComparator());
        }
        else{
            Collections.sort(current, new DateComparator());
        }

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(30, 30, 30, 30);
        //iterate through current and create an item for every element and ad it to items
        for(FoodItem f:current){
            items.addView(createFoodItem(f.getName(), f.getDate(), f.getId(), -1),param);
        }

        //just for testing
//        LinearLayout Lx = (LinearLayout)findViewById(R.id.testLinearLayout);
//        Lx.setBackgroundColor(Color.parseColor("#ff0000"));
//        Button Tx = (Button)findViewById(R.id.testButton);
//        Tx.setTextColor(Color.parseColor("#ff0000"));
    }

    public void viewToday(View view){
        //change border color
        LinearLayout border = (LinearLayout)findViewById(R.id.border);
        border.setBackgroundColor(Color.parseColor("#ff6103"));

        //clear list
        LinearLayout items = (LinearLayout)findViewById(R.id.items);
        items.removeAllViews();

        //change the value of current
        current = new ArrayList<>();
        Date today = new Date();
        for(FoodItem f:foodItems){
            if(f.getDate().compareTo(today)==0){
                current.add(f);
            }
        }
        //sort
        if(sort==0){
            Collections.sort(current,new AlphaComparator());
        }
        else{
            Collections.sort(current, new DateComparator());
        }

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(30, 30, 30, 30);
        //iterate through current and create an item for every element and ad it to items
        for(FoodItem f:current){
            items.addView(createFoodItem(f.getName(), f.getDate(), f.getId(), 0),param);
        }

        //just for testing
//        LinearLayout Lx = (LinearLayout)findViewById(R.id.testLinearLayout);
//        Lx.setBackgroundColor(Color.parseColor("#ff6103"));
//        Button Tx = (Button)findViewById(R.id.testButton);
//        Tx.setTextColor(Color.parseColor("#ff6103"));
    }

    public void viewTmrw(View view){
        //change border color
        LinearLayout border = (LinearLayout)findViewById(R.id.border);
        border.setBackgroundColor(Color.parseColor("#1e90ff"));

        //clear list
        LinearLayout items = (LinearLayout)findViewById(R.id.items);
        items.removeAllViews();

        //change the value of current
        current = new ArrayList<>();
        Date today = new Date();
        for(FoodItem f:foodItems){
            if(TimeUnit.DAYS.convert(f.getDate().getTime() - today.getTime(), TimeUnit.MILLISECONDS)==1){
                current.add(f);
            }
        }
        //sort
        if(sort==0){
            Collections.sort(current,new AlphaComparator());
        }
        else{
            Collections.sort(current, new DateComparator());
        }

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(30, 30, 30, 30);
        //iterate through current and create an item for every element and ad it to items
        for(FoodItem f:current){
            items.addView(createFoodItem(f.getName(), f.getDate(), f.getId(), 1),param);
        }

        //just for testing
//        LinearLayout Lx = (LinearLayout)findViewById(R.id.testLinearLayout);
//        Lx.setBackgroundColor(Color.parseColor("#1e90ff"));
//        Button Tx = (Button)findViewById(R.id.testButton);
//        Tx.setTextColor(Color.parseColor("#1e90ff"));
    }

    public void viewFuture(View view){
        //change border color
        LinearLayout border = (LinearLayout)findViewById(R.id.border);
        border.setBackgroundColor(Color.GREEN);

        //clear list
        LinearLayout items = (LinearLayout)findViewById(R.id.items);
        items.removeAllViews();

        //change the value of current
        current = new ArrayList<>();
        Date today = new Date();
        for(FoodItem f:foodItems){
            if(TimeUnit.DAYS.convert(f.getDate().getTime()-today.getTime(),TimeUnit.MILLISECONDS)>1){
                current.add(f);
            }
        }
        //sort
        if(sort==0){
            Collections.sort(current,new AlphaComparator());
        }
        else{
            Collections.sort(current, new DateComparator());
        }

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(30, 30, 30, 30);
        //iterate through current and create an item for every element and ad it to items
        for(FoodItem f:current){
            items.addView(createFoodItem(f.getName(), f.getDate(), f.getId(), 2),param);
        }

        //just for testing
//        LinearLayout Lx = (LinearLayout)findViewById(R.id.testLinearLayout);
//        Lx.setBackgroundColor(Color.parseColor("#00ff00"));
//        Button Tx = (Button)findViewById(R.id.testButton);
//        Tx.setTextColor(Color.parseColor("#00ff00"));
    }

    public void sortAlpha(View view){
        Button alpha = (Button)findViewById(R.id.alpha);
        alpha.setBackgroundColor(Color.parseColor("#303030"));

        Button date = (Button)findViewById(R.id.date);
        date.setBackgroundColor(Color.parseColor("#000000"));

        Collections.sort(current, new AlphaComparator());
        //clear list
        LinearLayout items = (LinearLayout)findViewById(R.id.items);
        items.removeAllViews();
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(30, 30, 30, 30);
        //iterate through current and create an item for every element and ad it to items
        for(FoodItem f:current){
            items.addView(createFoodItem(f.getName(), f.getDate(), f.getId(), 0),param);
        }
        sort = 0;
    }

    public void sortDate(View view){
        Button alpha = (Button)findViewById(R.id.alpha);
        alpha.setBackgroundColor(Color.parseColor("#000000"));

        Button date = (Button)findViewById(R.id.date);
        date.setBackgroundColor(Color.parseColor("#303030"));

        Collections.sort(current, new DateComparator());
        //clear list
        LinearLayout items = (LinearLayout)findViewById(R.id.items);
        items.removeAllViews();
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(30, 30, 30, 30);
        //iterate through current and create an item for every element and ad it to items
        for(FoodItem f:current){
            items.addView(createFoodItem(f.getName(), f.getDate(), f.getId(), 0),param);
        }
        sort = 1;
    }

    /**
     * statuses:
     *          -1 : expired
     *          0  : expires today
     *          1  : expires tomorrow
     *          2  : expires later
     * @param name
     * @param date
     * @param status
     * @return
     */
    public LinearLayout createFoodItem(String name, Date date, int id, int status){
        LinearLayout L  = new LinearLayout(this);
        L.setOrientation(LinearLayout.VERTICAL);
        L.setPadding(5, 5, 5, 5);
        if(status == -1) {
            L.setBackgroundColor(Color.parseColor("#ff0000"));
        }
        else if(status == 0){
            L.setBackgroundColor(Color.parseColor("#ff6103"));
        }
        else if(status == 1){
            L.setBackgroundColor(Color.parseColor("#1e90ff"));
        }
        else { //status = 2
            L.setBackgroundColor(Color.parseColor("#00ff00"));
        }

        TextView textName = new TextView(this);
        textName.setText(name);
        TextView textDate = new TextView(this);
        textDate.setText("Expires "+date.toString());
        TextView textId = new TextView(this);
        textId.setText(Integer.toString(id));

        LinearLayout L2 = new LinearLayout(this);
        L2.setOrientation(LinearLayout.HORIZONTAL);
        L2.setPadding(10,10,10,10);
        TextView daysLeft = new TextView(this);
        Date today = new Date();
        daysLeft.setText(Long.toString(TimeUnit.DAYS.convert(date.getTime()-today.getTime(),TimeUnit.MILLISECONDS))+" days left.");
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        L2.addView(daysLeft,param);

        Button delButton = new Button(this);
        delButton.setBackgroundColor(Color.parseColor("#000000"));
        delButton.setText("Delete");
        if(status == -1) {
            delButton.setTextColor(Color.parseColor("#ff0000"));
        }
        else if(status == 0){
            delButton.setTextColor(Color.parseColor("#ff6103"));
        }
        else if(status == 1){
            delButton.setTextColor(Color.parseColor("#1e90ff"));
        }
        else { //status = 2
            delButton.setTextColor(Color.parseColor("#00ff00"));
        }
        delButton.setGravity(Gravity.RIGHT);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(v);
            }
        });
        delButton.setGravity(Gravity.RIGHT);
        L2.addView(delButton);

        L.addView(textName);
        L.addView(textDate);
        L.addView(textId);
        L.addView(L2);

        return L;
    }

    public void delete(View view){
        //remove the item from the display
        LinearLayout L = (LinearLayout)view.getParent().getParent();
        LinearLayout items = (LinearLayout)findViewById(R.id.items);
        items.removeView(L);

        //remove the item from the database
        Database db = new Database(this);
        db.delete(Integer.parseInt(((TextView)L.getChildAt(2)).getText().toString()));
    }
}
