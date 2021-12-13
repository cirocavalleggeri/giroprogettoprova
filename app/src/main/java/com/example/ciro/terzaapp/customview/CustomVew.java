package com.example.ciro.terzaapp.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomVew extends View {
    Paint paint;
    public CustomVew(Context context,  AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setColor(0xffc0ff00);
        paint.setStyle(Paint.Style.FILL); //riempi tutta la finestra con il colore
        paint.setTextSize(55);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,getWidth(),getHeight(),paint);
        paint.setColor(0xffff0000);
        canvas.drawText("semplice view ",10,55,paint);

    }
}
