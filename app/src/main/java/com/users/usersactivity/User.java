package com.users.usersactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import com.cheng.example.truemission.Login;
import com.cheng.example.truemission.R;

public class User extends AppCompatActivity {
   // private Button mReturnButton;     这里可以不设置按钮，点击事件在layout文件中已实现

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
       // mReturnButton = (Button)findViewById(R.id.returnback);

    }
    public void back_to_login(View view) {
        //setContentView(R.layout.login);
        Intent intent3 = new Intent(User.this,Login.class) ;
        startActivity(intent3);
        finish();

    }
    public void turn_to_userstable(View view){
        Intent intent4 =new Intent(User.this,Userstable.class);
        startActivity(intent4);
        finish();
    }
}