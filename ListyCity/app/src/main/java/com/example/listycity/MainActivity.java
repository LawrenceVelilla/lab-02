package com.example.listycity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    Integer selectedCity = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button confirmButton = (Button) findViewById(R.id.add_confirmation);
        EditText cityToAdd = (EditText) findViewById(R.id.add_city_input);

        confirmButton.setVisibility(View.GONE);
        cityToAdd.setVisibility(View.GONE);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi", "Manila"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        Button addButton = (Button) findViewById(R.id.add_button);
        Button deleteButton = (Button) findViewById(R.id.delete_city);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Add Button Clicked");
                if (cityToAdd.getVisibility() == View.GONE) {
                    cityToAdd.setVisibility(View.VISIBLE);
                    confirmButton.setVisibility(View.VISIBLE);
                }
                else if (cityToAdd.getVisibility() == View.VISIBLE){
                    cityToAdd.setVisibility(View.GONE);
                    confirmButton.setVisibility(View.GONE);
                }

            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = position;
                view.setBackgroundColor(Color.LTGRAY);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        System.out.println("Delete Button Pressed");
                        dataList.remove(position);
                        cityAdapter.notifyDataSetChanged();
                        selectedCity = null;
                        view.setBackgroundColor(Color.TRANSPARENT);
                    }
                });


            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = cityToAdd.getText().toString();
                dataList.add(city); // Add to end of list
                cityAdapter.notifyDataSetChanged();

                cityToAdd.setVisibility(View.GONE);
                confirmButton.setVisibility(View.GONE);
            }
        });

    }

}