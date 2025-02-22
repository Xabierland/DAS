package com.example.myapplication;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_PERMISSION_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getIntent().hasExtra("action")) {
            String action = getIntent().getStringExtra("action");
            if ("exit".equals(action)) {
                finishAffinity(); // Esto cerrará todas las actividades de la aplicación
                return;
            } else if ("replay".equals(action)) {
                mostrarDialogoNivel();
            }
        }

        // Comprobar si viene de la notificación con acción de replay
        if (getIntent().hasExtra("action") && "replay".equals(getIntent().getStringExtra("action"))) {
            mostrarDialogoNivel();
        }

        // Inicializar botones
        Button btnJugar = findViewById(R.id.btnJugar);
        Button btnInstrucciones = findViewById(R.id.btnInstrucciones);

        // Configurar listeners
        btnJugar.setOnClickListener(v -> mostrarDialogoNivel());
        btnInstrucciones.setOnClickListener(v -> mostrarInstrucciones());
    }

    private void solicitarPermisoNotificaciones() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != 
                PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, 
                    new String[]{Manifest.permission.POST_NOTIFICATIONS}, 
                    NOTIFICATION_PERMISSION_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == NOTIFICATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "El permiso de notificaciones es necesario para mostrar los resultados", 
                    Toast.LENGTH_LONG).show();
            }
        }
    }

    private void mostrarDialogoNivel() {
        final String[] niveles = {"Fácil", "Normal", "Difícil"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona el nivel")
                .setItems(niveles, (dialog, which) -> {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("nivel", which); // 0: Fácil, 1: Normal, 2: Difícil
                    startActivity(intent);
                })
                .setNegativeButton("Cancelar", null);
        builder.create().show();
    }

    private void mostrarInstrucciones() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Instrucciones")
                .setMessage("El juego de los números muertos consiste en:\n\n" +
                        "- Adivinar un número con X dígitos\n" +
                        "- Por cada intento, se te dirá:\n" +
                        " * Números muertos: dígitos correctos en posición correcta\n" +
                        " * Números heridos: dígitos correctos en posición incorrecta\n\n" +
                        "Ejemplo: Si el número es 4314\n" +
                        "- Si dices 8765: 0 muertos, 0 heridos\n" +
                        "- Si dices 1098: 0 muertos, 1 herido\n" +
                        "- Si dices 4531: 1 muerto, 2 heridos")
                .setPositiveButton("OK", null);
        builder.create().show();
    }
}