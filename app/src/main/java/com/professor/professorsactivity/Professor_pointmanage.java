package com.professor.professorsactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cheng.example.truemission.R;
import com.db.UserDataManager;
import com.users.usersactivity.Pointmanage;
import com.users.usersactivity.Userstable;

public class Professor_pointmanage extends AppCompatActivity {
    private UserDataManager mUserDataManager;
    private static final String TAG = "Professor_Pointmanage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_pointmanage);
        TextView point=(TextView)findViewById(R.id.point);
        //TextView bug=(TextView)findViewById(R.id.point);
        SharedPreferences pref =getSharedPreferences("professorInfo",0);
        String username=pref.getString("PROFESSOR_NAME","");
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();                              //建立本地数据库
        }

        int professor_id=mUserDataManager.findIdByProfessorname(username);
        //bug.setText("BUG");
        try {
            int number=mUserDataManager.fetchQuestionDatasforProfessorid(professor_id);
            int userpoint=number*10;
            if(mUserDataManager.updateProfessorPoint(userpoint)){

             userpoint = mUserDataManager.findProfessorPoint(username);
            String s=Integer.toString(userpoint);
            point.setText(s);}
        }
        catch (Exception e){
//
            Log.e(TAG, "Error : ", e);
        }
    }

    public void turn_to_professorsstable(View view) {
        //setContentView(R.layout.login);
        Intent intent = new Intent(Professor_pointmanage.this,Professorstable.class) ;
        startActivity(intent);
        finish();

    }
    }

