package com.example.ciro.terzaapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivityStarter extends ListActivity {
    String nomeActivity[] = { "MainActivity",
            "CustomViewTextView", "ActivityCustomViewParametri","CustomLayoutActivity","ActivityCircularDraw" ,"ActivityCircularDrawArc1","ActivityCustomCircularDrawArcPath","ActivityDraw" ,
            "ActivityCircularindicator","ActivityCircularIndicatorOneTouchDrag"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,nomeActivity));
    }

    @Override
    protected void onListItemClick(ListView list, View view, int position,
                                   long id) {
        super.onListItemClick(list, view, position, id);
        String nomefinale = nomeActivity[position];
        try {
            Class clazz = Class
                    .forName("com.example.ciro.terzaapp." + nomefinale);
            Intent intent = new Intent(this, clazz);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

