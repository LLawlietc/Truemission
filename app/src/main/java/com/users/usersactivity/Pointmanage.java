package com.users.usersactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cheng.example.truemission.R;
import com.db.UserDataManager;

public class Pointmanage extends AppCompatActivity {
    private UserDataManager mUserDataManager;
    private static final String TAG = "Pointmanage";
    private SharedPreferences user_point_sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pointmanage);
        TextView point=(TextView)findViewById(R.id.point);
        //TextView bug=(TextView)findViewById(R.id.point);
       // SharedPreferences pref =getSharedPreferences("userInfo",0);
        //String username=pref.getString("USER_NAME","");



        //得到asker_id
        user_point_sp=getSharedPreferences("userInfo", 0);
        String name=user_point_sp.getString("USER_NAME", "");
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();                              //建立本地数据库
        }
        int asker_id=mUserDataManager.findIdByUsername(name);
        //bug.setText("BUG");



        try {
            int number=mUserDataManager.fetchQuestionDatasforAskerid(asker_id);
            int userpoint=number*10;
            if(mUserDataManager.updateUserPoint(userpoint)){
             userpoint = mUserDataManager.findUserPoint(name);
            String s=Integer.toString(userpoint);
                Integer d=userpoint;
                String e=d.toString();
            point.setText(s);}
        }
        catch (Exception e){
//
            Log.e(TAG, "Error : ", e);
        }
 }

    public void turn_to_userstable(View view) {
        //setContentView(R.layout.login);
        Intent intent = new Intent(Pointmanage.this,Userstable.class) ;
        startActivity(intent);
        finish();

    }

}
