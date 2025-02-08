package com.example.myapplication;

import javax.swing.text.html.ListView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// MainActivity.java
public class MainActivity extends AppCompatActivity {
    private final ActivityResultLauncher<Intent> getText = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    String text = data.getStringExtra("text");
                    TextView textView = findViewById(R.id.textView);
                    textView.setText(text);
                }
            }
        }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity2.class);
            getText.launch(intent);  // Aquí está el cambio principal
        });
    }
}