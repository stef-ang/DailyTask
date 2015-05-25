package com.stef_developer.dailytask.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;


/**
 * Created by wik on 5/14/15.
 */

public class TaskIcon extends View {

    //ditentukan constructor
    private int days;
    private float progress;
    //ditentukan constructor (opsional)
    private int radius;
    //dihitung sendiri
    private int color;
    private int arcWidth;
    private float textSize;

    private Paint ovalPaint;
    private Paint textPaint;
    private Paint arcPaint;

    //fungsi bantuan untuk ngerubah dp jadi px
    private int dp2px(int dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int)(dp * scale + 0.5f);
    }

    private int sp2px(int sp) {
        final float scaledDensity = getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scaledDensity);
    }

    public int getColor() {
        if (color == -1) //undefined
        {
            float[] hsv;
            if (days <= 7) {
                hsv = new float[]{days * 10.0f, 1.0f, 1.0f};
            }
            else if (days <= 14) {
                hsv = new float[]{120.0f, 1.0f, 80.0f - (40.0f/6.0f * days - 8)};
            }
            else {
                hsv = new float[]{120.0f, 0.8f, 0.25f};
            }
            color = Color.HSVToColor(hsv);
        }
        return color;
    }

    public TaskIcon(Context context, int days, float progress) {
        super(context);
        this.days = days;
        this.progress = (float)progress;
        this.color = -1;
        radius = dp2px(40);
        textSize = sp2px(24);
        arcWidth = dp2px(4);
        init_paints();
    }

    public TaskIcon(Context context, int days, float progress, int  radius) {
        super(context);
        this.days = days;
        this.progress = progress;
        this.color = -1;
        this.radius = dp2px(radius);
        textSize = radius/5*2;
        arcWidth = dp2px(Math.max(2, radius/12));
        init_paints();
    }

    private void init_paints() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Typeface roboto = Typeface.create("Roboto", Typeface.NORMAL);
        textPaint.setTypeface(roboto);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        ovalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ovalPaint.setColor(getColor());
        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setColor(Color.parseColor("#5599ff"));
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(arcWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Rect textBounds1 = new Rect();
        Rect textBounds2 = new Rect();
        float spacing = 1.5f*arcWidth;
        RectF ovalRect = new RectF(spacing, spacing, arcWidth + 2*radius - spacing, arcWidth + 2*radius - spacing);
        RectF arcRect = new RectF(arcWidth/2f, arcWidth/2f, arcWidth/2f + 2f*radius, arcWidth/2f + 2f*radius);
        canvas.drawOval(ovalRect, ovalPaint);
        textPaint.setTextSize(textSize);
        textPaint.getTextBounds("0123456789", 0, 10, textBounds1);
        canvas.drawText(String.valueOf(days), radius, radius, textPaint);
        textPaint.setTextSize(textSize * 0.5f);
        textPaint.getTextBounds("days left", 0, 9, textBounds2);
        canvas.drawText("days left", radius, radius + textBounds1.height()/2f + textBounds2.height()/2f, textPaint);
	if (progress > 0.00001f) {        
	    canvas.drawArc(arcRect, -90f, progress * 450.0f - 90.0f, false, arcPaint);
	}	
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 2*(radius+arcWidth);
        int height = 2*(radius+arcWidth);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=  MeasureSpec.getSize(heightMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                width = Math.min(width, widthSize);
                break;
        }
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                height = Math.min(height, heightSize);
                break;
        }
        setMeasuredDimension(width, height);
    }

}
