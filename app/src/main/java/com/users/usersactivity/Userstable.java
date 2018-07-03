package com.users.usersactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cheng.example.truemission.Login;
import com.cheng.example.truemission.R;

public class Userstable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userstable);
    }
    public void turn_to_seequestion(View view) {
        //setContentView(R.layout.login);
        Intent intent = new Intent(Userstable.this,Seequestion.class) ;
        startActivity(intent);
        finish();

    }

    public void turn_to_askquestion(View view) {
        //setContentView(R.layout.login);
        Intent intent = new Intent(Userstable.this,Askquestion.class) ;
        startActivity(intent);
        finish();

    }

    public void turn_to_pointmanage(View view) {
        //setContentView(R.layout.login);
        Intent intent = new Intent(Userstable.this,Pointmanage.class) ;
        startActivity(intent);
        finish();

    }
    public void turn_to_collectmanage(View view) {
        //setContentView(R.layout.login);
        Intent intent = new Intent(Userstable.this,Collectmanage.class) ;
        startActivity(intent);
        finish();

    }
    public void turn_to_attentionmanage(View view) {
        //setContentView(R.layout.login);
        Intent intent = new Intent(Userstable.this,Attentionmanage.class) ;
        startActivity(intent);
        finish();

    }

}
