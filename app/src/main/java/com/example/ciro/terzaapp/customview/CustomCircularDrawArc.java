package com.example.ciro.terzaapp.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class CustomCircularDrawArc extends View {
  private final static  int COLORE_DI_FONDO=0xF5FF4081;
  private final static  int COLORE_DI_PRIMO_PIANO=0xF5E7E7E7;
  private Paint paint;
  private  Paint backgroundPaint;
   private int angolo=278;


    public CustomCircularDrawArc(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setColor(COLORE_DI_PRIMO_PIANO);
        paint.setStyle(Paint.Style.FILL);

        backgroundPaint=new Paint();
        backgroundPaint.setColor(COLORE_DI_FONDO);
        backgroundPaint.setStyle(Paint.Style.FILL);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

       // public void drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint)
        canvas.drawArc(10,10,getWidth(),getHeight(),0,angolo,true,paint);
        int diametro_cerchio=0;
        if(getWidth()<getHeight()){diametro_cerchio=getWidth();
    }else {diametro_cerchio=getHeight();}
        canvas.drawArc(10,10,diametro_cerchio,diametro_cerchio,0,angolo,true,paint);
        int margine_orizzontale=(getWidth()-diametro_cerchio)/2;
        int margine_verticale=(getHeight()-diametro_cerchio)/2;
        canvas.drawArc(margine_orizzontale,margine_verticale,margine_orizzontale+diametro_cerchio,margine_verticale+diametro_cerchio,0,angolo,true,paint);
}
}
