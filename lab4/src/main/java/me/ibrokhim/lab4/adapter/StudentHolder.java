package me.ibrokhim.lab4.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import me.ibrokhim.lab4.R;

public class StudentHolder extends RecyclerView.ViewHolder {
    public final TextView student;
    public final ImageView photo;

    public StudentHolder(@NonNull ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.lab4_item_student, parent, false));
        student = itemView.findViewById(R.id.student);
        photo = itemView.findViewById(R.id.student_photo);
    }
}
