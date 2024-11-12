package com.example.collegeapp.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.collegeapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GalleryFragment extends Fragment implements TaskAdapter.TaskCompletionListener {

    private TaskViewModel taskViewModel;
    private TaskAdapter adapter;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        ListView listView = rootView.findViewById(R.id.list_view);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        adapter = new TaskAdapter(getContext(), new ArrayList<>(), this);
        listView.setAdapter(adapter);

        taskViewModel.getAllTasks().observe(getViewLifecycleOwner(), tasks -> {
            if (tasks != null) {
                adapter.clear();
                adapter.addAll(tasks);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "No tasks available", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton buttonAddTask = rootView.findViewById(R.id.button_add_task);
        buttonAddTask.setOnClickListener(view -> {
            AddEditTaskDialog dialog = new AddEditTaskDialog(taskViewModel); // Pass the ViewModel to the dialog
            dialog.show(getParentFragmentManager(), "AddEditTaskDialog");
        });

        return rootView;
    }

    @Override
    public void onTaskCompleted(Task task) {
        taskViewModel.delete(task);
        Toast.makeText(getContext(), "Task completed and deleted: " + task.getTitle(), Toast.LENGTH_SHORT).show();
    }
}

