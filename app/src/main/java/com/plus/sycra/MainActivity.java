package com.plus.sycra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.plus.sycra.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.plus.sycra.databinding.ActivityMainBinding binding = ActivityMainBinding
                .inflate(getLayoutInflater());
       // binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Access views using binding object
        binding.discover.setText(R.string.discover_txt);
        binding.grouping.setText(R.string.grouping_txt);

        //Toast message for discover button
        binding.discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create and show a toast message for discover button
                Toast.makeText(MainActivity.this, "You clicked discover", Toast.LENGTH_SHORT).show();
            }
        });

        //Toast message for grouping button
        binding.grouping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create and show a toast message for grouping button
                Toast.makeText(MainActivity.this, "You clicked grouping", Toast.LENGTH_SHORT).show();
            }
        });
    }





}