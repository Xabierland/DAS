package com.example.myapplication;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<String> taskList;

    public TaskAdapter(List<String> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        String task = taskList.get(position);
        holder.taskText.setText(task);

        // Configurar el listener de pulsación larga
        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                .setTitle("Eliminar tarea")
                .setMessage("¿Estás seguro de que quieres eliminar esta tarea?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    // Eliminar el item
                    taskList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, taskList.size());
                })
                .setNegativeButton("No", null)
                .show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskText;

        TaskViewHolder(View itemView) {
            super(itemView);
            taskText = itemView.findViewById(R.id.taskText);
        }
    }
}