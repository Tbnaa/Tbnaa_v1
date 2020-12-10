package com.example.tbnaaproject;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.tbnaaproject.adapters.CatCardsAdapter;
import com.example.tbnaaproject.models.Cats;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_cat_info#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_cat_info extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int catId;
    private byte[] catImage={(byte) R.drawable.img_cat_lucy};
    private String catName = "Zeo";
    private String catCity = "Dammam";
    private String catGender = "female";

    GridView gridView;
    ArrayList<Cats> catsList;
    CatCardsAdapter catAdapter;
    public Fragment_cat_info() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_cat_info.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_cat_info newInstance(String param1, String param2) {
        Fragment_cat_info fragment = new Fragment_cat_info();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

      //  TbnaaDatabase databaseHelper = new TbnaaDatabase(Fragment_cat_info.this);
        //catsList = new ArrayList<Cats>();

        //catsList = databaseHelper.getAllCats();
        // catAdapter = new CatCardsAdapter(Fragment_cat_info.this, catsList);
      //  gridView.setAdapter(catAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cat_info, container, false);
    }
}