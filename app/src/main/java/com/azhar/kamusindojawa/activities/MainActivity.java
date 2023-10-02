package com.azhar.kamusindojawa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.azhar.kamusindojawa.R;

public class MainActivity extends AppCompatActivity {

    ImageView imgIndo, imgJawa;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgIndo = findViewById(R.id.imgIndo);
        imgJawa = findViewById(R.id.imgJawa);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        imgIndo.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, IndonesiaJawaActivity.class);
            startActivity(intent);
        });

        imgJawa.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, JawaIndonesiaActivity.class);
            startActivity(intent);
        });
    }

}