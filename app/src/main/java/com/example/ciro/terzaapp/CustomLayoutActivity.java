package com.example.ciro.terzaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.ciro.terzaapp.customview.CustomLayout;
import com.example.ciro.terzaapp.customview.CustomVew;

import java.util.Random;

public class CustomLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_layout);
        //CustomLayout1 customLayout = (CustomLayout1) findViewById(R.id.id_custom_layout);
        CustomLayout customLayout = (CustomLayout) findViewById(R.id.id_custom_layout);
        //CustomLayout customLayout = new CustomLayout(this,null);

        Random rnd = new Random();
        for(int i = 1; i < 366; i++) {
            CustomVew view = new CustomVew(this);

           /* int width = rnd.nextInt(200) + 50;
            int height = rnd.nextInt(100) + 100;*/
            int width = 200;
            int height = 200;
            view.setLayoutParams(new ViewGroup.LayoutParams(width, height));
            view.setPadding(2, 2, 2, 2);
            view.setTesto(String.valueOf(i));

            customLayout.addView(view);

        }
        // customLayout.ridisegna();
        // setContentView(customLayout);
    }
}