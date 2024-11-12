package com.example.collegeapp.ui.faculty;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.collegeapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FacultyFragment extends Fragment {

    private RecyclerView MECHDepartment, AIDepartment, csDepartment, CyberDepartment;
    private LinearLayout MECHNoData, AINoData, csNoData, CyberNoData;

    private List<Teacherdata> list1, list2, list3, list4;

    private DatabaseReference reference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faculty, container, false);


        MECHDepartment = view.findViewById(R.id.MECHDepartment);
        AIDepartment = view.findViewById(R.id.AIDepartment);
        csDepartment = view.findViewById(R.id.csDepartment);
        CyberDepartment = view.findViewById(R.id.CyberDepartment);

        MECHNoData = view.findViewById(R.id.MECHNoData);
        AINoData = view.findViewById(R.id.AINoData);
        csNoData = view.findViewById(R.id.csNoData);
        CyberNoData = view.findViewById(R.id.CyberNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("Teacher");

        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();

        csDepartment();
        MECHDepartment();
        CyberDepartment();
        AIDepartment();

        return view;
    }

    private void csDepartment() {
        DatabaseReference dbref = reference.child("Computer Science");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1.clear();
                if (!snapshot.exists()) {
                    csNoData.setVisibility(View.VISIBLE);
                } else {
                    csNoData.setVisibility(View.GONE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Teacherdata data = dataSnapshot.getValue(Teacherdata.class);
                        list1.add(data);
                    }
                    setupRecyclerView(csDepartment, list1, "Computer Science");
                }
                csDepartment.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void MECHDepartment() {
        DatabaseReference dbref = reference.child("Mechanical");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2.clear();
                if (!snapshot.exists()) {
                    MECHNoData.setVisibility(View.VISIBLE);
                } else {
                    MECHNoData.setVisibility(View.GONE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Teacherdata data = dataSnapshot.getValue(Teacherdata.class);
                        list2.add(data);
                    }
                    setupRecyclerView(MECHDepartment, list2, "Mechanical");
                }
                MECHDepartment.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void CyberDepartment() {
        DatabaseReference dbref = reference.child("Cyber Security");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3.clear();
                if (!snapshot.exists()) {
                    CyberNoData.setVisibility(View.VISIBLE);
                } else {
                    CyberNoData.setVisibility(View.GONE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Teacherdata data = dataSnapshot.getValue(Teacherdata.class);
                        list3.add(data);
                    }
                    setupRecyclerView(CyberDepartment, list3, "Cyber Security");
                }
                CyberDepartment.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AIDepartment() {
        DatabaseReference dbref = reference.child("AI");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list4.clear();
                if (!snapshot.exists()) {
                    AINoData.setVisibility(View.VISIBLE);
                } else {
                    AINoData.setVisibility(View.GONE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Teacherdata data = dataSnapshot.getValue(Teacherdata.class);
                        list4.add(data);
                    }
                    setupRecyclerView(AIDepartment, list4, "AI");
                }
                AIDepartment.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecyclerView(RecyclerView recyclerView, List<Teacherdata> dataList, String department) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TeacherAdapter adapter = new TeacherAdapter(dataList, getContext());
        recyclerView.setAdapter(adapter);
    }
}