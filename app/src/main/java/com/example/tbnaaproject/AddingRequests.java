package com.example.tbnaaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddingRequests extends Fragment {
    RecyclerView recyclerViewAdding;
    String catname[], catgender[], catcity[], decision[];
    int catimages[] = {R.drawable.img_cat_nader, R.drawable.img_cat_alex, R.drawable.img_cat_maya};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adding_requests_list, container, false);

        recyclerViewAdding = view.findViewById(R.id.recyclerViewAddingRequests);

        catname = getResources().getStringArray(R.array.adding_requests_cats);
        catgender = getResources().getStringArray(R.array.adding_requests_gender);
        catcity = getResources().getStringArray(R.array.adding_requests_city);
        decision = getResources().getStringArray(R.array.adding_requests_status);
        //com.example.tbnaaproject.MyAdapter(Context ct, String name[], String gender[], String city[], String dec[], int imgs[])
        MyAdapter adapter = new MyAdapter(getActivity(), catname, catgender, catcity, decision, catimages);
        recyclerViewAdding.setAdapter(adapter);
        //new LinearLayoutManager(this) for main activity
        recyclerViewAdding.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }
}