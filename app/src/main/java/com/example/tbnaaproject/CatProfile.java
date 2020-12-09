package com.example.tbnaaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CatProfile extends AppCompatActivity {

    int catID,userId;
    byte[] catImageByte;
    TextView catAgeTextView, catCityTextView, catOwnerTextView, catNameTextView,catStateTextView,
            catStoryTextView, catGender_TextView,vaccinated_TextView,neutered_TextView;
    ImageView vaccinated_imageView,neutered_imageView, catGender_imageView,catImage_imageView,catStateImage;
    Button adoptButton;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_profile);

        final TbnaaDatabase tbnaadbHelper = new TbnaaDatabase(this);


        //Extract values from prevese page
//        Bundle b = getIntent().getExtras();
//        catID = b.getInt("catID");
//        userId = b.getInt("userID");


        userId=5;
        init();

        //i will remove this
        catID =1;
        Cursor c = tbnaadbHelper.retrieveCatInfo(catID);
        while (c.moveToNext()){

            //image
            catImageByte= c.getBlob(0);
            Bitmap bitmap= BitmapFactory.decodeByteArray(catImageByte,0,catImageByte.length);
            catImage_imageView.setImageBitmap(bitmap);

            //cat state
            String catStateString;
            catStateString=c.getString(8);
            if(catStateString.equalsIgnoreCase("null")||catStateString.equalsIgnoreCase("")){
                //do nothing
            }else{
                catStateTextView.setText("Adopted");
                catStateTextView.setTextColor(Color.RED);

            }


            //other data
            catNameTextView.setText(c.getString(1));
            catStoryTextView.setText(c.getString(7));
            catAgeTextView.setText(c.getString(2));
            catCityTextView.setText(c.getString(3));
            catGender_TextView.setText(c.getString(4));
            vaccinated_TextView.setText(c.getString(5));
            neutered_TextView.setText(c.getString(6));

            adoptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(CatProfile.this, AdoptionForm.class);
                    intent.putExtra("catID",catID);
                    intent.putExtra("userID",userId);
                    startActivity(intent);

                }
            });


        }
    }

    private void init() {
        //TextView
        catAgeTextView = (TextView) findViewById(R.id.catAge_catProfile_xml);
        catCityTextView = (TextView) findViewById(R.id.catCity_catProfile_xml);
        //catOwnerTextView = (TextView) findViewById(R.id.owner_catProfile_xml);
        catNameTextView = (TextView) findViewById(R.id.catName_catProfile_xml);
        catStoryTextView = (TextView) findViewById(R.id.catStory_catProfile_xml);
        catStateTextView=(TextView) findViewById(R.id.catState_catProfile_xml);
        catGender_TextView=(TextView) findViewById(R.id.catGender_catProfile_xml);
        vaccinated_TextView=(TextView) findViewById(R.id.catVaccinated_catProfile_xml);
        neutered_TextView=(TextView) findViewById(R.id.catNeutered_catProfile_xml);

        //Buttons
        adoptButton = (Button) findViewById(R.id.adoptButton_catProfile_xml);


        //ImageView
        vaccinated_imageView = (ImageView) findViewById(R.id.vaccinated_imageView);
       // neutered_imageView = (ImageView) findViewById(R.id.neutered_imageView);
        catGender_imageView = (ImageView) findViewById(R.id.catGender_imageView);
        catImage_imageView= (ImageView) findViewById(R.id.cat_image_catProfile_xml);
        //catStateImage = (ImageView) findViewById(R.id.catStateImage_catProfile);


    }


}