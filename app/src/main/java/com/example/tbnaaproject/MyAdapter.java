package com.example.tbnaaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  {
    String catname[], catgender[], catcity[],  decision[];
    int gendericon[] = {R.drawable.ic_male_cat, R.drawable.ic_female_cat};
    int status[] = {R.drawable.ic_accept, R.drawable.ic_declined};
    int images[];
    Context context;

    public MyAdapter(Context ct, String name[], String gender[], String city[], String dec[], int imgs[]){
        context = ct;
        catname = name;
        catgender = gender;
        catcity = city;
        decision = dec;
        images = imgs;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cat_adding_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.catName.setText(catname[position]);
        holder.catCity.setText(catcity[position]);
        holder.catGender.setImageResource(catgender[position].equals("male")? gendericon[0]:gendericon[1]);
        holder.catImage.setImageResource(images[position]);
        //TODO: set icon based on status
        holder.requestStatus.setText(decision[position].toUpperCase());
    }

    @Override
    public int getItemCount() {
        return catname.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView catName, catCity, requestStatus;
        ImageView catGender, catImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            catName = itemView.findViewById(R.id.textViewCatName);
            catCity = itemView.findViewById(R.id.textViewCatCity);
            catImage = itemView.findViewById(R.id.imageViewCat);
            catGender = itemView.findViewById(R.id.imageViewCatGender);
            requestStatus = itemView.findViewById(R.id.textViewRequestStatus);

        }
    }
}
