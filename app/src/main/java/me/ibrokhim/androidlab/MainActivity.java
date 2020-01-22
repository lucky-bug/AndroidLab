package me.ibrokhim.androidlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import me.ibrokhim.lab1.Lab1Activity;
import me.ibrokhim.lab2.Lab2Activity;
import me.ibrokhim.lab3.Lab3Activity;
import me.ibrokhim.lab4.Lab4Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_lab1).setOnClickListener(v -> startActivity(Lab1Activity.newIntent(this)));
        findViewById(R.id.btn_lab2).setOnClickListener(v -> startActivity(Lab2Activity.newIntent(this)));
        findViewById(R.id.btn_lab3).setOnClickListener(v -> startActivity(new Intent(this, Lab3Activity.class)));
        findViewById(R.id.btn_lab4).setOnClickListener(v -> startActivity(new Intent(this, Lab4Activity.class)));
    }
}
