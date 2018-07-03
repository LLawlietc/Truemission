package com.db;

/**
 * Created by cheng on 2018/4/3.
 */

public class ProfessorData {
    private int professorId;
    private String professorName;
    private String professorPwd;
    private int professorPoint=0;
    public int pwdresetFlag=0;
    public int getProfessorId(){
        return professorId;
    }
    public void setProfessorId(int professorId){
        this.professorId=professorId;
    }
    public String getProfessorName(){
        return professorName;
    }
    public void setProfessorName(String professorName){
        this.professorName=professorName;
    }
    public String getProfessorPwd(){
        return professorPwd;
    }
    public void setProfessorPwd(String professorPwd){
        this.professorPwd=professorPwd;
    }
    public int getProfessorPoint(){
        return professorPoint;
    }
    public void setProfessorPoint(int professorPoint){
        this.professorPoint=professorPoint;
    }
    public ProfessorData(String professorName,String professorPwd){
        this.professorName=professorName;
        this.professorPwd=professorPwd;

    }

}
