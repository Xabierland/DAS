package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSpanish = findViewById(R.id.buttonSpanish);
        Button buttonBasque = findViewById(R.id.buttonBasque);
        Button buttonEnglish = findViewById(R.id.buttonEnglish);
        Button buttonActivity2 = findViewById(R.id.buttonActivity2);

        buttonSpanish.setOnClickListener(view -> setLocale("es"));
        buttonBasque.setOnClickListener(view -> setLocale("eu"));
        buttonEnglish.setOnClickListener(view -> setLocale("en"));
        
        buttonActivity2.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });
    }
}