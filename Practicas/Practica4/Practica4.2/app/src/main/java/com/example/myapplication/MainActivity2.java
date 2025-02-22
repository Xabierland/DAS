package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.Random;

import com.example.myapplication.MainActivity;

import android.os.Handler;



public class MainActivity2 extends AppCompatActivity {
    private int numeroObjetivo;
    private int intentosRestantes;
    private int nivel;
    private TextView tvNivel, tvIntentos, tvIntentosAnteriores, tvPista;
    private EditText etNumero;
    private Button btnAdivinar;
    private StringBuilder historialIntentos;
    private static final String CHANNEL_ID = "NumerosmuertosChannel";
    private int intentosIniciales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Inicializar vistas
        tvNivel = findViewById(R.id.tvNivel);
        tvIntentos = findViewById(R.id.tvIntentos);
        tvIntentosAnteriores = findViewById(R.id.tvIntentosAnteriores);
        etNumero = findViewById(R.id.etNumero);
        btnAdivinar = findViewById(R.id.btnAdivinar);
        historialIntentos = new StringBuilder();

        // Configurar nivel y número objetivo
        nivel = getIntent().getIntExtra("nivel", 0);
        configurarNivel();
        
        // Crear canal de notificaciones
        createNotificationChannel();

        // Configurar botón de adivinar
        btnAdivinar.setOnClickListener(v -> verificarNumero());
    }

    private void configurarNivel() {
        Random random = new Random();
        switch (nivel) {
            case 0: // Fácil
                numeroObjetivo = random.nextInt(1000); // 3 dígitos
                intentosRestantes = 15;
                tvNivel.setText("Nivel: Fácil");
                etNumero.setHint("Introduce un número de 3 dígitos");
                break;
            case 1: // Normal
                numeroObjetivo = random.nextInt(10000); // 4 dígitos
                intentosRestantes = 10;
                tvNivel.setText("Nivel: Normal");
                etNumero.setHint("Introduce un número de 4 dígitos");
                break;
            case 2: // Difícil
                numeroObjetivo = random.nextInt(100000); // 5 dígitos
                intentosRestantes = 8;
                tvNivel.setText("Nivel: Difícil");
                etNumero.setHint("Introduce un número de 5 dígitos");
                break;
        }
        intentosIniciales = intentosRestantes;
        actualizarIntentosRestantes();

        // Añadir esto para mostrar el número objetivo
        tvPista = findViewById(R.id.tvPista);
        tvPista.setText("Número a adivinar: " + numeroObjetivo);
    }

    private void verificarNumero() {
        String numeroIngresado = etNumero.getText().toString();
        if (numeroIngresado.isEmpty()) {
            Toast.makeText(this, "Por favor, introduce un número", Toast.LENGTH_SHORT).show();
            return;
        }

        int numero = Integer.parseInt(numeroIngresado);
        intentosRestantes--;
        
        // Calcular muertos y heridos
        int[] resultado = calcularMuertosYHeridos(numero);
        int muertos = resultado[0];
        int heridos = resultado[1];

        // Actualizar historial
        historialIntentos.insert(0, numeroIngresado + " -> " + muertos + " muertos, " + heridos + " heridos\n");
        tvIntentosAnteriores.setText(historialIntentos.toString());

        if (numero == numeroObjetivo) {
            // Victoria
            mostrarNotificacionVictoria();
        } else if (intentosRestantes == 0) {
            // Derrota
            mostrarDialogoDerrota();
        } else {
            // Continuar jugando
            Toast.makeText(this, muertos + " muertos, " + heridos + " heridos", Toast.LENGTH_SHORT).show();
            actualizarIntentosRestantes();
            etNumero.setText("");
        }
    }

    private int[] calcularMuertosYHeridos(int numeroIngresado) {
        String numObj = String.valueOf(numeroObjetivo);
        String numIng = String.valueOf(numeroIngresado);
        
        // Rellenar con ceros a la izquierda si es necesario
        while (numIng.length() < numObj.length()) {
            numIng = "0" + numIng;
        }

        int muertos = 0;
        int heridos = 0;
        boolean[] usadosObj = new boolean[numObj.length()];
        boolean[] usadosIng = new boolean[numIng.length()];

        // Contar muertos
        for (int i = 0; i < numObj.length(); i++) {
            if (numObj.charAt(i) == numIng.charAt(i)) {
                muertos++;
                usadosObj[i] = true;
                usadosIng[i] = true;
            }
        }

        // Contar heridos
        for (int i = 0; i < numObj.length(); i++) {
            if (!usadosIng[i]) {
                for (int j = 0; j < numObj.length(); j++) {
                    if (!usadosObj[j] && numIng.charAt(i) == numObj.charAt(j)) {
                        heridos++;
                        usadosObj[j] = true;
                        break;
                    }
                }
            }
        }

        return new int[]{muertos, heridos};
    }

    private void actualizarIntentosRestantes() {
        tvIntentos.setText("Intentos restantes: " + intentosRestantes);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Números Muertos",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void mostrarNotificacionVictoria() {
        // Crear el canal de notificación primero
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Números Muertos",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Canal para el juego Números Muertos");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            // Crear alta prioridad
            channel.setImportance(NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
    
        // Crear los intents para las acciones
        Intent intentFinish = new Intent(this, MainActivity.class);
        intentFinish.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intentFinish.putExtra("action", "exit");
        PendingIntent pendingIntentFinish = PendingIntent.getActivity(this, 1, intentFinish,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
    
        // Modificar el intent de replay para que comience una nueva partida
        Intent intentReplay = new Intent(this, MainActivity.class);
        intentReplay.putExtra("action", "replay");
        intentReplay.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntentReplay = PendingIntent.getActivity(this, 2, intentReplay,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
    
        // Construir la notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("¡Victoria!")
            .setContentText("Has ganado en " + (intentosIniciales - intentosRestantes) + " intentos")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(new long[]{0, 1000, 500, 1000})
            .addAction(android.R.drawable.ic_menu_close_clear_cancel, "Finalizar", pendingIntentFinish)
            .addAction(android.R.drawable.ic_menu_rotate, "Jugar de nuevo", pendingIntentReplay)
            .setAutoCancel(true)
            .setWhen(System.currentTimeMillis())
            .setFullScreenIntent(pendingIntentFinish, true);

        // Mostrar la notificación
        NotificationManager notificationManager = 
            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
        
        // Deshabilitar el botón y el EditText
        btnAdivinar.setEnabled(false);
        etNumero.setEnabled(false);
        
        // Actualizar el texto para mostrar que ha ganado
        tvPista.setText("¡Has ganado! El número era: " + numeroObjetivo);
    }
    
    private void mostrarDialogoDerrota() {
        new AlertDialog.Builder(this)
                .setTitle("Fin del juego")
                .setMessage("Te has quedado sin intentos. El número era: " + numeroObjetivo)
                .setPositiveButton("OK", (dialog, which) -> {
                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                })
                .setCancelable(false)
                .show();
    }
}