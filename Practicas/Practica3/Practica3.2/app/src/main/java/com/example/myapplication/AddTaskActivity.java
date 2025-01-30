package com.example.myapplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        findViewById(R.id.btnAddTask).setOnClickListener(v -> {
            EditText editText = findViewById(R.id.etTaskDescription);
            String task = editText.getText().toString();
            
            if (!task.isEmpty()) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("task_description", task);
                setResult(Activity.RESULT_OK, resultIntent);
            } else {
                setResult(Activity.RESULT_CANCELED);
            }
            
            finish();
        });
    }
}