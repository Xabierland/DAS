package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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

        // Lista de tareas hardcodeadas
        String[] tasks = {
                "Si besarte fuera un delito, cumpliría cadena perpetua en tus labios. 🤢",
                "Eres el WiFi de mi vida, porque sin ti me siento desconectado. 📶💔",
                "Quisiera ser lágrima para nacer en tus ojos, vivir en tu mejilla y morir en tus labios. 😬",
                "Si la belleza fuera tiempo, tú serías la eternidad… y yo, un viajero perdido en tu mirada. ⏳🥴",
                "No soy Google, pero tengo todo lo que buscas… en mi corazón. ❤️🔍"
        };
        
        // Obtener el ListView y asignarle el adaptador con la lista de tareas
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        listView.setAdapter(adapter);

        // Coje el Buttom y haz que tenga un snackbar que te lleve a un video de youtube
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Snackbar.make(v, "¿Quieres ver un video de gatitos?", Snackbar.LENGTH_LONG)
                    .setAction("¡Claro!", view -> {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
                        startActivity(intent);
                    })
                    .show();
        });
    }
}