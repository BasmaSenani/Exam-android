package com.example.listfiletest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;

public class CreateProductActivity extends AppCompatActivity {

    EditText name ;
    EditText description ;
    Button ajouter ;
    Button retourner ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_layout);
        name = findViewById(R.id.editName);
        description = findViewById(R.id.editDescription);
        ajouter = findViewById(R.id.ajouter);
        retourner = findViewById(R.id.getBack);

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("product",
                        name.getText().toString()+","+description.getText().toString()+"\n");
                setResult(RESULT_OK , intent);
                finish();
            }

        });

        retourner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                setResult(RESULT_OK , intent);
                finish();
            }
        });


    }

    public void onPause(){
        super.onPause();
        System.out.println("******************* onPause Second ********************");
        try{
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput("products.txt",0));
            out.write(name.getText().toString()+","+description.getText().toString()+"\n");
            System.out.println("************ onTry second ****************");
            out.close() ;
        }catch(Throwable t){
            Toast.makeText(this,"Exception :"+t.toString(),Toast.LENGTH_LONG).show();
        }
    }

}
