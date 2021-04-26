package com.example.stomatology;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class EntityAdapter extends ArrayAdapter<Entity> {
    private Context context;
    private Entity entity;

    public EntityAdapter(Context c,  List<Entity> users) {
        super(c, 0, users);
        this.context = c;
    }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        entity = getItem(position);
        Log.d("RRR",position+"");
        context = parent.getContext();
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }
        TextView tv = convertView.findViewById(R.id.item);
        tv.setText(entity.getName());
        return convertView;
    }
}