package com.db;

/**
 * Created by cheng on 2018/4/6.
 */

public class QuestionData {
    private int questionId;
    private int askerId;
    private String mcontext;
    private int professorId;
    private String answer;

    public QuestionData(int questionId,int askerId,String mcontext,int professorId,String answer){
        super();
        this.questionId=questionId;
        this.askerId=askerId;
        this.mcontext=mcontext;
        this.professorId=professorId;
        this.answer=answer;
    }

    public int getQuestionId(){
        return questionId;
    }
    public void setQuestionId(int questionId){
        this.questionId=questionId;
    }
    public int getAskerId(){
        return askerId;
    }
    public void setAskerId(int askerId){
        this.askerId=askerId;
    }
    public String getMcontext(){
        return mcontext;
    }
    public void setMcontext(String mcontext){
        this.mcontext=mcontext;
    }
    public int getProfessorId(){
        return professorId;
    }
    public void setProfessorId(int professorId){
        this.professorId=professorId;
    }
    public String getAnswer(){
        return answer;
    }
    public void setAnswer(String answer){
        this.answer=answer;
    }
    @Override
    public String toString(){return questionId+" "+askerId+" "+mcontext+" "+professorId+" "+answer;}
}
