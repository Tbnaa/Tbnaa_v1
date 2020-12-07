package com.example.tbnaaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CatCardsAdapter {

//    Context context;
//    private ArrayList<Product> originalList;
//    private ArrayList<Product> dataList;
//    private ProductFilter filter;
//
//    public ProductGridAdapter(Context context, int layoutResourceId, ArrayList<Product> dataList) {
//        super(context, layoutResourceId, dataList);
//        this.context = context;
//
//        this.originalList = new ArrayList<Product>();
//        this.originalList.addAll(dataList);
//
//        this.dataList = new ArrayList<Product>();
//        this.dataList.addAll(dataList);
//    }
//
//    @Override
//    public int getCount() {
//        return super.getCount();
//    }
//    @Override
//    public Filter getFilter() {
//        if (filter == null){
//            filter  = new ProductFilter();
//        }
//        return filter;
//    }
//
//    private static class ViewHolder {
//        ImageView product_imageView;
//        TextView name_textView, price_textView;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        ViewHolder holder;
//        if (convertView == null) {
//            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.grid_view_items, null);
//
//            holder = new ViewHolder();
//            holder.product_imageView = convertView.findViewById(R.id.product_imageView);
//            holder.name_textView = convertView.findViewById(R.id.name_textView);
//            holder.price_textView = convertView.findViewById(R.id.price_textView);
//
//            convertView.setTag(holder);
//        }else{
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        final Product object = dataList.get(position);
//        holder.product_imageView.setImageResource(object.getImageID());
//        holder.price_textView.setText("Price : " + object.getPrice());
//        holder.name_textView.setText(object.getName());
//
//        return convertView;
//    }
//
//    // This class is use to filter the data for search able
//    private class ProductFilter extends Filter
//    {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//
//            String value =  constraint.toString().toLowerCase();
//            FilterResults result = new FilterResults();
//            if(constraint.toString().length()>0)
//            {
//                ArrayList<Product> filteredItems = new ArrayList<Product>();
//
//                for(int i = 0, l = originalList.size(); i < l; i++) {
//                    Product product = originalList.get(i);
//                    if(product.getCategoryID().toLowerCase().equalsIgnoreCase(value))
//                        filteredItems.add(product);
//                }
//                result.count = filteredItems.size();
//                result.values = filteredItems;
//            } else {
//                synchronized(this) {
//                    result.values = originalList;
//                    result.count = originalList.size();
//                }
//            }
//            return result;
//        }
//
//        @SuppressWarnings("unchecked")
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            dataList = (ArrayList<Product>)results.values;
//            notifyDataSetChanged();
//            clear();
//            for(int i = 0, l = dataList.size(); i < l; i++)
//                add(dataList.get(i));
//            notifyDataSetInvalidated();
//        }
//    }
}
