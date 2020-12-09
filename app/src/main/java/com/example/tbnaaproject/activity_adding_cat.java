package com.example.tbnaaproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class activity_adding_cat extends AppCompatActivity {

    EditText catNameEditText,catStoryEditText,catAgeEditText, catHealtheConditionEditText;
    ImageView catImage_ImageView;
    Spinner catCitySpinner;
    Button addCatButton,uploadCatImageButton;
    RadioGroup genderRadioGroup, vaccinatedRadioGroup,neuteredRadioGroup;
    RadioButton catGenderRadioButton, isVaccinatedRadioButton, isNeuteredRadioButton;

    String cities[]={"Choose a city","Riyadh","Abha","Dammam","Jeddah","Medina","Mecca"};
    String cityName;

    // for uploading cat image
    final int PICK_IMAGE_FROM_GALLERY=1;
    public Bitmap imageBitmap;
    public byte[] imageByte;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_cat);

        final TbnaaDatabase tbnaadbHelper= new TbnaaDatabase(this);

        init();

        // upload cat image
        uploadCatImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, 1001);
                    } else pickImageFromGallery();
                } else pickImageFromGallery();
            }
        });

        //set Spinner adapter
        ArrayAdapter <String> adapter=new ArrayAdapter <String>(this,R.layout.support_simple_spinner_dropdown_item,cities);
        catCitySpinner.setAdapter(adapter);

        //city Spinner values
        catCitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {

                    case 0:
                        cityName = "";
                        break;
                    case 1:
                        cityName = "Riyadh";
                        break;

                    case 2:
                        cityName = "Abha";
                        break;

                    case 3:
                        cityName = "Dammam";
                        break;

                    case 4:
                        cityName = "Jeddah";
                        break;

                    case 5:
                        cityName = "Medina";
                        break;

                    case 6:
                        cityName = "Mecca";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //add cat
        addCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String catName, catStory, catAge, catHealtheCondition, catCity,
                            catGender, catVaccinated, catNeutered;
                    byte[] catImage;

                    catName = catNameEditText.getText().toString();
                    catStory = catStoryEditText.getText().toString();
                    catAge = catAgeEditText.getText().toString();
                    catHealtheCondition = catHealtheConditionEditText.getText().toString();
                    catCity = cityName;


                    if (catName.isEmpty() || catStory.isEmpty() || catAge.isEmpty()
                            || catHealtheCondition.isEmpty()
                    ) {
                        toast("Please fill all the fields");
                    } else if (genderRadioGroup.getCheckedRadioButtonId() == -1) {
                        // no radio buttons are checked
                        toast("Please enter cat's gender");
                    } else if (vaccinatedRadioGroup.getCheckedRadioButtonId() == -1) {
//                        // no radio buttons are checked
                        toast("Please enter cat's vaccinated field");
                    } else if (neuteredRadioGroup.getCheckedRadioButtonId() == -1) {
                        // no radio buttons are checked
                        toast("Please enter cat's neutered field");
                    } else if (catCity.isEmpty()) {
                        toast("Please enter cat's city field");
                    } else {

                        //RadioButtons
                        int genderRadioButtonID = genderRadioGroup.getCheckedRadioButtonId();
                        catGenderRadioButton = findViewById(genderRadioButtonID);
                        catGender = catGenderRadioButton.getText().toString();

                        int vaccinatedRadioGroupID = vaccinatedRadioGroup.getCheckedRadioButtonId();
                        isVaccinatedRadioButton = findViewById(vaccinatedRadioGroupID);
                        catVaccinated = isVaccinatedRadioButton.getText().toString();


                        int neuteredRadioGroupID = neuteredRadioGroup.getCheckedRadioButtonId();
                        isNeuteredRadioButton = findViewById(neuteredRadioGroupID);
                        catNeutered = isNeuteredRadioButton.getText().toString();

                        //image
                        catImage = imageViewToByte(catImage_ImageView);

                        //add cat to db

                        tbnaadbHelper.addCat(catImage, catName, catAge,
                                catCity, catGender, catVaccinated,
                                catNeutered, catHealtheCondition, catStory);

                        toast("Your request for adding your cat has been sent successfully. Wait For administrator approval");

                        //----------------------------------------------------------------
                        //add data by using content provider
                        ContentValues values = new ContentValues();

                        // fetching text from user
                        values.put(TbnaaContentProvider.CatName,catName);
                        values.put(TbnaaContentProvider.CatLocation,catCity);
                        // inserting into database through content URI (using TbnaaContentProvider to access the insert method)
                        getContentResolver().insert(TbnaaContentProvider.CONTENT_URI, values);
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }


        });
    }


    // Start upload cat image
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // check permission
        if(requestCode==PICK_IMAGE_FROM_GALLERY){
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,PICK_IMAGE_FROM_GALLERY);
            }else{
                Toast.makeText(getApplicationContext(),"You don't have permission to access file location",Toast.LENGTH_LONG).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //-----------------------------------------------------------------------------------

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==PICK_IMAGE_FROM_GALLERY && resultCode ==RESULT_OK && data!= null){
            imageUri = data.getData();

            try {
                imageBitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                //convert Bitmap into byte
                ByteArrayOutputStream byteStream =new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,byteStream);
                imageByte=byteStream.toByteArray();
                catImage_ImageView.setImageURI(imageUri);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    //End uploading cat image

    //-------------------------------------------------------------------
    private void init() {
        //EditText
        catNameEditText = (EditText) findViewById(R.id.catName_catProfile_xml);
        catStoryEditText = (EditText) findViewById(R.id.clinicName_xml);
        catAgeEditText = (EditText) findViewById(R.id.catAge_catProfile_xml);
        catHealtheConditionEditText = (EditText) findViewById(R.id.adoptedStory_xml);

        //Buttons
        addCatButton = (Button) findViewById(R.id.add_cat_button);
        uploadCatImageButton = (Button) findViewById(R.id.adoptButton_catProfile_xml);

        //ImageView
        catImage_ImageView = (ImageView) findViewById(R.id.cat_image_catProfile_xml);

        //Spinner
        catCitySpinner = (Spinner) findViewById(R.id.catcity_xml);

        //RadioGroup
        genderRadioGroup = findViewById(R.id.cat_gender_rideogroup);
        vaccinatedRadioGroup = findViewById(R.id.vaccinated_rideogroup);
        neuteredRadioGroup = findViewById(R.id.neutered_rideogroup);
    }

    //------------------------------------------------------------------
    private byte[] imageViewToByte(ImageView image) {
        BitmapDrawable drawable=(BitmapDrawable)catImage_ImageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream byteStream =new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteStream);
        imageByte=byteStream.toByteArray();

        return imageByte;
    }

    //-----------------------------------------------------------------
    public void toast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

    public void pickImageFromGallery(){
        Intent galleryIntent= new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,PICK_IMAGE_FROM_GALLERY);

    }





