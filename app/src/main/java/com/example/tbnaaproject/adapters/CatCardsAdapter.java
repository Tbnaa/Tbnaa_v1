package com.example.tbnaaproject.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tbnaaproject.R;
import com.example.tbnaaproject.models.Cats;

import java.util.ArrayList;

public class CatCardsAdapter extends BaseAdapter {

    Context context;
    private ArrayList<Cats> catsList;
    private static LayoutInflater inflater = null;

    public CatCardsAdapter(Context context, ArrayList<Cats> catsList) {
        this.context = context;
        this.catsList = catsList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return catsList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = inflater.inflate(R.layout.grid_view_items, null);

        ImageView catImageView = (ImageView) convertView.findViewById(R.id.product_imageView);
        TextView catNameTextView = (TextView) convertView.findViewById(R.id.name_textView);
        TextView catGenderTextView = (TextView) convertView.findViewById(R.id.gender_textView);
        TextView catCityTextView = (TextView) convertView.findViewById(R.id.City_textView);

        Cats c = new Cats();
        c = catsList.get(position);

        Bitmap cattImage = BitmapFactory.decodeByteArray(c.getCatImage(), 0, c.getCatImage().length);
        catImageView.setImageBitmap(cattImage);
        catNameTextView.setText("Name: " + c.getCatName());
        catGenderTextView.setText("Gender: " + c.getCatGender());
        catCityTextView.setText("City: " + c.getCatCity());
        return convertView;
    }
}
