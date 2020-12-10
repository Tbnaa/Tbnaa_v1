package com.example.tbnaaproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tbnaaproject.R;
import com.example.tbnaaproject.TbnaaMemberWords;
import com.example.tbnaaproject.Word;


import java.util.ArrayList;

public class TbnaaMemberAdapter extends ArrayAdapter<TbnaaMemberWords> {


    public TbnaaMemberAdapter(Context context, ArrayList<TbnaaMemberWords> words) {
        super(context,0, words);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_tbnaa_member_list, parent, false);
        }

        TbnaaMemberWords currentWord = getItem(position);

        TextView MemberName = (TextView) listItemView.findViewById(R.id.MemName);
        MemberName.setText(currentWord.getName());

        TextView phoneTextView = (TextView) listItemView.findViewById(R.id.MemPhone);
        phoneTextView.setText(currentWord.getPhone());

        TextView EmailTextView = (TextView) listItemView.findViewById(R.id.MemEmail);
        EmailTextView.setText(currentWord.getEmail());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.MemberImage);
        imageView.setImageResource(currentWord.getmImageResourceId());
        imageView.setVisibility(View.VISIBLE);

        return listItemView;
    }

}
