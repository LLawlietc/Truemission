package com.db;

/**
 * Created by cheng on 2018/4/6.
 */

public class User_Professor_Data {
    private int up_Id;
    private int user_concern_Id;
    private int professor_concern_Id;
    public User_Professor_Data(int user_concern_Id,int professor_concern_Id){
        super();
        this.user_concern_Id=user_concern_Id;
        this.professor_concern_Id=professor_concern_Id;
    }
    public int getUp_Id(){
        return up_Id;
    }
    public void setUp_Id(int up_Id){
        this.up_Id=up_Id;

    }
    public int getUser_concern_Id(){
        return user_concern_Id;
    }
    public void setUser_concern_Id(int user_concern_Id){
        this.user_concern_Id=user_concern_Id;
    }
    public int getProfessor_concern_Id(){
        return professor_concern_Id;
    }
    public void setProfessor_concern_Id(int professor_concern_Id){
        this.professor_concern_Id=professor_concern_Id;
    }
}
