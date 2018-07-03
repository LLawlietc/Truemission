package com.professor.professorsactivity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.adapter.AttentionmanageAdapter;
import com.adapter.Professor_FansmanageAdapter;
import com.cheng.example.truemission.R;
import com.db.UserDataManager;
import com.db.User_Professor_Data;

import java.util.List;

public class Professor_fansmanage extends AppCompatActivity {
    private static final String TAG="Professor_fansmanage";
    UserDataManager mUserDataManager;
    private ListView lvfans;
    List<User_Professor_Data> user_professor_datas;
    SharedPreferences fansmanage_sp;
    Professor_FansmanageAdapter professor_fansmanageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_fansmanage);
        lvfans=(ListView)findViewById(R.id.professor_lvfans);
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();                              //建立本地数据库
        }
        fansmanage_sp=getSharedPreferences("professorInfo", 0);
        String name=fansmanage_sp.getString("PROFESSOR_NAME", "");
        int professor_id=mUserDataManager.findIdByProfessorname(name);
        user_professor_datas=mUserDataManager.fetchALLFansData_professor(professor_id);
        professor_fansmanageAdapter=new Professor_FansmanageAdapter(this,R.layout.professor_fansmanage_item,user_professor_datas);
        lvfans.setAdapter(professor_fansmanageAdapter);
    }
}
