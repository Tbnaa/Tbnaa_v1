package com.example.tbnaaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AdoptionRequests extends Fragment {

    RecyclerView recyclerViewAdoption;
    String catname[], catgender[], catcity[], decision[];
    int catimages[] = {R.drawable.img_cat_lucy, R.drawable.img_cat_sasha, R.drawable.img_cat_nader, R.drawable.img_cat_alex, R.drawable.img_cat_maya};

    public AdoptionRequests() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AdoptionRequests newInstance(String param1, String param2) {
        AdoptionRequests fragment = new AdoptionRequests();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adoption_requests_list, container, false);

        recyclerViewAdoption = view.findViewById(R.id.recyclerViewAdoptionRequests);

        catname = getResources().getStringArray(R.array.adoption_requests);
        catgender = getResources().getStringArray(R.array.cat_gender);
        catcity = getResources().getStringArray(R.array.cat_city);
        decision = getResources().getStringArray(R.array.request_decision);
        //com.example.tbnaaproject.MyAdapter(Context ct, String name[], String gender[], String city[], String dec[], int imgs[])
        MyAdapter adapter = new MyAdapter(getActivity(), catname, catgender, catcity, decision, catimages);
        recyclerViewAdoption.setAdapter(adapter);
        //new LinearLayoutManager(this) for main activity
        recyclerViewAdoption.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }
}