//add cat
//    public void addCat() {
//
//
//        try {
//
//            String catName, catStory, catAge, catHealtheCondition, catCity,
//                    catGender, catVaccinated, catNeutered;
//            byte[] catImage;
//
//            catName = catNameEditText.getText().toString();
//            catStory = catStoryEditText.getText().toString();
//            catAge = catAgeEditText.getText().toString();
//            catHealtheCondition = catHealtheConditionEditText.getText().toString();
//            catCity = "cityName";
//            catGender = catGenderRadioButton.getText().toString();
//            catVaccinated = isVaccinatedRadioButton.getText().toString();
//            catNeutered = isNeuteredRadioButton.getText().toString();
//
//
//            if (genderRadioGroup.getCheckedRadioButtonId() == -1) {
//                // no radio buttons are checked
//                toast("Please enter cat's gender");
//            }
//
//            if (vaccinatedRadioGroup.getCheckedRadioButtonId() == -1) {
//                // no radio buttons are checked
//                toast("Please enter cat's vaccinated field");
//            }
//            if (neuteredRadioGroup.getCheckedRadioButtonId() == -1) {
//                // no radio buttons are checked
//                toast("Please enter cat's neutered field");
//            }
//
//            if (catName.isEmpty() || catStory.isEmpty() || catAge.isEmpty()
//                    || catHealtheCondition.isEmpty() || catGender.isEmpty()
//            ) {
//                toast("Please fill all the fields cat's neutered field");
//            } else {
////                            catImage=imageViewToByte(catImage_ImageView);
////
////                            tbnaaDbHelper.addCat(catImage, catName, catAge,
////                                    catCity, catGender,  catVaccinated,
////                                    catNeutered, catHealtheCondition,catStory);
//                toast("Your request for adding your cat has been sent successfully. Wait For administrator approval");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }

    }