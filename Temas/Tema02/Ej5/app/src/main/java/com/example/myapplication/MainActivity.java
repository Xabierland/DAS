package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("onCreate","Paso por onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onStart","Paso por onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onResume","Paso por onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("onPause","Paso por onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("onStop","Paso por onStop");
    }
}