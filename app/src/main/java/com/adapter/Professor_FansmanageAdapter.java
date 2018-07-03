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
import com.db.UserDataManager;
import com.db.User_Professor_Data;

import java.util.List;

/**
 * Created by cheng on 2018/4/27.
 */

public class Professor_FansmanageAdapter extends ArrayAdapter<User_Professor_Data> {
    private int resourceId;
    private static final String TAG="FansmanageAdapter";
    private List<User_Professor_Data> mContentList;
    private Context mContext;
    @Override
    public int getCount() {
        Log.i(TAG, "getCount");
        return mContentList.size();
    }

    @Override
    public User_Professor_Data getItem(int position) {
        Log.i(TAG, "getItem");
        return mContentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.i(TAG, "getItemId");
        return position;
    }
    public Professor_FansmanageAdapter(Context context, int textViewResourceId,
                                  List<User_Professor_Data> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.mContext=context;
        this.mContentList=objects;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        User_Professor_Data user_professor_data= getItem(position);
        View view;
        Professor_FansmanageAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new Professor_FansmanageAdapter.ViewHolder();
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            //viewHolder.question = (TextView) view.findViewById(R.id.question);
            // viewHolder.answer = (TextView) view.findViewById(R.id.answer);
            viewHolder.fans_id=(TextView) view.findViewById(R.id.fans_id);
            view.setTag(viewHolder);

        } else {
            view=convertView;

            viewHolder = (Professor_FansmanageAdapter.ViewHolder) view.getTag();
        }
        // viewHolder.attention.setOnClickListener(this);
        //viewHolder.collect.setOnClickListener(this);
        viewHolder.fans_id.setText(user_professor_data.getUser_concern_Id()+" ");
        //  Log.e(TAG,"233"+viewHolder.question.getText());
        //Log.e(TAG,"222333" + viewHolder.answer.getText());

        return view;
    }


    class ViewHolder {

        TextView fans_id;


    }
}
