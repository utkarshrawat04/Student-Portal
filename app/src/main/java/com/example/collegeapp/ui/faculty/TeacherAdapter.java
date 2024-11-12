package com.example.collegeapp.ui.faculty;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.collegeapp.R;

import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {
    private List<Teacherdata> list;
    private Context context;


    public TeacherAdapter(List<Teacherdata> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.faculty_item_layout, parent, false);
        return new TeacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
        Teacherdata item = list.get(position);

        // Set text for name, email, and post
        holder.name.setText(item.getName());
        holder.email.setText(item.getEmail());
        holder.post.setText(item.getPost());

        // Get the image URL
        String imageUrl = item.getImage();

        // Log the URL for debugging
        Log.d("TeacherAdapter", "Image URL: " + imageUrl);

        if (imageUrl != null && !imageUrl.isEmpty()) {
            // Load image with Picasso
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.avatar) // Optional: placeholder image
                    .error(R.drawable.avatar) // Optional: error image
                    .into(holder.imageView);
        } else {
            // Set a default image if URL is invalid or empty
            holder.imageView.setImageResource(R.drawable.avatar);
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class TeacherViewHolder extends RecyclerView.ViewHolder {
        private TextView name, email, post;

        private ImageView imageView;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.teacherName);
            email = itemView.findViewById(R.id.teacherEmail);
            post = itemView.findViewById(R.id.teacherPost);
            imageView = itemView.findViewById(R.id.teacherImage);

        }
    }
}

