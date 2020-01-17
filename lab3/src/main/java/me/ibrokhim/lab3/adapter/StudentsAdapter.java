package me.ibrokhim.lab3.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.ibrokhim.lab3.Student;

public class StudentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_NUMBER = 0;
    public static final int TYPE_STUDENT = 1;

    private List<Student> students = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_NUMBER:
                return new NumberHolder(parent);
            case TYPE_STUDENT:
                return new StudentHolder(parent);
        }

        throw new IllegalArgumentException("Unknown viewType = " + viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_NUMBER:
                NumberHolder numberHolder = (NumberHolder) holder;
                numberHolder.bind((position + 1) / 2);
                break;
            case TYPE_STUDENT:
                StudentHolder studentHolder = (StudentHolder) holder;
                Student student = students.get(position / 2);
                studentHolder.student.setText(
                        student.firstName + " " + student.middleName + " " + student.lastName
                );
                break;
        }
    }

    @Override
    public int getItemCount() {
        return students.size() * 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? TYPE_NUMBER : TYPE_STUDENT;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
