package com.example.ciro.terzaapp.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CustomVewATMOST extends View {
    Paint paint;
    int DEFAULT_SIZE=1500;
    public CustomVewATMOST(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setColor(0xffc0ffff);
        paint.setStyle(Paint.Style.FILL); //riempi tutta la finestra con il colore
        paint.setTextSize(55);


    }
    private static int getMeasurementSize(int measureSpec, int defaultSize) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch(mode) {
            // la misura è data o è decisa dal parente
            case MeasureSpec.EXACTLY:
                return size;
            // in questo caso abbiamo più flessibilità ad esempio matchparent o wrapcontent
            case MeasureSpec.AT_MOST:
                return Math.min(defaultSize, size);

            case MeasureSpec.UNSPECIFIED:
            default:
                return defaultSize;
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        Log.d("VIEW","larghezza pixel :"+MeasureSpec.toString(widthMeasureSpec)+" altezza:"+MeasureSpec.toString(heightMeasureSpec));

        int width = getMeasurementSize(widthMeasureSpec, DEFAULT_SIZE);
        int height = getMeasurementSize(heightMeasureSpec, DEFAULT_SIZE);
        Log.d("VIEW","larghezza pixel :"+width+" altezza pixel:"+height);
        Log.d("VIEW","larghezza dp :"+ pixelsToDp(width)+" altezza dp:"+pixelsToDp(height));

        setMeasuredDimension(width, height);


    }
    public final int dpToPixels(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density + 0.5);
    }

    public final int pixelsToDp(int dp) {
        return (int) (dp / getResources().getDisplayMetrics().density + 0.5);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);





        canvas.drawRect(0,0,getWidth(),getHeight(),paint);
        paint.setColor(0xff00f3c0);
        canvas.drawText("MeasureSpec.AT_MOST perchè l'altezza ",10,55,paint);

        canvas.drawText(" è wrapcontent",10,110,paint);
    }

}
