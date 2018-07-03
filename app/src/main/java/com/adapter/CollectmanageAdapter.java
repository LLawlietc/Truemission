package com.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.cheng.example.truemission.R;
import com.db.QuestionData;
import com.db.User_Question_Data;

import java.util.List;

/**
 * Created by cheng on 2018/4/25.
 */

public class CollectmanageAdapter extends ArrayAdapter<User_Question_Data> implements View.OnClickListener {
    private int resourceId;
    private static final String TAG="CollectmanageAdapter";
    private List<User_Question_Data> mContentList;
    private Context mContext;
    private CollectmanageAdapter.Callback mCallback;

    public interface Callback {
        void click(View v);
    }
    @Override
    public int getCount() {
        Log.i(TAG, "getCount");
        return mContentList.size();
    }

    @Override
    public User_Question_Data getItem(int position) {
        Log.i(TAG, "getItem");
        return mContentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.i(TAG, "getItemId");
        return position;
    }
    public CollectmanageAdapter(Context context, int textViewResourceId,
                           List<User_Question_Data> objects, CollectmanageAdapter.Callback callback) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.mContext=context;
        this.mContentList=objects;
        mCallback=callback;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        User_Question_Data user_question_data= getItem(position);
        View view;
        CollectmanageAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new CollectmanageAdapter.ViewHolder();
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            //viewHolder.question = (TextView) view.findViewById(R.id.question);
           // viewHolder.answer = (TextView) view.findViewById(R.id.answer);
            viewHolder.question_id=(TextView) view.findViewById(R.id.question_id);
            viewHolder.delete_collection=(Button) view.findViewById(R.id.delete_collection);
            view.setTag(viewHolder);

        } else {
            view=convertView;

            viewHolder = (CollectmanageAdapter.ViewHolder) view.getTag();
        }
        // viewHolder.attention.setOnClickListener(this);
        //viewHolder.collect.setOnClickListener(this);
        viewHolder.delete_collection.setOnClickListener(this);
        viewHolder.delete_collection.setTag(position);
        viewHolder.question_id.setText(user_question_data.getQuestion_collect_Id()+" ");
        //  Log.e(TAG,"233"+viewHolder.question.getText());
        //Log.e(TAG,"222333" + viewHolder.answer.getText());

        return view;
    }


    class ViewHolder {
        //TextView question;
        //TextView answer;
        TextView question_id;
        Button delete_collection;

    }
    @Override
    public void onClick(View v) {
        mCallback.click(v);
    }
}
