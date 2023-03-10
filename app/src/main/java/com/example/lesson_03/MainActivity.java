package com.example.lesson_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lesson_03.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvSelectedTheme.setOnClickListener(v -> {
            Toast.makeText(this, "Hello!", Toast.LENGTH_SHORT).show();
        });
        String[] themes = {"dark", "light", "android"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, themes);
        binding.spThemes.setAdapter(adapter);
        binding.spThemes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedTheme = themes[position];
                binding.tvSelectedTheme.setText(selectedTheme);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        binding.btnNext.setOnClickListener(v -> {
            startActivity(new Intent(this, ListContactActivity.class));
        });
    }
}