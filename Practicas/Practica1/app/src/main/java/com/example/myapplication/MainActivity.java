package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.LinearLayout;
import android.view.Gravity; // Necesario para usar Gravity
import android.view.ViewGroup.LayoutParams; // Necesario para usar LayoutParams
import android.widget.TextView; //Hay que meter esto para poder usar TextView
import android.widget.Button;
import android.widget.EditText;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //! Definimos los objetos
        // Layout principal
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        // Layout Superior
        LinearLayout layoutSuperior = new LinearLayout(this);
        layoutSuperior.setOrientation(LinearLayout.VERTICAL);
        layoutSuperior.setGravity(Gravity.CENTER);
        layoutSuperior.setLayoutParams(new LinearLayout.LayoutParams(
            LayoutParams.MATCH_PARENT, 
            0,
            1.0f  // Peso 1 para que ocupe la mitad
        ));
        layoutSuperior.setBackgroundColor(android.graphics.Color.BLACK); // Color gris claro para distinguir los layouts

        // Layout Inferior
        LinearLayout layoutInferior = new LinearLayout(this);
        layoutInferior.setOrientation(LinearLayout.VERTICAL);
        layoutInferior.setGravity(Gravity.CENTER);
        layoutInferior.setLayoutParams(new LinearLayout.LayoutParams(
            LayoutParams.MATCH_PARENT, 
            0,
            1.0f  // Peso 1 para que ocupe la mitad
        ));
        layoutInferior.setBackgroundColor(android.graphics.Color.BLACK); // Color gris más claro

        //Mostrar un mensaje
        TextView mensaje = new TextView(this);
        mensaje.setText("Hola Mundo");

        // Contador
        TextView etiquetaContador = new TextView(this);
        etiquetaContador.setText("Contador: 0");
        Button botonContador = new Button(this);
        botonContador.setText("Incrementar contador");
        botonContador.setOnClickListener(new View.OnClickListener() {
            int contador = 0;
            @Override
            public void onClick(View v) {
                contador++;
                etiquetaContador.setText("Contador: " + contador);
            }
        });

        // Cambiar color de fondo
        Button botonCambiarColor = new Button(this);
        botonCambiarColor.setText("Cambiar color de fondo");
        botonCambiarColor.setOnClickListener(new View.OnClickListener() {
            boolean color = true;
            @Override
            public void onClick(View v) {
                if (color) {
                    layoutSuperior.setBackgroundColor(android.graphics.Color.BLUE);
                } else {
                    layoutSuperior.setBackgroundColor(android.graphics.Color.BLACK);
                }
                color = !color;
            }
        });

        // Ocultar elementos
        TextView etiquetaOcultar = new TextView(this);
        etiquetaOcultar.setText("Elementos ocultos");
        Button botonOcultar = new Button(this);
        botonOcultar.setText("Ocultar elementos");
        botonOcultar.setOnClickListener(new View.OnClickListener() {
            boolean oculto = false;
            @Override
            public void onClick(View v) {
                if (oculto) {
                    etiquetaContador.setVisibility(View.VISIBLE);
                    botonContador.setVisibility(View.VISIBLE);
                    botonCambiarColor.setVisibility(View.VISIBLE);
                    oculto = false;
                } else {
                    etiquetaContador.setVisibility(View.INVISIBLE);
                    botonContador.setVisibility(View.INVISIBLE);
                    botonCambiarColor.setVisibility(View.INVISIBLE);
                    oculto = true;
                }
            }
        });

        // Intercambiar posiciones del los layouts
        Button botonCambiar = new Button(this);
        botonCambiar.setText("Cambiar layouts");
        botonCambiar.setOnClickListener(new View.OnClickListener() {
            boolean cambio = true;
            @Override
            public void onClick(View v) {
                if (cambio) {
                    mainLayout.removeView(layoutSuperior);
                    mainLayout.removeView(layoutInferior);
                    mainLayout.addView(layoutInferior);
                    mainLayout.addView(layoutSuperior);
                    cambio = false;
                } else {
                    mainLayout.removeView(layoutSuperior);
                    mainLayout.removeView(layoutInferior);
                    mainLayout.addView(layoutSuperior);
                    mainLayout.addView(layoutInferior);
                    cambio = true;
                }
            }
        });
        

        // Campo edit text
        EditText campoTexto = new EditText(this);
        campoTexto.setHint("Introduce un texto");
        Button botonTexto = new Button(this);
        botonTexto.setText("Mostrar texto");
        botonTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView mensaje = new TextView(MainActivity.this);
                mensaje.setText("Hola " + campoTexto.getText().toString());
                layoutInferior.addView(mensaje);
            }
        });

        // Dos layout diferentes
            // Elementos del layout superior
        layoutSuperior.addView(mensaje);
        layoutSuperior.addView(etiquetaContador);
        layoutSuperior.addView(botonContador);
        layoutSuperior.addView(botonCambiarColor);
        layoutSuperior.addView(etiquetaOcultar);
        layoutSuperior.addView(botonOcultar);
        layoutSuperior.addView(campoTexto);
        layoutSuperior.addView(botonTexto);
            // Elementos del layout inferior
        layoutInferior.addView(botonCambiar);
            // Añadimos los layouts secundarios al principal
        mainLayout.addView(layoutSuperior);
        mainLayout.addView(layoutInferior);

            // Establecemos el layout principal
        setContentView(mainLayout);
    }
}