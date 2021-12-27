package com.example.listfiletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UnderProductActivity extends AppCompatActivity {

    ListView list ;
    String productName ;
    ArrayList<Product> productArrayList = new ArrayList<Product>();
    CustomerAdapter adapter ;
    public static final int REQUEST_CODE1 = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_product);
        list = findViewById(R.id.listView);
        productName = getIntent().getStringExtra("product");
        System.out.println("*************** on Create Under ****************");
        System.out.println(productName);
        readAndAdapt(productName);

    }

    public void readAndAdapt(String productName){
        try{
            InputStream in = openFileInput(productName+".txt");
            if(in != null){
                InputStreamReader tmp = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(tmp);
                String element ;
                StringBuffer buffer = new StringBuffer();
                while((element= reader.readLine())!=null){
                    buffer.append(element+"\n") ;
                }
                in.close();
                String[] productsProps;
                String[] products = buffer.toString().split("\n");
                for (int j =0 ; j<products.length;j++){
                    Product temProduct = new Product() ;
                    String[] productProps ;
                    productProps=products[j].split(",");
                    temProduct.setName(productProps[0]);
                    temProduct.setDescription(productProps[1]);
                    productArrayList.add(temProduct) ;
                }

            }
        }catch (FileNotFoundException FNF){
            System.out.println("EXCEPTION "+ FNF.toString());
        }
        catch(Throwable t){}

        adapter = new CustomerAdapter(getApplicationContext() , productArrayList);
        list.setAdapter(adapter);

    }
}