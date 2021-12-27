package com.example.listfiletest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomerAdapter extends ArrayAdapter<Product> {

    ArrayList<Product> myList ;
    Context myContext ;
    public CustomerAdapter( Context myContext, ArrayList<Product> myList) {
        super(myContext, 0,myList);
        this.myContext = myContext ;
        this.myList = myList ;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView ;
        if(v==null){
            v= LayoutInflater.from(myContext).inflate(R.layout.list_item , parent , false);
        }
        Product thisProduct = myList.get(position);
        ImageView image = v.findViewById(R.id.image);
        TextView name = v.findViewById(R.id.txt1) ;
        name.setText(thisProduct.getName());
        TextView description = v.findViewById(R.id.txt2) ;
        description.setText(thisProduct.getDescription());
        return v ;
    }
}
