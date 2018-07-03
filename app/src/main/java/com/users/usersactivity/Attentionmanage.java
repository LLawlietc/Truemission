package com.users.usersactivity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.adapter.AttentionmanageAdapter;
import com.adapter.CollectmanageAdapter;
import com.cheng.example.truemission.R;
import com.db.UserDataManager;
import com.db.User_Professor_Data;
import com.db.User_Question_Data;

import java.util.List;

public class Attentionmanage extends AppCompatActivity implements AdapterView.OnItemClickListener,AttentionmanageAdapter.Callback {
    private static final String TAG="Attentionmanage";
    UserDataManager mUserDataManager;
    private ListView lvattentions;
    List<User_Professor_Data> user_professor_datas;
    SharedPreferences attentionmanage_sp;
    AttentionmanageAdapter attentionmanageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attentionmanage);
        lvattentions=(ListView)findViewById(R.id.lvattentions);
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();                              //建立本地数据库
        }
        attentionmanage_sp=getSharedPreferences("userInfo", 0);
        String user_name=attentionmanage_sp.getString("USER_NAME", "");
        int user_id=mUserDataManager.findIdByUsername(user_name);
        user_professor_datas=mUserDataManager.fetchAllAtentionData_user(user_id);
        attentionmanageAdapter=new AttentionmanageAdapter(this,R.layout.attentionmanage_item,user_professor_datas,this);
        lvattentions.setAdapter(attentionmanageAdapter);
        lvattentions.setOnItemClickListener(this);
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
        Toast.makeText(Attentionmanage.this, "attention，位置是-->" + v.getTag()
                + ",内容是-->" + user_professor_datas.get((Integer) v.getTag()), Toast.LENGTH_SHORT).show();
        mUserDataManager.openDataBase();
        int userid=user_professor_datas.get((Integer)v.getTag()).getUser_concern_Id();
        int professorid=user_professor_datas.get((Integer)v.getTag()).getProfessor_concern_Id();
        boolean flag=mUserDataManager.deleteUser_Professor_Data(userid,professorid);
        if(flag==false){
            Toast.makeText(Attentionmanage.this,"取消关注失败",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(Attentionmanage.this,"取消关注成功",Toast.LENGTH_SHORT).show();
        }
    }
}
