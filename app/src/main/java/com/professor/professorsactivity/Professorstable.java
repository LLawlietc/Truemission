package com.professor.professorsactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cheng.example.truemission.R;

public class Professorstable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professorstable);
    }
    public void turn_to_professor_seequestion(View view) {
        //setContentView(R.layout.login);
        Intent intent = new Intent(Professorstable.this,Professor_seequestion.class) ;
        startActivity(intent);
        finish();

    }
    public void turn_to_professor_fansmanage(View view) {
        //setContentView(R.layout.login);
        Intent intent = new Intent(Professorstable.this,Professor_fansmanage.class) ;
        startActivity(intent);
        finish();

    }
    public void turn_to_professor_pointmanage(View view) {
        //setContentView(R.layout.login);
        Intent intent = new Intent(Professorstable.this,Professor_pointmanage.class) ;
        startActivity(intent);
        finish();

    }
}
