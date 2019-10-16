package com.example.app3;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Canvas;

public class customView extends View {
    public customView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        Rect rect = new Rect();
        rect.left = 0;
        rect.right = getWidth();
        rect.top = 0;
        rect.bottom = getHeight();

        canvas.drawRect(rect, paint);
    }
}
