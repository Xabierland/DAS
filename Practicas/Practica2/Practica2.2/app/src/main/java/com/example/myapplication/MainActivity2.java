package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity2 extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button buttonActivity1 = findViewById(R.id.buttonActivity1);
        
        buttonActivity1.setOnClickListener(view -> {
            finish(); // Esto volverá a la actividad anterior
        });
    }
}