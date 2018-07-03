package com.professor.professorsactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cheng.example.truemission.Login;
import com.cheng.example.truemission.*;
import com.cheng.example.truemission.R;


public class Professor extends AppCompatActivity {
//这里可以不设置按钮，点击事件已经在layout中完成
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor);
    }
    public void back_to_professor_login(View view) {
        Intent intent3 = new Intent(Professor.this,Professor_Login.class) ;
        startActivity(intent3);
        finish();

    }
    public void turn_to_professorstable(View view){
        Intent intent4 =new Intent(Professor.this,Professorstable.class);
        startActivity(intent4);
        finish();
    }
}
