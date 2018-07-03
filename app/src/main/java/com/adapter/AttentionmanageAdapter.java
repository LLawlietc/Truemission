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
import com.db.User_Professor_Data;
import java.util.List;

/**
 * Created by cheng on 2018/4/25.
 */

public class AttentionmanageAdapter extends ArrayAdapter<User_Professor_Data> implements View.OnClickListener {
 private int resourceId;
 private static final String TAG="AttentionmanageAdapter";
 private List<User_Professor_Data> mContentList;
 private Context mContext;
 private AttentionmanageAdapter.Callback mCallback;
    public interface Callback {
        void click(View v);
    }
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
    public AttentionmanageAdapter(Context context, int textViewResourceId,
                                List<User_Professor_Data> objects, AttentionmanageAdapter.Callback callback) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.mContext=context;
        this.mContentList=objects;
        mCallback=callback;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        User_Professor_Data user_professor_data= getItem(position);
        View view;
        AttentionmanageAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new AttentionmanageAdapter.ViewHolder();
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            //viewHolder.question = (TextView) view.findViewById(R.id.question);
            // viewHolder.answer = (TextView) view.findViewById(R.id.answer);
            viewHolder.professor_id=(TextView) view.findViewById(R.id.professor_id);
            viewHolder.delete_attention=(Button) view.findViewById(R.id.delete_attention);
            view.setTag(viewHolder);

        } else {
            view=convertView;

            viewHolder = (AttentionmanageAdapter.ViewHolder) view.getTag();
        }
        // viewHolder.attention.setOnClickListener(this);
        //viewHolder.collect.setOnClickListener(this);
        viewHolder.delete_attention.setOnClickListener(this);
        viewHolder.delete_attention.setTag(position);
        viewHolder.professor_id.setText(user_professor_data.getProfessor_concern_Id()+" ");
        //  Log.e(TAG,"233"+viewHolder.question.getText());
        //Log.e(TAG,"222333" + viewHolder.answer.getText());

        return view;
    }


    class ViewHolder {
        //TextView question;
        //TextView answer;
        TextView professor_id;
        Button delete_attention;

    }
    @Override
    public void onClick(View v) {
        mCallback.click(v);
    }
}
