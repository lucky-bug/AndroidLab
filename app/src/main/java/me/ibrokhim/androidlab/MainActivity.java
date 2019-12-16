package me.ibrokhim.androidlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import me.ibrokhim.lab1.Lab1Activity;
import me.ibrokhim.lab2.Lab2Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_lab1).setOnClickListener(v -> startActivity(Lab1Activity.newIntent(this)));
        findViewById(R.id.btn_lab2).setOnClickListener(v -> startActivity(Lab2Activity.newIntent(this)));
    }
}
