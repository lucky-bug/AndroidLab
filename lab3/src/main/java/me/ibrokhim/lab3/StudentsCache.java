package me.ibrokhim.lab3;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class StudentsCache {
    private static StudentsCache instance;

    private Set<Student> students = new LinkedHashSet<Student>();

    public static StudentsCache getInstance() {
        if (instance == null) {
            synchronized (StudentsCache.class) {
                instance = new StudentsCache();
            }
        }

        return instance;
    }

    private StudentsCache() {
    }

    @NonNull
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public void addStudent(@NonNull Student student) {
        students.add(student);
    }

    public boolean contains(@NonNull Student student) {
        return students.contains(student);
    }
}
