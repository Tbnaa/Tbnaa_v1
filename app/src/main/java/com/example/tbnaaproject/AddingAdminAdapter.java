package com.example.tbnaaproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tbnaaproject.models.Cats;

import java.util.ArrayList;

public class AddingAdminAdapter extends BaseAdapter {
        Context context;
        private ArrayList<Cats> catsList;
        private static LayoutInflater inflater = null;
        private static ListView listView;

        public AddingAdminAdapter(Context context, ArrayList<Cats> catsList) {
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
                        convertView = inflater.inflate(R.layout.activity_adding_cats_requests_list, null);

                ImageView catImageView = (ImageView) convertView.findViewById(R.id.image);
                TextView catNameTextView = (TextView) convertView.findViewById(R.id.catName);
                ImageButton yes = (ImageButton) convertView.findViewById(R.id.yes);
                ImageButton no = (ImageButton) convertView.findViewById(R.id.no);


                Cats c = new Cats();
                c = catsList.get(position);

                Bitmap cattImage = BitmapFactory.decodeByteArray(c.getCatImage(), 0, c.getCatImage().length);
                catImageView.setImageBitmap(cattImage);
                catNameTextView.setText("Name: " + c.getCatName());
                return convertView;

                //OnClick listeners for all the buttons on the ListView Item
                yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                });

                //OnClick listeners for all the buttons on the ListView Item
                no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                });


        }



}
