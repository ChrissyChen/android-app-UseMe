package project.csc780.xinlu.useme;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Xinlu Chen on 5/15/17.
 */

public class CompassView extends View implements Runnable {

    private Paint mTextPaint;
    private int screenWidth, screenHeight;
    private float dec = 0.0f;
    private String displayMessage = "North 0°";

    public CompassView(Context context) {
        this(context, null);
    }

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        screenWidth = ScreenUtil.getScreenWidth(context);
        screenHeight = ScreenUtil.getScreenHeight(context);
        init();
        new Thread(this).start();
    }

    private void init() {
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.GRAY);
        mTextPaint.setTextSize(65);
        mTextPaint.setStrokeWidth(3);
        mTextPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(displayMessage, screenWidth / 4 , screenHeight / 3, mTextPaint);
    }

    // update the degree of compass
    public void setDegree(float degree) {
        // set the sensitivity
        if(Math.abs(dec - degree) >= 2 ) {
            dec = degree;
            int range = 20;
            String degreeStr = String.valueOf(dec);


            if(dec > 360 - range && dec < 360 + range) {
                displayMessage = "North " + degreeStr + "°";
            }

            if(dec > 90 - range && dec < 90 + range) {
                displayMessage = "East " + degreeStr + "°";
            }

            if(dec > 180 - range && dec < 180 + range) {
                displayMessage = "South " + degreeStr + "°";
            }

            if(dec > 270 - range && dec < 270 + range) {
                displayMessage = "West " + degreeStr + "°";
            }

            if(dec > 45 - range && dec < 45 + range) {
                displayMessage = "NE " + degreeStr + "°";
            }

            if(dec > 135 - range && dec < 135 + range) {
                displayMessage = "SE " + degreeStr + "°";
            }

            if(dec > 225 - range && dec < 225 + range) {
                displayMessage = "SW " + degreeStr + "°";
            }

            if(dec > 315 - range && dec < 315 + range) {
                displayMessage = "NE " + degreeStr + "°";
            }
        }
    }


    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            postInvalidate();
        }
    }

}


