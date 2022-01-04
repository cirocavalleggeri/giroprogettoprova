package com.example.ciro.terzaapp.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.ciro.terzaapp.R;

public class CustomVewParametri extends View {
    Paint paint;
    int DEFAULT_SIZE=1500;
    String frase="frase di default";
    private static final int DEFAULT_FILL_COLOR = 0xffff0000;
    public CustomVewParametri(Context context, AttributeSet attrs) {
        super(context, attrs);


        int fillColor;


        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomViewParametrizzata, 0, 0);
        try {
            fillColor = ta.getColor(R.styleable.CustomViewParametrizzata_fillColor, DEFAULT_FILL_COLOR);
            frase=ta.getString(0);
        } finally {
            ta.recycle();
        }
        init(fillColor,frase);

    }

    private void init(int fillColor, String frase) {
        paint=new Paint();
        paint.setColor(0xffff0000);
        paint.setStyle(Paint.Style.FILL); //riempi tutta la finestra con il colore
        paint.setTextSize(55);
        setColore(fillColor);
        setFrase(frase);
    }

    public void setFrase(String frase) {
        this.frase=frase;
        invalidate();
    }

    public void setColore(int fillColor) {
        paint.setColor(fillColor);
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





        canvas.drawRect(150,300,300,400,paint);
       // paint.setColor(0xfff0f3c0);
        canvas.drawText(frase,10,55,paint);


    }

}
