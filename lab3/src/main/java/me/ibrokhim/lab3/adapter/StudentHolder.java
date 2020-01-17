package me.ibrokhim.lab3.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import me.ibrokhim.lab3.R;

public class StudentHolder extends RecyclerView.ViewHolder {
    public final TextView student;

    public StudentHolder(@NonNull ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.lab3_item_student, parent, false));
        student = itemView.findViewById(R.id.student);
    }
}
