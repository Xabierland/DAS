package com.example.myapplication;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView contadorTextView;
    private TextView estadosTextView;
    private int contador = 0;
    private static final String KEY_CONTADOR = "contador";
    private static final String KEY_ESTADOS = "estados";
    private StringBuilder estadosBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        contadorTextView = findViewById(R.id.contadorTextView);
        estadosTextView = findViewById(R.id.estadosTextView);
        
        agregarEstado("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        agregarEstado("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        agregarEstado("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        agregarEstado("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        agregarEstado("onStop");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_CONTADOR, ++contador);
        savedInstanceState.putString(KEY_ESTADOS, estadosBuilder.toString());
        agregarEstado("onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        contador = savedInstanceState.getInt(KEY_CONTADOR, 0);
        String estados = savedInstanceState.getString(KEY_ESTADOS, "");
        estadosBuilder = new StringBuilder(estados);
        agregarEstado("onRestoreInstanceState");
        actualizarVistas();
    }

    private void agregarEstado(String estado) {
        estadosBuilder.append(" -> ").append(estado);
        actualizarVistas();
    }

    private void actualizarVistas() {
        if (contadorTextView != null) {
            contadorTextView.setText("Número de rotaciones: " + contador);
        }
        if (estadosTextView != null) {
            estadosTextView.setText(estadosBuilder.toString());
        }
    }
}