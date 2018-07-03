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

import java.util.List;

/**
 * Created by cheng on 2018/4/17.
 */

public class Professor_QuestionAdapter extends ArrayAdapter<QuestionData> implements View.OnClickListener {
    private int resourceId;
    private static final String TAG="ProfessorAdapter";
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
    public Professor_QuestionAdapter(Context context, int textViewResourceId,
                           List<QuestionData> objects,Callback callback) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.mContext=context;
        this.mContentList=objects;
        mCallback=callback;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        QuestionData questiondata= getItem(position);
        View view;
        Professor_QuestionAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new Professor_QuestionAdapter.ViewHolder();
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder.question = (TextView) view.findViewById(R.id.question);
            viewHolder.answer = (TextView) view.findViewById(R.id.answer);
            viewHolder.question_id=(TextView)view.findViewById(R.id.question_id);
            viewHolder.professor_answer=(Button) view.findViewById(R.id.professor_answer);
            view.setTag(viewHolder);

        } else {
            view=convertView;

            viewHolder = (Professor_QuestionAdapter.ViewHolder) view.getTag();
        }
        // viewHolder.attention.setOnClickListener(this);
        //viewHolder.collect.setOnClickListener(this);
        viewHolder.professor_answer.setOnClickListener(this);
        viewHolder.professor_answer.setTag(position);
        viewHolder.question.setText(questiondata.getMcontext());
        //  Log.e(TAG,"233"+viewHolder.question.getText());
        viewHolder.answer.setText(questiondata.getAnswer());
        viewHolder.question_id.setText(questiondata.getQuestionId()+" ");
        //Log.e(TAG,"222333" + viewHolder.answer.getText());
        return view;
    }


    class ViewHolder {
        TextView question;
        TextView answer;
        TextView question_id;
        Button professor_answer;


    }
    @Override
    public void onClick(View v) {
        mCallback.click(v);
    }
}
