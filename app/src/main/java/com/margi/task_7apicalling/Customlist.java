package com.margi.task_7apicalling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Margi on 10-Feb-17.
 */
public class Customlist extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Post> p;

    public Customlist(Context context, ArrayList<Post> p)
    {
        this.context = context;
        this.p = p;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return p.size();
    }

    @Override
    public Object getItem(int i) {
        return p.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        if (view == null)
            view = inflater.inflate(R.layout.single_row, null);

        TextView txtid = (TextView) view.findViewById(R.id.main_id);
        TextView txtusrid = (TextView) view.findViewById(R.id.main_userid);
        TextView txtbody = (TextView) view.findViewById(R.id.main_body);
        TextView txttitle = (TextView) view.findViewById(R.id.main_title);


        Post p1 = p.get(i);
        txtusrid.setText(String.valueOf(p1.getUserId()));
        txtid.setText(String.valueOf(p1.getId()));
        txttitle.setText(p1.getTitle());
        txtbody.setText(p1.getBody());

        return view;
    }
}