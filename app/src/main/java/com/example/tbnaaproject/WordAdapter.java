package com.example.tbnaaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.core.content.ContextCompat;

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Context context, ArrayList<Word> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_adoption_requests_adminlist, parent, false);
        }

        Word currentWord = getItem(position);

        TextView catTextView = (TextView) listItemView.findViewById(R.id.catName);
        catTextView.setText(currentWord.getCatName());

        TextView userTextView = (TextView) listItemView.findViewById(R.id.userName);
        userTextView.setText(currentWord.getUserName());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        imageView.setImageResource(currentWord.getmImageResourceId());
        imageView.setVisibility(View.VISIBLE);

        return listItemView;
    }
}
