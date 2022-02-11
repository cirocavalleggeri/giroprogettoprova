package com.example.ciro.terzaapp.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class CustomCircularDrawArcPath extends View {
  private final static  int COLORE_DI_FONDO=0xF5FF4081;
  private final static  int COLORE_DI_PRIMO_PIANO=0xF5E7E7E7;
  private Paint paint;
  private  Paint backgroundPaint;
   private int angolo=278;
   private  int angolo360=360;
  private Path clip_path;
  private  boolean stato;
  private int diametro_clip_arco;

    public int getAngolomobile() {
        return angolomobile;
    }

    public void setAngolomobile(int angolomobile) {
        this.angolomobile = angolomobile;
    }

    private int angolomobile;

    public CustomCircularDrawArcPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setColor(COLORE_DI_PRIMO_PIANO);
        paint.setStyle(Paint.Style.FILL);

        backgroundPaint=new Paint();
        backgroundPaint.setColor(COLORE_DI_FONDO);
        backgroundPaint.setStyle(Paint.Style.FILL);
        stato=false;
        angolomobile=0;
        diametro_clip_arco=0;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

       // public void drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint)
        if(clip_path==null){
            clip_path=new Path();
        }

        int diametro_cerchio=0;

        if(getWidth()<getHeight()){diametro_cerchio=getWidth();
                                      }else {diametro_cerchio=getHeight();}
        diametro_clip_arco=(int)(diametro_cerchio*.61);
        int clip_X=(getWidth()-diametro_clip_arco)/2;
        int clip_Y=(getHeight()-diametro_clip_arco)/2;
        clip_path.addArc(clip_X,clip_Y,clip_X+diametro_clip_arco,clip_Y+diametro_clip_arco,0,360);


        int margine_orizzontale=(getWidth()-diametro_cerchio)/2;
        int margine_verticale=(getHeight()-diametro_cerchio)/2;
        canvas.drawArc(margine_orizzontale,margine_verticale,margine_orizzontale+diametro_cerchio,margine_verticale+diametro_cerchio,0,angolo360,true,backgroundPaint);
        canvas.drawArc(margine_orizzontale,margine_verticale,margine_orizzontale+diametro_cerchio,margine_verticale+diametro_cerchio,0,angolo,true,paint);






   }
}
