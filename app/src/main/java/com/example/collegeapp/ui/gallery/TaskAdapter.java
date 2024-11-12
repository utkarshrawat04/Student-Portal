package com.example.collegeapp.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.example.collegeapp.R;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    public interface TaskCompletionListener {
        void onTaskCompleted(Task task);
    }

    private TaskCompletionListener listener;

    public TaskAdapter(Context context, List<Task> tasks, TaskCompletionListener listener) {
        super(context, 0, tasks != null ? tasks : new ArrayList<>());
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        }

        Task currentTask = getItem(position);

        if (currentTask != null) {
            TextView titleTextView = convertView.findViewById(R.id.text_view_title);
            TextView descriptionTextView = convertView.findViewById(R.id.text_view_description);
            TextView dueDateTextView = convertView.findViewById(R.id.text_view_due_date);
            Button completeButton = convertView.findViewById(R.id.button_complete);

            titleTextView.setText(currentTask.getTitle());
            descriptionTextView.setText(currentTask.getDescription());

            String dueDate = currentTask.getDueDate();
            if (dueDate != null && !dueDate.isEmpty()) {
                dueDateTextView.setText("Due Date: " + dueDate);
            } else {
                dueDateTextView.setText("Due Date: Not Set");
            }

            completeButton.setOnClickListener(v -> {
                // Show confirmation dialog before marking task as complete
                new AlertDialog.Builder(getContext())
                        .setTitle("Mark Task as Complete")
                        .setMessage("Are you sure you want to mark this task as completed?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            // Mark the task as completed if the user presses "Yes"
                            currentTask.setCompleted(true);
                            if (listener != null) {
                                listener.onTaskCompleted(currentTask);
                            }
                        })
                        .setNegativeButton("No", (dialog, which) -> {
                            // Do nothing if the user presses "No"
                            dialog.dismiss();
                        })
                        .create()
                        .show();
            });
        }

        return convertView;
    }
}
