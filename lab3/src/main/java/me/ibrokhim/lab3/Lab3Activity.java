package me.ibrokhim.lab3;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import me.ibrokhim.lab3.adapter.StudentsAdapter;

public class Lab3Activity extends AppCompatActivity {
    private static final int REQUEST_STUDENT_ADD = 1;

    private RecyclerView list;
    private StudentsAdapter studentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = findViewById(android.R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        list.setLayoutManager(layoutManager);
        list.setAdapter(studentsAdapter = new StudentsAdapter());
        studentsAdapter.setStudents(StudentsCache.getInstance().getStudents());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Lab3Activity.this, Lab3AddStudentActivity.class), REQUEST_STUDENT_ADD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_STUDENT_ADD && resultCode == RESULT_OK) {
            Student student = Lab3AddStudentActivity.getResultStudent(data);

            StudentsCache.getInstance().addStudent(student);

            studentsAdapter.setStudents(StudentsCache.getInstance().getStudents());
            studentsAdapter.notifyItemRangeChanged(studentsAdapter.getItemCount() - 2, 2);
            list.scrollToPosition(studentsAdapter.getItemCount() - 1);
        }
    }
}
