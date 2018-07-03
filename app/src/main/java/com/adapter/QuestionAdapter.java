package com.adapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.cheng.example.truemission.R;
import com.db.QuestionData;
import com.db.UserDataManager;
import com.db.User_Question_Data;

import java.util.List;

/**
 * Created by cheng on 2018/4/9.
 */

public class QuestionAdapter extends ArrayAdapter<QuestionData> implements View.OnClickListener {
    private int resourceId;
    private static final String TAG="QuestionAdapter";
    private List<QuestionData> mContentList;
    private Context mContext;

    private Callback mCallback;
    public interface Callback {
                  void click(View v);
             }
    @Override
         public int getCount() {
                 Log.i(TAG, "getCount");
                 return mContentList.size();
    }

                 @Override
         public QuestionData getItem(int position) {
                 Log.i(TAG, "getItem");
                 return mContentList.get(position);
             }

                 @Override
         public long getItemId(int position) {
                 Log.i(TAG, "getItemId");
                 return position;
             }

    /**
     25      * 自定义接口，用于回调按钮点击事件到Activity
     26      * @author chengsiwei
     27      * 2018-4-9
     28      */

   // public QuestionAdapter( List<QuestionData> mcontentList,
                                      // Context mContext) {
               // this.mContentList = mcontentList;
              // this.mContext=mContext;
          //  }
    public QuestionAdapter(Context context, int textViewResourceId,
                         List<QuestionData> objects, Callback callback) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
       this.mContext=context;
        this.mContentList=objects;
        mCallback=callback;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        QuestionData questiondata= getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder.question = (TextView) view.findViewById(R.id.question);
            viewHolder.answer = (TextView) view.findViewById(R.id.answer);
            viewHolder.question_id=(TextView) view.findViewById(R.id.question_id);
            viewHolder.attention=(Button) view.findViewById(R.id.attention);
            viewHolder.collect =(Button) view.findViewById(R.id.collect);
            view.setTag(viewHolder);
        } else {
            view=convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.attention.setOnClickListener(this);
        viewHolder.collect.setOnClickListener(this);
        viewHolder.attention.setTag(position);
        viewHolder.collect.setTag(position);
        viewHolder.question.setText(questiondata.getMcontext());
        viewHolder.question_id.setText(Integer.toString(questiondata.getQuestionId()));
        viewHolder.answer.setText(questiondata.getAnswer());
        return view;
    }


    class ViewHolder {
        TextView question;
        TextView answer;
        TextView question_id;
        Button attention;
        Button collect;

    }

    @Override
         public void onClick(View v) {
                mCallback.click(v);
             }

}

