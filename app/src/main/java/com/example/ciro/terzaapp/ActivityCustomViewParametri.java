package com.example.ciro.terzaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.ciro.terzaapp.customview.CustomVewParametri;

public class ActivityCustomViewParametri extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_custom_view_parametri);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(
                new LinearLayout.LayoutParams(ViewGroup.
                        LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
       CustomVewParametri customView = new CustomVewParametri(this,null);
        customView.setFrase("Ciao da programma");
        customView.setColore( 0xff00ff00);
        linearLayout.addView(customView);

        setContentView(linearLayout);
    }
}