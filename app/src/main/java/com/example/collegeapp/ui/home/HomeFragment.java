package com.example.collegeapp.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.collegeapp.R;

public class HomeFragment extends Fragment {

    private static final String PREFS_NAME = "AppPrefs";
    private static final String KEY_NOTES = "notes";

    private EditText notesEditText;

    // SharedPreferences for saving notes
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        notesEditText = rootView.findViewById(R.id.edit_text_notes);

        // Initialize SharedPreferences
        sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Load saved notes if available
        String savedNote = sharedPreferences.getString(KEY_NOTES, "");
        notesEditText.setText(savedNote);

        // Save notes when text changes
        notesEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable editable) {
                // Save the notes to SharedPreferences
                sharedPreferences.edit().putString(KEY_NOTES, editable.toString()).apply();
            }
        });

        return rootView;
    }
}
