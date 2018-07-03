package com.users.usersactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.adapter.QuestionAdapter;
import com.cheng.example.truemission.Login;
import com.cheng.example.truemission.R;
import com.cheng.example.truemission.Register;
import com.db.QuestionData;
import com.db.UserDataManager;
import com.db.User_Professor_Data;
import com.db.User_Question_Data;

import java.util.ArrayList;
import java.util.List;



public class Seequestion extends AppCompatActivity implements AdapterView.OnItemClickListener,QuestionAdapter.Callback {
    private static final String TAG="Seequestion";
    UserDataManager mUserDataManager;
    private ListView lvquestion;
     List<QuestionData> questiondata;
    SharedPreferences seequestion_sp;
    QuestionAdapter  questionAdapter;
///////////////
//    private QuestionAdapter questionAdapter;
//////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seequestion);
        lvquestion=(ListView)findViewById(R.id.lvquestions);
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();                              //建立本地数据库
        }
         questiondata=mUserDataManager.fetchAllQuestionDatas();

        questionAdapter = new QuestionAdapter(this, R.layout.seequestion_item, questiondata,this);

        lvquestion.setAdapter(questionAdapter);
        lvquestion.setOnItemClickListener(this);



    }
    /**
     54      * 响应ListView中item的点击事件
     55      */
         @Override
         public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
                 Toast.makeText(this, "listview的item被点击了！，点击的位置是-->" + position,
                                 Toast.LENGTH_SHORT).show();
             }
    /**
     * 接口方法，响应ListView按钮点击事件
     */
    @Override
    public void click(View v) {
        switch(v.getId()) {
            case R.id.attention:
                Toast.makeText(Seequestion.this, "attention，位置是-->" + v.getTag()
                        + ",内容是-->" + questiondata.get((Integer) v.getTag()), Toast.LENGTH_SHORT).show();
                seequestion_sp = getSharedPreferences("userInfo", 0);
                String user_name=seequestion_sp.getString("USER_NAME", "");
                int user_concern_id=mUserDataManager.findIdByUsername(user_name);
                int professor_concern_id=questiondata.get((Integer) v.getTag()).getProfessorId();
                if(professor_concern_id==0){
                    Toast.makeText(Seequestion.this,getString(R.string.noprofessor),Toast.LENGTH_SHORT).show();
                }
                else{
                    User_Professor_Data user_professor_data=new User_Professor_Data(user_concern_id,professor_concern_id);
                    mUserDataManager.openDataBase();
                    long flag = mUserDataManager.insertUser_Professor_Data(user_professor_data); //新建收藏信息，插入SQLite数据库
                    if (flag == -1) {
                        Toast.makeText(this, getString(R.string.concern_fail),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, getString(R.string.concern_success),Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case R.id.collect:
                Toast.makeText(Seequestion.this,"collect",Toast.LENGTH_SHORT).show();
                seequestion_sp = getSharedPreferences("userInfo", 0);
                 String name=seequestion_sp.getString("USER_NAME", "");
                 int user_collect_id=mUserDataManager.findIdByUsername(name);
                int question_collect_id=questiondata.get((Integer) v.getTag()).getQuestionId();
                User_Question_Data user_question_data=new User_Question_Data(user_collect_id,question_collect_id);
                mUserDataManager.openDataBase();
                long flag = mUserDataManager.insertUser_Question_Data(user_question_data); //新建收藏信息，插入SQLite数据库
                if (flag == -1) {
                    Toast.makeText(this, getString(R.string.collect_fail),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, getString(R.string.collect_success),Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
