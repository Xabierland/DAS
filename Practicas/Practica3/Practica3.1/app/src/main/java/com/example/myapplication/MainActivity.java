package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonToast = findViewById(R.id.buttonToast);
        Button buttonSnackbar = findViewById(R.id.buttonSnackbar);

        buttonToast.setOnClickListener(view -> {
            Toast.makeText(this, "This is a toast", Toast.LENGTH_SHORT).show();
        });

        //Snackbar que al hacer click abre un video de youtube
        buttonSnackbar.setOnClickListener(view -> {
            Snackbar snackbar = Snackbar.make(view, "This is a snackbar", Snackbar.LENGTH_LONG);
            snackbar.setAction("Open", v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=E4WlUXrJgy4"));
                startActivity(intent);
            });
            snackbar.setActionTextColor(Color.BLUE);
            snackbar.show();
        });
    }
}