package com.cheng.example.truemission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.db.UserDataManager;
import com.db.ProfessorData;

public class Professor_Register extends AppCompatActivity {
    private EditText mAccount;                        //用户名编辑
    private EditText mPwd;                            //密码编辑
    private EditText mPwdCheck;                       //密码编辑
    private Button mSureButton;                       //确定按钮
    private Button mCancelButton;                     //取消按钮
    private UserDataManager mProfessorDataManager;         //专家数据管理类
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor__register);
        mAccount = (EditText) findViewById(R.id.resetpwd_edit_name);
        mPwd = (EditText) findViewById(R.id.resetpwd_edit_pwd_old);
        mPwdCheck = (EditText) findViewById(R.id.resetpwd_edit_pwd_new);

        mSureButton = (Button) findViewById(R.id.register_btn_sure);
        mCancelButton = (Button) findViewById(R.id.register_btn_cancel);

        mSureButton.setOnClickListener(m_register_Listener);      //注册界面两个按钮的监听事件
        mCancelButton.setOnClickListener(m_register_Listener);

        if (mProfessorDataManager == null) {
            mProfessorDataManager = new UserDataManager(this);
            mProfessorDataManager.openDataBase();                              //建立本地数据库
        }

    }
    View.OnClickListener m_register_Listener = new View.OnClickListener() {    //不同按钮按下的监听事件选择
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.register_btn_sure:                       //确认按钮的监听事件
                    professor_register_check();
                    break;
                case R.id.register_btn_cancel:                     //取消按钮的监听事件,由注册界面返回登录界面
                    Intent intent_Professor_Register_to_Professor_Login = new Intent(Professor_Register.this,Professor_Login.class) ;    //切换User Activity至Login Activity
                    startActivity(intent_Professor_Register_to_Professor_Login);
                    finish();
                    break;
            }
        }
    };
    public void professor_register_check() {                                //确认按钮的监听事件
        if (isProfessorNameAndPwdValid()) {
            String professorName = mAccount.getText().toString().trim();
            String professorPwd = mPwd.getText().toString().trim();
            String professorPwdCheck = mPwdCheck.getText().toString().trim();
            //检查用户是否存在
            int count=mProfessorDataManager.findProfessorByName(professorName);
            //用户已经存在时返回，给出提示文字
            if(count>0){
                Toast.makeText(this, getString(R.string.name_already_exist, professorName),Toast.LENGTH_SHORT).show();
                return ;
            }
            if(professorPwd.equals(professorPwdCheck)==false){     //两次密码输入不一样
                Toast.makeText(this, getString(R.string.pwd_not_the_same),Toast.LENGTH_SHORT).show();
                return ;
            } else {                                //没有问题则注册

                ProfessorData mProfessor = new ProfessorData(professorName, professorPwd);
                mProfessorDataManager.openDataBase();
                long flag = mProfessorDataManager.insertProfessorData(mProfessor); //新建用户信息，插入SQLite数据库
                if (flag == -1) {
                    Toast.makeText(this, getString(R.string.register_fail),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, getString(R.string.register_success),Toast.LENGTH_SHORT).show();
                    Intent intent_Professor_Register_to_Professor_Login = new Intent(Professor_Register.this,Professor_Login.class) ;    //切换User Activity至Login Activity
                    startActivity(intent_Professor_Register_to_Professor_Login);
                    finish();
                }
            }
        }
    }
    public boolean isProfessorNameAndPwdValid() {            //判断username和pwd是否为空
        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.account_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        }else if(mPwdCheck.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_check_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
