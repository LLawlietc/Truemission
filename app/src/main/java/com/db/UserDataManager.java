package com.db;

/**
 * Created by cheng on 2018/3/31.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UserDataManager {             //用户数据管理类
    //一些宏定义和声明
    private static final String TAG = "UserDataManager";
    private static final String DB_NAME = "database.db";
    private static final String TABLE_NAME_USERS = "users";
    public static final String ID_USER = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PWD = "user_pwd";
    public static final String USER_POINT ="user_point";
    private static final int DB_VERSION = 2;
    private Context mContext = null;

    private static final String TABLE_NAME_PROFESSORS = "professors";
    public static final String ID_PROFESSOR = "professor_id";
    public static final String PROFESSOR_NAME = "professor_name";
    public static final String PROFESSOR_PWD = "professor_pwd";
    public static final String PROFESSOR_POINT="professor_point";

    public static final String TABLE_NAME_QUESTION ="questions";
    public static final String QUESTION_ID ="question_id";
    public static final String ASKER_ID = "asker_id";
    public static final String CONTEXT ="context";
    public static final String PROFESSOR_ID ="professor_id";
    public static final String ANSWER ="answer";

    public static final String TABLE_NAME_USERS_QUESTIONS ="users_questions";
    public static final String UQ_ID ="uq_id";
    public static final String USER_COLLECT_ID ="user_collect_id";
    public static final String QUESTION_COLLECT_ID ="question_collect_id";

    public static final String TABLE_NAME_USERS_PROFESSORS ="users_professors";
    public static final String UP_ID ="up_id";
    public static final String USER_CONCERN_ID ="user_concern_id";
    public static final String PROFESSOR_CONCERN_ID ="professor_concern_id";

   // public static final String TABLE_NAME_PROFESSORS_USERS ="professors_users";
   // public static final String PU_ID ="pu_id";
    //public static final String PROFESSOR_FANS_ID ="professor_fans_id";
    //public static final String USER_FANS_ID ="user_fans_id";

    //创建用户表
    private static final String DB_CREATE_USER = "CREATE TABLE " + TABLE_NAME_USERS + " ("
            + ID_USER + " integer primary key autoincrement," + USER_NAME + " varchar,"
            + USER_PWD + " varchar," + USER_POINT+" integer"+");";


    //创建专家表
    private static final String DB_CREATE_PROFESSOR = "CREATE TABLE " + TABLE_NAME_PROFESSORS + " ("
            + ID_PROFESSOR + " integer primary key autoincrement," + PROFESSOR_NAME + " varchar,"
            + PROFESSOR_PWD + " varchar," +PROFESSOR_POINT+" integer"+ ");";

    //创建问题表
    private static final String DB_CREATE_QUESTION ="CREATE TABLE " + TABLE_NAME_QUESTION + " (" + QUESTION_ID
            + " integer primary key autoincrement," + ASKER_ID + " integer," + CONTEXT+" text," + PROFESSOR_ID + " integer,"
            + ANSWER + " text" + ");";

    //创建用户收藏问题关联表
    private static final String DB_CREATE_USERS_QUESTIONS ="CREATE TABLE " + TABLE_NAME_USERS_QUESTIONS +
            " (" + UQ_ID + " integer primary key autoincrement," + USER_COLLECT_ID + " integer," + QUESTION_COLLECT_ID +
            " integer" +");";

    //创建用户关注专家关联表
    private static final String DB_CREATE_USERS_PROFESSORS ="CREATE TABLE " + TABLE_NAME_USERS_PROFESSORS +
            " (" + UP_ID + " integer primary key autoincrement," + USER_CONCERN_ID + " integer," + PROFESSOR_CONCERN_ID +
            " integer" + ");";


    private SQLiteDatabase mSQLiteDatabase = null;
    private DataBaseManagementHelper mDatabaseHelper = null;

    //DataBaseManagementHelper继承自SQLiteOpenHelper
    private static class DataBaseManagementHelper extends SQLiteOpenHelper {  //内部类
       //内部类构造方法
        DataBaseManagementHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i(TAG,"db.getVersion()="+db.getVersion());
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USERS + ";");
            db.execSQL(DB_CREATE_USER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PROFESSORS + ";");
            db.execSQL(DB_CREATE_PROFESSOR);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_QUESTION + ";");
            db.execSQL(DB_CREATE_QUESTION);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USERS_QUESTIONS + ";");
            db.execSQL(DB_CREATE_USERS_QUESTIONS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USERS_PROFESSORS + ";");
            db.execSQL(DB_CREATE_USERS_PROFESSORS);
            Log.i(TAG, "db.execSQL(DB_CREATE_USER)");
            Log.e(TAG, DB_CREATE_USER);
            Log.i(TAG, "db.execSQL(DB_CREATE_PROFESSOR)");
            Log.e(TAG, DB_CREATE_PROFESSOR);
            Log.i(TAG, "db.execSQL(DB_CREATE_QUESTION)");
            Log.e(TAG, DB_CREATE_QUESTION);
            Log.i(TAG, "db.execSQL(DB_CREATE_USERS_QUESTIONS)");
            Log.e(TAG, DB_CREATE_USERS_QUESTIONS);
            Log.i(TAG, "db.execSQL(DB_CREATE_USERS_PROFESSORS)");
            Log.e(TAG, DB_CREATE_USERS_PROFESSORS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i(TAG, "DataBaseManagementHelper onUpgrade");
            onCreate(db);
        }
    }

    public UserDataManager(Context context) {
        mContext = context;
        Log.i(TAG, "UserDataManager construction!");
    }
    //打开数据库
    public void openDataBase() throws SQLException {
        mDatabaseHelper = new DataBaseManagementHelper(mContext);
        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
        //getWritableDatabase()创建或打开一个现有的数据库
    }
    //关闭数据库
    public void closeDataBase() throws SQLException {
        mDatabaseHelper.close();
    }

    //用户表操作



    //添加新用户，即注册
    public long insertUserData(UserData userData) {
        String userName=userData.getUserName();
        String userPwd=userData.getUserPwd();
        ContentValues values = new ContentValues();//对要添加的数据进行组装
        values.put(USER_NAME, userName);
        values.put(USER_PWD, userPwd);
        return mSQLiteDatabase.insert(TABLE_NAME_USERS, ID_USER, values);//插入数据
    }
    //更新用户信息，如修改密码
    public boolean updateUserData(UserData userData) {
        //int id = userData.getUserId();
        String userName = userData.getUserName();
        String userPwd = userData.getUserPwd();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userName);
        values.put(USER_PWD, userPwd);
        return mSQLiteDatabase.update(TABLE_NAME_USERS, values,null, null) > 0;
        //return mSQLiteDatabase.update(TABLE_NAME, values, ID + "=" + id, null) > 0;
    }

    //更新用户积分
    public boolean updateUserPoint(int userpoint){
        ContentValues values=new ContentValues();
        values.put(USER_POINT,userpoint);
        return mSQLiteDatabase.update(TABLE_NAME_USERS,values,null,null)>0;
    }






    //
    public Cursor fetchUserData(int id) throws SQLException {
        Cursor mCursor = mSQLiteDatabase.query(false, TABLE_NAME_USERS, null, ID_USER
                + "=" + id, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //查询所有用户
    public Cursor fetchAllUserDatas() {
        return mSQLiteDatabase.query(TABLE_NAME_USERS, null, null, null, null, null,
                null);
    }
    //根据id删除用户
    public boolean deleteUserData(int id) {
        return mSQLiteDatabase.delete(TABLE_NAME_USERS, ID_USER + "=" + id, null) > 0;
    }
    //根据用户名注销
    public boolean deleteUserDatabyname(String name) {
        return mSQLiteDatabase.delete(TABLE_NAME_USERS, USER_NAME + "=" + name, null) > 0;
    }
    //删除所有用户
    public boolean deleteAllUserDatas() {
        return mSQLiteDatabase.delete(TABLE_NAME_USERS, null, null) > 0;
    }

    //
    public String getStringByColumnNameUser(String columnName, int id) {
        Cursor mCursor = fetchUserData(id);
        int columnIndex = mCursor.getColumnIndex(columnName);//返回某列名对应的列索引值
        String columnValue = mCursor.getString(columnIndex);//返回当前行指定列的值
        mCursor.close();
        return columnValue;
    }
    //根据id更新用户对应列
    public boolean updateUserDataById(String columnName, int id,
                                      String columnValue) {
        ContentValues values = new ContentValues();
        values.put(columnName, columnValue);
        return mSQLiteDatabase.update(TABLE_NAME_USERS, values, ID_USER + "=" + id, null) > 0;
    }
    //根据用户名找用户，可以判断注册时用户名是否已经存在
    public int findUserByName(String userName){
        Log.i(TAG,"findUserByName , userName="+userName);
        int result=0;
        Cursor mCursor=mSQLiteDatabase.query(TABLE_NAME_USERS, null, USER_NAME+"="+userName, null, null, null, null);//SQLite查询
        if(mCursor!=null){
            result=mCursor.getCount();//返回个数
            mCursor.close();
            Log.i(TAG,"findUserByName , result="+result);
        }
        return result;
    }
    //根据用户名和密码找用户，用于登录
    public int findUserByNameAndPwd(String userName,String pwd){
        Log.i(TAG,"findUserByNameAndPwd");
        int result=0;
        Cursor mCursor=mSQLiteDatabase.query(TABLE_NAME_USERS, null, USER_NAME+"="+userName+" and "+USER_PWD+"="+pwd,
                null, null, null, null);
        if(mCursor!=null){
            result=mCursor.getCount();
            mCursor.close();
            Log.i(TAG,"findUserByNameAndPwd , result="+result);
        }
        return result;
    }
    public int findUserPoint(String userName){
        Cursor mCursor=mSQLiteDatabase.query(TABLE_NAME_USERS,null,USER_NAME+"="+userName,null,null,null,null);
        Log.e(TAG,"bug here");
        int point=0;
        if (mCursor.moveToNext())
            point=mCursor.getInt(mCursor.getColumnIndex("user_point"));
        mCursor.close();
        return point;
    }
    //根据用户名返回id
    public int findIdByUsername(String userName){
        Log.i(TAG,"findIdByUsername");
        Cursor mCursor=mSQLiteDatabase.query(TABLE_NAME_USERS,null,USER_NAME+"="+userName,null,null,null,null);
        int id=0;
        if (mCursor.moveToNext())
            id=mCursor.getInt(mCursor.getColumnIndex("user_id"));
        mCursor.close();
        return id;
    }


    //专家表操作



    //添加新用户，即注册
    public long insertProfessorData(ProfessorData professorData) {
        String professorName=professorData.getProfessorName();
        String professorPwd=professorData.getProfessorPwd();
        ContentValues values = new ContentValues();//对要添加的数据进行组装
        values.put(PROFESSOR_NAME, professorName);
        values.put(PROFESSOR_PWD, professorPwd);
        return mSQLiteDatabase.insert(TABLE_NAME_PROFESSORS, ID_PROFESSOR, values);
        //插入数据（第二个参数是用于在未指定添加数据的情况下给某些可为空的列自动赋值null）

    }
    //更新用户信息，如修改密码
    public boolean updateProfessorData(ProfessorData professorData) {
        int id = professorData.getProfessorId();
        String professorName = professorData.getProfessorName();
        String professorPwd = professorData.getProfessorPwd();
        ContentValues values = new ContentValues();
        values.put(PROFESSOR_NAME, professorName);
        values.put(PROFESSOR_PWD, professorPwd);
        return mSQLiteDatabase.update(TABLE_NAME_PROFESSORS, values,null, null) > 0;
        //return mSQLiteDatabase.update(TABLE_NAME, values, ID + "=" + id, null) > 0;
    }
    //更新专家积分
    public boolean updateProfessorPoint(int professorpoint){
        ContentValues values=new ContentValues();
        values.put(PROFESSOR_POINT,professorpoint);
        return mSQLiteDatabase.update(TABLE_NAME_PROFESSORS,values,null,null)>0;
    }
    //查询
    public Cursor fetchProfessorData(int id) throws SQLException {
        Cursor mCursor = mSQLiteDatabase.query(false, TABLE_NAME_PROFESSORS, null, ID_PROFESSOR
                + "=" + id, null, null, null, null, null);//查询语句
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //查询所有用户
    public Cursor fetchAllProfessorDatas() {
        return mSQLiteDatabase.query(TABLE_NAME_PROFESSORS, null, null, null, null, null,
                null);
    }
    //根据id删除用户
    public boolean deleteProfessorData(int id) {
        return mSQLiteDatabase.delete(TABLE_NAME_PROFESSORS, ID_PROFESSOR + "=" + id, null) > 0;
    }
    //根据用户名注销
    public boolean deleteProfessorDatabyname(String name) {
        return mSQLiteDatabase.delete(TABLE_NAME_PROFESSORS, PROFESSOR_NAME + "=" + name, null) > 0;
    }
    //删除所有用户
    public boolean deleteAllProfessorDatas() {
        return mSQLiteDatabase.delete(TABLE_NAME_PROFESSORS, null, null) > 0;
    }

    //
    public String getStringByColumnNameProfessor(String columnName, int id) {
        Cursor mCursor = fetchProfessorData(id);
        int columnIndex = mCursor.getColumnIndex(columnName);//返回某列名对应的列索引值
        String columnValue = mCursor.getString(columnIndex);//返回当前行指定列的值
        mCursor.close();
        return columnValue;
    }
    //根据id更新用户对应列
    public boolean updateProfessorDataById(String columnName, int id,
                                           String columnValue) {
        ContentValues values = new ContentValues();
        values.put(columnName, columnValue);
        return mSQLiteDatabase.update(TABLE_NAME_PROFESSORS, values, ID_PROFESSOR + "=" + id, null) > 0;
    }
    //根据用户名找用户，可以判断注册时用户名是否已经存在
    public int findProfessorByName(String professorName){
        Log.i(TAG,"findProfessorByName , professorName="+professorName);
        int result=0;
        Cursor mCursor=mSQLiteDatabase.query(TABLE_NAME_PROFESSORS, null, PROFESSOR_NAME+"="+professorName, null, null, null, null);//SQLite查询
        if(mCursor!=null){
            result=mCursor.getCount();//返回个数
            mCursor.close();
            Log.i(TAG,"findProfessorByName , result="+result);
        }
        return result;
    }
    //根据用户名和密码找用户，用于登录
    public int findProfessorByNameAndPwd(String professorName,String pwd){
        Log.i(TAG,"findProfessorByNameAndPwd");
        int result=0;
        Cursor mCursor=mSQLiteDatabase.query(TABLE_NAME_PROFESSORS, null, PROFESSOR_NAME+"="+professorName+" and "+PROFESSOR_PWD+"="+pwd,
                null, null, null, null);
        if(mCursor!=null){
            result=mCursor.getCount();
            mCursor.close();
            Log.i(TAG,"findProfessorByNameAndPwd , result="+result);
        }
        return result;
    }
    public int findProfessorPoint(String professorName){
        Cursor mCursor=mSQLiteDatabase.query(TABLE_NAME_PROFESSORS,null,PROFESSOR_NAME+"="+professorName,null,null,null,null);
        Log.e(TAG,"bug here");
        int point=0;
        if (mCursor.moveToNext())
            point=mCursor.getInt(mCursor.getColumnIndex("professor_point"));
        mCursor.close();
        return point;
    }
    //根据专家名返回id
    public int findIdByProfessorname(String professorName){
        Log.i(TAG,"findIdByProfessorname");
        Cursor mCursor=mSQLiteDatabase.query(TABLE_NAME_PROFESSORS,null,PROFESSOR_NAME+"="+professorName,null,null,null,null);
        int id=0;
        if (mCursor.moveToNext())
            id=mCursor.getInt(mCursor.getColumnIndex("professor_id"));
        mCursor.close();
        return id;
    }




    //问题表操作


    //添加新问题
    public long insertQuestionData(int asker_id,String mcontext,int professor_id,String answer) {
       // int asker_id=questionData.getAskerId();
       // String mcontext=questionData.getMcontext();
       // int professor_id=questionData.getProfessorId();
       // String answer  =questionData.getAnswer();
        ContentValues values = new ContentValues();//对要添加的数据进行组装
        values.put(ASKER_ID, asker_id);
        values.put(CONTEXT, mcontext);
        values.put(PROFESSOR_ID,professor_id);
        values.put(ANSWER,answer);
        return mSQLiteDatabase.insert(TABLE_NAME_QUESTION, QUESTION_ID, values);//插入数据
    }
    //更新问题信息
    public boolean updateQuestionData(QuestionData questionData) {
        //int id = userData.getUserId();
        int asker_id=questionData.getAskerId();
        String mcontext=questionData.getMcontext();
        int professor_id=questionData.getProfessorId();
        String answer  =questionData.getAnswer();
        ContentValues values = new ContentValues();//对要添加的数据进行组装
        values.put(ASKER_ID, asker_id);
        values.put(CONTEXT, mcontext);
        values.put(PROFESSOR_ID,professor_id);
        values.put(ANSWER,answer);
        return mSQLiteDatabase.update(TABLE_NAME_QUESTION, values,null, null) > 0;
        //return mSQLiteDatabase.update(TABLE_NAME, values, ID + "=" + id, null) > 0;
    }
    //根据question_id 查问题
    public Cursor fetchQuestionData(int question_id) throws SQLException {
       // Cursor 是每行的集合。使用 moveToFirst() 定位第一行。
        // 你必须知道每一列的名称。你必须知道每一列的数据类型。
        // Cursor 是一个随机的数据源。所有的数据都是通过下标取得。
        Cursor mCursor = mSQLiteDatabase.query(false, TABLE_NAME_QUESTION, null, QUESTION_ID
                + "=" + question_id, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //查询所有问题
    public List<QuestionData> fetchAllQuestionDatas() {
        List<QuestionData> questiondata=new ArrayList<QuestionData>();
        Cursor cursor=mSQLiteDatabase.query(TABLE_NAME_QUESTION, null, null, null, null, null,
                null);
        Log.d(TAG,"bug here");
        while(cursor.moveToNext()){
            int questionid= cursor.getInt(cursor.getColumnIndex("question_id"));
            int askerid=cursor.getInt(cursor.getColumnIndex("asker_id"));

            String context=cursor.getString(cursor.getColumnIndex("context"));

            String answer=cursor.getString(cursor.getColumnIndex("answer"));

            int professorid=cursor.getInt(cursor.getColumnIndex("professor_id"));
            QuestionData q=new QuestionData(questionid,askerid,context,professorid,answer);
            questiondata.add(q);
        }
        return questiondata;

    }
    //查询对应userid的所有问题的个数
    public int fetchQuestionDatasforAskerid(int asker_id){
        int count=0;
        Cursor cursor=mSQLiteDatabase.query(TABLE_NAME_QUESTION,null,ASKER_ID + "=" + asker_id,null,null,null,null);
        if(cursor!=null){
             count=cursor.getCount();
        }
        return count;
    }

    //查询对应professorid的所有问题的个数
    public int fetchQuestionDatasforProfessorid(int professor_id){
        int count=0;
        Cursor cursor=mSQLiteDatabase.query(TABLE_NAME_QUESTION,null,PROFESSOR_ID + "=" + professor_id,null,null,null,null);
        if(cursor!=null){
            count=cursor.getCount();
        }
        return count;
    }

    //根据id删除问题
    public boolean deleteQuestionData(int question_id) {
        return mSQLiteDatabase.delete(TABLE_NAME_QUESTION, QUESTION_ID + "=" + question_id, null) > 0;
    }
    //根据asker_id注销
    public boolean deleteQuestionDatabyAskerid(int asker_id) {
        return mSQLiteDatabase.delete(TABLE_NAME_QUESTION, ASKER_ID + "=" + asker_id, null) > 0;
    }
    //删除所有用户
    public boolean deleteAllQuestionDatas() {
        return mSQLiteDatabase.delete(TABLE_NAME_QUESTION, null, null) > 0;
    }

    //
   /* public String getStringByColumnNameUser(String columnName, int id) {
        Cursor mCursor = fetchUserData(id);
        int columnIndex = mCursor.getColumnIndex(columnName);//返回某列名对应的列索引值
        String columnValue = mCursor.getString(columnIndex);//返回当前行指定列的值
        mCursor.close();
        return columnValue;
    }*/
    //根据id更新问题对应列
    public boolean updateQuestionDataById1(String columnName, int question_id,
                                      String columnValue) {
        ContentValues values = new ContentValues();
        values.put(columnName, columnValue);
        return mSQLiteDatabase.update(TABLE_NAME_QUESTION, values, QUESTION_ID + "=" + question_id, null) > 0;
    }
    public boolean updateQuestionDataById2(String columnName, int question_id,
                                           int columnValue) {
        ContentValues values = new ContentValues();
        values.put(columnName, columnValue);
        return mSQLiteDatabase.update(TABLE_NAME_QUESTION, values, QUESTION_ID + "=" + question_id, null) > 0;
    }
    //根据用户名找用户，可以判断注册时用户名是否已经存在
   /* public int findUserByName(String userName){
        Log.i(TAG,"findUserByName , userName="+userName);
        int result=0;
        Cursor mCursor=mSQLiteDatabase.query(TABLE_NAME_USERS, null, USER_NAME+"="+userName, null, null, null, null);//SQLite查询
        if(mCursor!=null){
            result=mCursor.getCount();//返回个数
            mCursor.close();
            Log.i(TAG,"findUserByName , result="+result);
        }
        return result;
    }*/
    //根据用户名和密码找用户，用于登录
   /* public int findUserByNameAndPwd(String userName,String pwd){
        Log.i(TAG,"findUserByNameAndPwd");
        int result=0;
        Cursor mCursor=mSQLiteDatabase.query(TABLE_NAME_USERS, null, USER_NAME+"="+userName+" and "+USER_PWD+"="+pwd,
                null, null, null, null);
        if(mCursor!=null){
            result=mCursor.getCount();
            mCursor.close();
            Log.i(TAG,"findUserByNameAndPwd , result="+result);
        }
        return result;
    }*/


    //用户收藏表操作

    //添加新收藏
    public long insertUser_Question_Data(User_Question_Data user_question_data) {
        int user_collect_id=user_question_data.getUser_collect_Id();
        int question_collect_id=user_question_data.getQuestion_collect_Id();
        ContentValues values = new ContentValues();//对要添加的数据进行组装
        values.put(USER_COLLECT_ID, user_collect_id);
        values.put(QUESTION_COLLECT_ID, question_collect_id);
        return mSQLiteDatabase.insert(TABLE_NAME_USERS_QUESTIONS, null, values);//插入数据
    }
    //删除收藏
    public boolean deleteUser_Question_Data(int userid,int questionid){
        return mSQLiteDatabase.delete(TABLE_NAME_USERS_QUESTIONS,USER_COLLECT_ID + "=" + userid +" and " + QUESTION_COLLECT_ID + "="+questionid ,null)>0;
    }
    //查找所有收藏
    public List<User_Question_Data> fetchAllCollectionDatas(int user_id) {
        List<User_Question_Data> user_question_datas=new ArrayList<User_Question_Data>();
        Cursor cursor=mSQLiteDatabase.query(TABLE_NAME_USERS_QUESTIONS, null, USER_COLLECT_ID + "=" + user_id, null, null, null,
                null);
        Log.d(TAG,"bug here2");
        while(cursor.moveToNext()){
            int questionid= cursor.getInt(cursor.getColumnIndex("question_collect_id"));
            int userid=cursor.getInt(cursor.getColumnIndex("user_collect_id"));
            User_Question_Data q=new User_Question_Data(userid,questionid);
           user_question_datas.add(q);
        }
        return user_question_datas;

    }


    //用户专家关联表操作

    //添加新关注
    public long insertUser_Professor_Data(User_Professor_Data user_professor_data){
        int user_concern_id=user_professor_data.getUser_concern_Id();
        int professor_concern_id=user_professor_data.getProfessor_concern_Id();
        ContentValues values = new ContentValues();
        values.put(USER_CONCERN_ID,user_concern_id);
        values.put(PROFESSOR_CONCERN_ID,professor_concern_id);
        return mSQLiteDatabase.insert(TABLE_NAME_USERS_PROFESSORS,null,values);
    }
    //取消关注
    public boolean deleteUser_Professor_Data(int userid,int professorid){
        return mSQLiteDatabase.delete(TABLE_NAME_USERS_PROFESSORS,USER_CONCERN_ID + "=" + userid + "and" + PROFESSOR_CONCERN_ID + "=" + professorid,null)>0;
    }
    //根据userid查找所有关注
    public List<User_Professor_Data> fetchAllAtentionData_user(int userid){
        List<User_Professor_Data> user_professor_datas=new ArrayList<User_Professor_Data>();
        Cursor cursor=mSQLiteDatabase.query(TABLE_NAME_USERS_PROFESSORS,null,USER_CONCERN_ID + "=" + userid,null,null,null,null);
        Log.d(TAG,"bug here3");
        while(cursor.moveToNext()){
            int professor_id= cursor.getInt(cursor.getColumnIndex("professor_concern_id"));
            int user_id=cursor.getInt(cursor.getColumnIndex("user_concern_id"));
            User_Professor_Data q=new User_Professor_Data(user_id,professor_id);
            user_professor_datas.add(q);
        }
        return user_professor_datas;
    }
    //根据professorid查找所有粉丝
    public List<User_Professor_Data> fetchALLFansData_professor(int professorid){
        List<User_Professor_Data> user_professor_datas=new ArrayList<User_Professor_Data>();
        Cursor cursor=mSQLiteDatabase.query(TABLE_NAME_USERS_PROFESSORS,null,PROFESSOR_CONCERN_ID + "=" + professorid,null,null,null,null);
        Log.d(TAG,"bug here4");
        while(cursor.moveToNext()){
            int professor_id= cursor.getInt(cursor.getColumnIndex("professor_concern_id"));
            int user_id=cursor.getInt(cursor.getColumnIndex("user_concern_id"));
            User_Professor_Data q=new User_Professor_Data(user_id,professor_id);
            user_professor_datas.add(q);
        }
        return user_professor_datas;
    }
}

