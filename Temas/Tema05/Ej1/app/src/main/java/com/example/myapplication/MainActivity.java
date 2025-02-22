package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        // Logica del boton que al darle se abre un dialogo y recoge la fecha y hora para
        // despues mostrarla en un TextView
        findViewById(R.id.btnDate).setOnClickListener(v -> {
            new DateTimePickerDialogFragment().show(getSupportFragmentManager(), "DateTimePickerDialogFragment");
        });

        // Boton para cerrar la aplicacion que abre un dialogo para confirmarlo
        findViewById(R.id.btnClose).setOnClickListener(v -> {
            new CloseAppDialogFragment().show(getSupportFragmentManager(), "CloseAppDialogFragment");
        });
    }

    public void onDateTimeSelected(int year, int month, int day, int hour, int minute) {
        String dateTime = day + "/" + (month + 1) + "/" + year + " " + hour + ":" + minute;
        findViewById(R.id.tvDate).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.textViewDateTime)).setText(dateTime);
    }
}