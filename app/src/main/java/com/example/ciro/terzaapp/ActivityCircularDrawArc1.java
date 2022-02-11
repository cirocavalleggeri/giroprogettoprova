package com.example.ciro.terzaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ciro.terzaapp.customview.CustomCircularDrawArc1;

public class ActivityCircularDrawArc1 extends AppCompatActivity {
int angolomobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_draw_arc1);
        CustomCircularDrawArc1 arcoView=new CustomCircularDrawArc1(this,null);
        /*arcoView.setAngolomobile(30);
        arcoView.invalidate();*/
       /* for (int angolomobile=0;angolomobile<360;angolomobile++){
          arcoView.setAngolomobile(angolomobile);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arcoView.invalidate();
        }*/
    }
}