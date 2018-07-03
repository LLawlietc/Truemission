package com.professor.professorsactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.adapter.Professor_QuestionAdapter;
import com.adapter.QuestionAdapter;
import com.cheng.example.truemission.R;
import com.db.QuestionData;
import com.db.UserDataManager;

import java.util.List;

public class Professor_seequestion extends AppCompatActivity implements AdapterView.OnItemClickListener,Professor_QuestionAdapter.Callback {
    private static final String TAG="Professor_seequestion";
    UserDataManager mUserDataManager;
    private ListView professor_lvquestion;
    SharedPreferences professorseequestion_sp;
     List<QuestionData> questiondata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_seequestion);
        professor_lvquestion=(ListView)findViewById(R.id.professor_lvquestions);
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();                              //建立本地数据库
        }
         questiondata=mUserDataManager.fetchAllQuestionDatas();

        Professor_QuestionAdapter questionAdapter = new Professor_QuestionAdapter(this, R.layout.professor_seequestion_item, questiondata,this);

        professor_lvquestion.setAdapter(questionAdapter);
        professor_lvquestion.setOnItemClickListener(this);
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
        Toast.makeText(Professor_seequestion.this,"按钮已点击",Toast.LENGTH_SHORT).show();
        professorseequestion_sp=getSharedPreferences("professorInfo",0);
        String professor_name=professorseequestion_sp.getString("PROFESSOR_NAME", "");
        int professor_id=mUserDataManager.findIdByProfessorname(professor_name);
        int question_id=questiondata.get((Integer) v.getTag()).getQuestionId();
        String question=questiondata.get((Integer) v.getTag()).getMcontext();
        Intent intent=new Intent(this,Professor_giveanswer.class);
        intent.putExtra("questionid",question_id+"");
        intent.putExtra("professorid",professor_id+"");
        intent.putExtra("question",question);
        startActivity(intent);
    }
}
