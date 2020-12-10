package com.example.tbnaaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AdoptionForm extends AppCompatActivity {

    int userId,catID;
    byte[] catImageByte;
    EditText additionalPhoneNoEditText,clinicNameEditText,adoptedStoryEditText;

    RadioGroup hadAdoptedRadioGroup, liveAloneRadioGroup,haveKidsRadioGroup,
            perantApprovalRadioGroup,allergicRadioGroup,isOwnerRadioGroup,catPlacementRadioGroup, pledgeRadioGroup;

    RadioButton gethadAdoptedRadiobutton, getliveAloneRadiobutton,gethaveKidsRadiobutton, getperantApprovalRadiobutton,
    getallergicRadiobutton,getisOwnerRadiobutton,getcatPlacementRadiobutton, getpledgeRadiobutton;

    Button adaptButton;

    ImageView catImage_imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_form);

        final TbnaaDatabase tbnaadbHelper = new TbnaaDatabase(this);

        //Extract values from prevese page
        Bundle b = getIntent().getExtras();
        catID = b.getInt("catID");
        userId = b.getInt("userID");

        //toast(""+catID+"    "+userId);

        init();


        Cursor c = tbnaadbHelper.retrieveCatInfo(catID);
        while (c.moveToNext()) {

            //image
            catImageByte = c.getBlob(0);
            Bitmap bitmap = BitmapFactory.decodeByteArray(catImageByte, 0, catImageByte.length);
            catImage_imageView.setImageBitmap(bitmap);

            adaptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        String additionalPhoneNo, clinicName, adoptedStory, gethadAdoptedValue, getliveAloneValue,
                                gethaveKidsRadiobuttonValue, getperantApprovalRadiobuttonValue,
                                getallergicRadiobuttonValue, getisOwnerRadiobuttonValue, getcatPlacementRadiobuttonValue,
                                getpledgeRadiobuttonValue;

                        // pull values from edit texts
                        additionalPhoneNo = additionalPhoneNoEditText.getText().toString();
                        clinicName = clinicNameEditText.getText().toString();
                        adoptedStory = adoptedStoryEditText.getText().toString();

                        //check that all edit texts are entered
                        if (additionalPhoneNo.isEmpty() || clinicName.isEmpty() || adoptedStory.isEmpty()) {
                            toast("Please fill all the fields");

                            //check that all radio buttons are entered
                        } else if (hadAdoptedRadioGroup.getCheckedRadioButtonId() == -1) {
                            // no radio buttons are checked
                            toast("Please enter the third field");

                        } else if (liveAloneRadioGroup.getCheckedRadioButtonId() == -1) {
                            // no radio buttons are checked
                            toast("Please enter the fifth field\n");

                        } else if (haveKidsRadioGroup.getCheckedRadioButtonId() == -1) {
                            // no radio buttons are checked
                            toast("Please enter the sixth field");

                        } else if (perantApprovalRadioGroup.getCheckedRadioButtonId() == -1) {
                            // no radio buttons are checked
                            toast("Please enter the parent approval field");

                        } else if (allergicRadioGroup.getCheckedRadioButtonId() == -1) {
                            // no radio buttons are checked
                            toast("Please enter the eighth field\n");

                        } else if (isOwnerRadioGroup.getCheckedRadioButtonId() == -1) {
                            // no radio buttons are checked
                            toast("Please enter the ninth field\n");

                        } else if (catPlacementRadioGroup.getCheckedRadioButtonId() == -1) {
                            // no radio buttons are checked
                            toast("Please enter cat's placement field");

                        } else if (pledgeRadioGroup.getCheckedRadioButtonId() == -1) {
                            // no radio buttons are checked
                            toast("Please read the pledge and confirm your agreement ");
                        } else {

                            //RadioButtons
                            int hadAdoptedRadioGroupID = hadAdoptedRadioGroup.getCheckedRadioButtonId();
                            gethadAdoptedRadiobutton = findViewById(hadAdoptedRadioGroupID);
                            gethadAdoptedValue = gethadAdoptedRadiobutton.getText().toString();

                            int liveAloneRadioGroupID = liveAloneRadioGroup.getCheckedRadioButtonId();
                            getliveAloneRadiobutton = findViewById(liveAloneRadioGroupID);
                            getliveAloneValue = getliveAloneRadiobutton.getText().toString();

                            int haveKidsRadioGroupID = haveKidsRadioGroup.getCheckedRadioButtonId();
                            gethaveKidsRadiobutton = findViewById(haveKidsRadioGroupID);
                            gethaveKidsRadiobuttonValue = gethaveKidsRadiobutton.getText().toString();

                            int perantApprovalRadioGroupID = perantApprovalRadioGroup.getCheckedRadioButtonId();
                            getperantApprovalRadiobutton = findViewById(perantApprovalRadioGroupID);
                            getperantApprovalRadiobuttonValue = getperantApprovalRadiobutton.getText().toString();

                            int allergicRadioGroupID = allergicRadioGroup.getCheckedRadioButtonId();
                            getallergicRadiobutton = findViewById(allergicRadioGroupID);
                            getallergicRadiobuttonValue = getallergicRadiobutton.getText().toString();

                            int isOwnerRadioGroupID = isOwnerRadioGroup.getCheckedRadioButtonId();
                            getisOwnerRadiobutton = findViewById(isOwnerRadioGroupID);
                            getisOwnerRadiobuttonValue = getisOwnerRadiobutton.getText().toString();

                            int pledgeRadioGroupID = pledgeRadioGroup.getCheckedRadioButtonId();
                            getpledgeRadiobutton = findViewById(pledgeRadioGroupID);
                            getpledgeRadiobuttonValue = getpledgeRadiobutton.getText().toString();

                            int catPlacementRadioGroupID = catPlacementRadioGroup.getCheckedRadioButtonId();
                            getcatPlacementRadiobutton = findViewById(catPlacementRadioGroupID);
                            getcatPlacementRadiobuttonValue = getcatPlacementRadiobutton.getText().toString();


                            // add adaption form to db
                            tbnaadbHelper.addAdaptionForm(gethadAdoptedValue, adoptedStory,
                                    additionalPhoneNo, getliveAloneValue, gethaveKidsRadiobuttonValue,
                                    getperantApprovalRadiobuttonValue, getallergicRadiobuttonValue, getcatPlacementRadiobuttonValue,
                                    getisOwnerRadiobuttonValue, clinicName, getpledgeRadiobuttonValue);

                            toast("Your request for adapting this cat has been sent successfully. Wait For administrator approval");

                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
        }
    }





    //----------------------------------------------------------------------------------
    private void init(){

        //EditText
        additionalPhoneNoEditText = (EditText) findViewById(R.id.additionalPhoneNo_xml);
        clinicNameEditText = (EditText) findViewById(R.id.clinicName_xml);
        adoptedStoryEditText = (EditText) findViewById(R.id.adoptedStory_xml);

        //RadioGroup
        hadAdoptedRadioGroup= findViewById(R.id.hadAdopted_xml);
        liveAloneRadioGroup = findViewById(R.id.liveAlone_radioGroup);
        haveKidsRadioGroup = findViewById(R.id.haveKids_radioGroup_xml);
        perantApprovalRadioGroup = findViewById(R.id.perantApproval_radioGroup_xml);
        allergicRadioGroup = findViewById(R.id.allergic_radioGroup);
        isOwnerRadioGroup = findViewById(R.id.isOwner_radioGroup);
        catPlacementRadioGroup = findViewById(R.id.catPlacement_radioGroup);
        pledgeRadioGroup = findViewById(R.id.pledge);

        //Buttons
        adaptButton = (Button) findViewById(R.id.adoptButton_adaptionForm_xml);

        //image view
        catImage_imageView= (ImageView) findViewById(R.id.cat_image_catProfile_xml);

    }

    //-------------------------------------------------------
    public void toast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }
}
