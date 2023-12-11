package com.example.academicagendav1.Tasks.Tasks;

import static android.os.Build.VERSION_CODES.R;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    private final TextView taskTextView;

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        taskTextView = itemView.findViewById(R.id.taskTextView);
    }

    public void bind(String task) {
        taskTextView.setText(task);
    }
}