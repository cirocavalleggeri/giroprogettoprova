package com.example.ciro.terzaapp.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CircularActivityIndicatorOneTouchDrag extends View {
    private static final int DEFAULT_FG_COLOR = 0xffff0000;
    private static final int DEFAULT_BG_COLOR = 0xffa0a0a0;
    private static final int PRESSED_FG_COLOR = 0xff0000ff;

    private static final String TAG = "CIRC";
    private Paint backgroundPaint;
    private Paint foregroundPaint;
    private int selectedAngle;
    private Path clipPath;
    Boolean pressed;

    public CircularActivityIndicatorOneTouchDrag(Context context, AttributeSet
            attributeSet) {
        super(context, attributeSet);
        backgroundPaint = new Paint();
        backgroundPaint.setColor(DEFAULT_BG_COLOR);
        backgroundPaint.setStyle(Paint.Style.FILL);
        foregroundPaint = new Paint();
        foregroundPaint.setColor(DEFAULT_FG_COLOR);
        foregroundPaint.setStyle(Paint.Style.FILL);
        selectedAngle = 280;
        pressed = false;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        if (pressed) {
            foregroundPaint.setColor(PRESSED_FG_COLOR);
        } else {
            foregroundPaint.setColor(DEFAULT_FG_COLOR);
        }
        int circleSize = getWidth();
        if (getHeight() < circleSize) circleSize = getHeight();
        int horMargin = (getWidth() - circleSize) / 2;
        int verMargin = (getHeight() - circleSize) / 2;
        // create a clipPath the first time
        if (clipPath == null) {
            int clipWidth = (int) (circleSize * 0.75);
            int clipX = (getWidth() - clipWidth) / 2;
            int clipY = (getHeight() - clipWidth) / 2;
            clipPath = new Path();
            clipPath.addArc(
                    clipX,
                    clipY,
                    clipX + clipWidth,
                    clipY + clipWidth,
                    0, 360);
        }
        canvas.clipPath(clipPath, Region.Op.DIFFERENCE);
        canvas.save();
        canvas.rotate(-90, getWidth() / 2, getHeight() / 2);
        canvas.drawArc(
                horMargin,
                verMargin,
                horMargin + circleSize,
                verMargin + circleSize,
                0, 360, true, backgroundPaint);
        canvas.drawArc(
                horMargin,
                verMargin,
                horMargin + circleSize,
                verMargin + circleSize,
                0, selectedAngle, true, foregroundPaint);
        canvas.restore();
    }

    /* @Override
     public boolean onTouchEvent(MotionEvent event) {
         Log.d(TAG, "touch: " + event);
         return super.onTouchEvent(event);
     }*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                selectedAngle = computeAngle(event.getX(), event.getY());
                cambia_stato_pressed(true);
                return true;
            case MotionEvent.ACTION_UP:
                cambia_stato_pressed(false);
                return true;
            case MotionEvent.ACTION_MOVE:
                selectedAngle = computeAngle(event.getX(), event.getY());
                invalidate();
                return true;
            default:
                return false;
        }
    }


    private void cambia_stato_pressed(Boolean premuto) {
        this.pressed = premuto;
    }


    private int computeAngle(float x, float y) {
        x -= getWidth() / 2;
        y -= getHeight() / 2;
        int angle = (int) (180.0 * Math.atan2(y, x) / Math.PI) + 90;
        return (angle > 0) ? angle : 360 + angle;
    }

}