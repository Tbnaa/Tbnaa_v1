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
import android.widget.Toast;

import com.example.tbnaaproject.models.Cats;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
//implements View.OnClickListener
public class AddingAdminAdapter extends BaseAdapter{
        Context context;
        private ArrayList<AddingAdminWord> catsList;
        private static LayoutInflater inflater = null;
        private static ListView listView;
        TbnaaDatabase DBhelper;
        AddingAdminWord c;

        public AddingAdminAdapter(Context context, ArrayList<AddingAdminWord> catsList) {
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


        private int lastPosition = -1;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null)
                        convertView = inflater.inflate(R.layout.activity_adding_cats_requests_list, null);

                ImageView catImageView = (ImageView) convertView.findViewById(R.id.image);
                TextView catNameTextView = (TextView) convertView.findViewById(R.id.catName);
                ImageButton yes = (ImageButton) convertView.findViewById(R.id.yes);
                ImageButton no = (ImageButton) convertView.findViewById(R.id.no);

                c = new AddingAdminWord();
                c = catsList.get(position);

                Bitmap cattImage = BitmapFactory.decodeByteArray(c.getCatImage(), 0, c.getCatImage().length);
                catImageView.setImageBitmap(cattImage);
                catNameTextView.setText("Name: " + c.getCatName());

                yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Toast.makeText(context, "Accept", Toast.LENGTH_SHORT).show();
                                DBhelper.UpdateIsApproved(c.getCatID());

                        }
                });


                no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Toast.makeText(context, "reject", Toast.LENGTH_SHORT).show();
                                DBhelper.UpdateIsApprovedReject(c.getCatID());
                        }
                });

                return convertView;


        }
/*
        @Override
        public void onClick(View v) {

                int position=(Integer) v.getTag();
                Object object= getItem(position);
                AddingAdminWord dataModel=(AddingAdminWord)object;

                switch (v.getId())
                {
                        case R.id.yes:
                                Snackbar.make(v, "Release date " +dataModel.getCatName(), Snackbar.LENGTH_LONG)
                                        .setAction("No action", null).show();
                                Toast.makeText(context, "meeeeeeee", Toast.LENGTH_SHORT).show();
                                break;
                        case R.id.no:
                                Snackbar.make(v, "Release date " +dataModel.getCatImage(), Snackbar.LENGTH_LONG)
                                        .setAction("No action", null).show();
                                Toast.makeText(context, "meeeeeeee", Toast.LENGTH_SHORT).show();
                                break;
                }
        }
*/


}
