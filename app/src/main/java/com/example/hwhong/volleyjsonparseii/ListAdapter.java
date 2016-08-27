package com.example.hwhong.volleyjsonparseii;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by hwhong on 8/27/16.
 */
public class ListAdapter extends ArrayAdapter<String> {

    private String[] name = {};
    private String[] email = {};
    private String[] home = {};
    private String[] mobile = {};
    private Context c;
    private LayoutInflater inflater;

    public ListAdapter(Context context, String[] name, String[] email, String[] home, String[] mobile) {
        super(context, R.layout.row, name);
        this.c = context;
        this.name = name;
        this.email = email;
        this.home = home;
        this.mobile = mobile;
    }

    public class ViewHolder {
        TextView name;
        TextView email;
        TextView home;
        TextView mobile;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row, null);
        }

        final ViewHolder holder = new  ViewHolder();

        holder.name = (TextView) convertView.findViewById(R.id.name);
        holder.email = (TextView) convertView.findViewById(R.id.email);
        holder.home = (TextView) convertView.findViewById(R.id.home);
        holder.mobile = (TextView) convertView.findViewById(R.id.mobile);

        holder.name.setText(name[position]);
        holder.email.setText(email[position]);
        holder.home.setText(home[position]);
        holder.mobile.setText(mobile[position]);

        return convertView;
    }
}