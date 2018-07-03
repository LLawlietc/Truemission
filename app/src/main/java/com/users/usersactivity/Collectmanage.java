package com.users.usersactivity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.adapter.CollectmanageAdapter;
import com.adapter.QuestionAdapter;
import com.cheng.example.truemission.R;
import com.db.QuestionData;
import com.db.UserDataManager;
import com.db.User_Question_Data;

import java.util.List;

public class Collectmanage extends AppCompatActivity implements AdapterView.OnItemClickListener,CollectmanageAdapter.Callback {
    private static final String TAG="Collectmanage";
    UserDataManager mUserDataManager;
    private ListView lvcollections;
    List<User_Question_Data> user_question_datas;
    SharedPreferences collectmanage_sp;
    CollectmanageAdapter collectmanageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collectmanage);
        lvcollections=(ListView)findViewById(R.id.lvcollections);
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();                              //建立本地数据库
        }
        collectmanage_sp = getSharedPreferences("userInfo", 0);
        String user_name=collectmanage_sp.getString("USER_NAME", "");
        int user_id=mUserDataManager.findIdByUsername(user_name);
        user_question_datas=mUserDataManager.fetchAllCollectionDatas(user_id);
        collectmanageAdapter=new CollectmanageAdapter(this,R.layout.collectmanage_item,user_question_datas,this);

        lvcollections.setAdapter(collectmanageAdapter);
        lvcollections.setOnItemClickListener(this);
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
        Toast.makeText(Collectmanage.this, "attention，位置是-->" + v.getTag()
                + ",内容是-->" + user_question_datas.get((Integer) v.getTag()),
                Toast.LENGTH_SHORT).show();
        mUserDataManager.openDataBase();
        int userid=user_question_datas.get((Integer)v.getTag()).getUser_collect_Id();
        int questionid=user_question_datas.get((Integer)v.getTag()).getQuestion_collect_Id();
        boolean flag=mUserDataManager.deleteUser_Question_Data(userid,questionid);
        if(flag==false){
            Toast.makeText(Collectmanage.this,"删除失败",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(Collectmanage.this,"删除成功",Toast.LENGTH_SHORT).show();
        }
    }
}
