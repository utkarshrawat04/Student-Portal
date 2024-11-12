package com.example.collegeapp.ui.gallery;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.collegeapp.R;

import java.util.Calendar;

public class AddEditTaskDialog extends DialogFragment {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText dueDateEditText;
    private TaskViewModel taskViewModel;

    public AddEditTaskDialog(TaskViewModel taskViewModel) {
        this.taskViewModel = taskViewModel;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_edit_task, null);

        titleEditText = view.findViewById(R.id.edit_text_title);
        descriptionEditText = view.findViewById(R.id.edit_text_description);
        dueDateEditText = view.findViewById(R.id.edit_text_due_date);

        dueDateEditText.setOnClickListener(v -> showDatePickerDialog());

        Button saveButton = view.findViewById(R.id.button_save_task);
        saveButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            String dueDate = dueDateEditText.getText().toString();

            Task newTask = new Task(title, description, dueDate);
            taskViewModel.insert(newTask);  // Save the task to the ViewModel and database

            dismiss();  // Close the dialog
        });

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                (view, year1, month1, dayOfMonth) -> {
                    String formattedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                    dueDateEditText.setText(formattedDate);
                },
                year, month, day
        );

        datePickerDialog.show();
    }
}
