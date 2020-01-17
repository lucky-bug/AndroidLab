package me.ibrokhim.lab3.adapter;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.ibrokhim.lab3.Lab3Activity;
import me.ibrokhim.lab3.R;
import me.ibrokhim.lab3.Student;
import me.ibrokhim.lab3.StudentsCache;

public class StudentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    public static final int TYPE_NUMBER = 0;
    public static final int TYPE_STUDENT = 1;

    private List<Student> students = new ArrayList<>();

    public String filterQuery = "";

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
                String fullName = student.firstName + " " + student.middleName + " " + student.lastName;
                Spannable spanString = Spannable.Factory.getInstance().newSpannable(fullName);
                int startPos = fullName.indexOf(filterQuery);
                spanString.setSpan(new ForegroundColorSpan(Color.RED), startPos, startPos + filterQuery.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                studentHolder.student.setText(spanString);
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
                    newStudents = StudentsCache.getInstance().getStudents();
                    Log.d("Ibrokhim Check This","Heyyooo");
                } else {
                    newStudents = new ArrayList<>();

                    for (Student student : StudentsCache.getInstance().getStudents()) {
                        if (student.getFullname().contains(filterQuery)) {
                            newStudents.add(student);
                        }
                    }
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
