package com.example.tbnaaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tbnaaproject.models.Cats;

import java.util.ArrayList;

public class AddingAdminAdapter extends ArrayAdapter<AddingAdminWord> {


       public AddingAdminAdapter(Context context, ArrayList<AddingAdminWord> Adminwords) {
        super(context, 0, Adminwords);
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
        listItemView = LayoutInflater.from(getContext()).inflate(
        R.layout.activity_adding_cats_requests_list, parent, false);
        }

        AddingAdminWord currentWord = getItem(position);

        TextView catTextView = (TextView) listItemView.findViewById(R.id.catName);
        catTextView.setText(currentWord.getCatName());

        TextView userTextView = (TextView) listItemView.findViewById(R.id.userName);
        userTextView.setText(currentWord.getUserName());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        imageView.setImage(currentWord.getCatImage());
        imageView.setVisibility(View.VISIBLE);

        return listItemView;
        }
}
