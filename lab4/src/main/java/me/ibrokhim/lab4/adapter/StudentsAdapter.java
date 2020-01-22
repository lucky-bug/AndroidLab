package me.ibrokhim.lab4.adapter;

import android.graphics.Color;
import android.net.Uri;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.ibrokhim.lab4.db.Lab4Database;
import me.ibrokhim.lab4.db.Student;
import me.ibrokhim.lab4.db.StudentDao;

public class StudentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    public static final int TYPE_NUMBER = 0;
    public static final int TYPE_STUDENT = 1;

    private List<Student> students = new ArrayList<>();

    public String filterQuery = "";

    private StudentDao studentDao;

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        studentDao = Lab4Database.getInstance(parent.getContext()).studentDao();

        switch (viewType) {
            case TYPE_NUMBER:
                return new NumberHolder(parent);
            case TYPE_STUDENT:
                return new StudentHolder(parent);
        }
        throw new IllegalArgumentException("unknown viewType = " + viewType);
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

                studentHolder.student.setText(student.lastName + " " + student.firstName
                        + " " + student.secondName);

                if (!TextUtils.isEmpty(student.photoPath)) {
                    studentHolder.photo.setVisibility(View.VISIBLE);
                    studentHolder.photo.setImageURI(Uri.parse(student.photoPath));
                } else {
                    studentHolder.photo.setVisibility(View.GONE);
                    studentHolder.photo.setImageURI(null);
                }
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                filterQuery = constraint.toString();
                List<Student> newStudents;

                if (filterQuery.isEmpty()) {
                    newStudents = studentDao.getAll();
                    Log.d("Ibrokhim Check This","Heyyooo");
                } else {
                    newStudents = studentDao.findByName(filterQuery);
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = newStudents;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                setStudents((List<Student>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
