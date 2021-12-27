package com.example.listfiletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Product> productArrayList ;
    ListView list ;
    CustomerAdapter adapter ;
    Button addProduct ;
    public static int onCreation =0 ;
    public static final int REQUEST_CODE1 = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("*************** onCreate *********************");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.listView);
        addProduct = findViewById(R.id.addProduct) ;
        productArrayList = new ArrayList<Product>() ;
        readAndAdapt();
        try {
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput("product1.txt", 0));
            out.write("product1,this is a great product \n");
            out.write("product1,this is a great product \n");
            out.write("product1,this is a great product \n");
            out.write("product1,this is a great product \n");
            out.close();
        } catch (Throwable t) {
            Toast.makeText(this, "Exception :" + t.toString(), Toast.LENGTH_LONG).show();
        }
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateProductActivity.class);
                startActivityForResult(
                        intent , REQUEST_CODE1
                );
            }
        });

    }

    public void createFile(){
        try {
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput("products.txt", 0));
            out.write("product1,this is a great product \n");
            out.write("product2,this is a great product \n");
            out.write("product3,this is a great product \n");
            out.write("product4,this is a great product \n");
            out.close();
        } catch (Throwable t) {
            Toast.makeText(this, "Exception :" + t.toString(), Toast.LENGTH_LONG).show();

        }
    }

    public void readAndAdapt(){
        try{
            InputStream in = openFileInput("products.txt");
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
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),UnderProductActivity.class);
                intent.putExtra("product",productArrayList.get(position).getName());
                startActivityForResult(
                        intent , REQUEST_CODE1
                );

            }
        });
    }


    public void onResume(){
        super.onResume();
        System.out.println("************************* onResume ********************");
        readAndAdapt();
    }

   /* protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("********************* onActivityResult ********************");
        if(requestCode==REQUEST_CODE1)
        {
            if(resultCode==RESULT_OK)
            {
                String stringExtra= data.getStringExtra("product");
                try{
                    OutputStreamWriter out = new OutputStreamWriter(openFileOutput("products.txt",0));
                    out.write(stringExtra);
                    out.close() ;
                }catch(Throwable t){
                    Toast.makeText(this,"Exception :"+t.toString(),Toast.LENGTH_LONG).show();
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/






}