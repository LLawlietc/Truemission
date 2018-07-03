package com.db;

/**
 * Created by cheng on 2018/4/6.
 */

public class User_Question_Data {
    private int uq_Id;
    private int user_collect_Id;
    private int question_collect_Id;
    public User_Question_Data(int user_collect_Id,int question_collect_Id){
        super();
        this.user_collect_Id=user_collect_Id;
        this.question_collect_Id=question_collect_Id;
    }
    public int getUq_Id(){
        return uq_Id;
    }
    public void setUq_Id(int uq_Id){
        this.uq_Id=uq_Id;
    }
    public int getUser_collect_Id(){
        return user_collect_Id;
    }
    public void setUser_collect_Id(int user_collect_Id){
        this.user_collect_Id=user_collect_Id;
    }
    public int getQuestion_collect_Id(){
        return question_collect_Id;
    }
    public void setQuestion_collect_Id(int question_collect_Id){
        this.question_collect_Id=question_collect_Id;
    }
}
