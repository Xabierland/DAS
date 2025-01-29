package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ponemos el texto del EditText en el TextView
                textView.setText(editText.getText().toString());
                //No se que hace esto, yo no he hecho nada se lo he copiado a ibai
                // Crear y aplicar la animación de rotación
                RotateAnimation rotateAnimation = new RotateAnimation(
                    0f, 45f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
                );
                rotateAnimation.setDuration(500);
                rotateAnimation.setFillAfter(true);
                textView.startAnimation(rotateAnimation);
            }
        });
    }
}