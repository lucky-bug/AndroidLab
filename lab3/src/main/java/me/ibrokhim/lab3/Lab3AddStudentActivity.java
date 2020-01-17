package me.ibrokhim.lab3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class Lab3AddStudentActivity extends AppCompatActivity {
    private static final String EXTRA_STUDENT = "student";

    private EditText firstName;
    private EditText middleName;
    private EditText lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3_add_student);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstName = findViewById(R.id.first_name);
        middleName = findViewById(R.id.middle_name);
        lastName = findViewById(R.id.last_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lab3_add_student, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public static Student getResultStudent(@NonNull Intent intent) {
        return intent.getParcelableExtra(EXTRA_STUDENT);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        if (item.getItemId() == R.id.action_save) {
            // Create a better way of saving data
            Student student = new Student(
                    firstName.getText().toString(),
                    middleName.getText().toString(),
                    lastName.getText().toString()
            );

            Intent data = new Intent();
            data.putExtra(EXTRA_STUDENT, student);
            setResult(RESULT_OK, data);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
