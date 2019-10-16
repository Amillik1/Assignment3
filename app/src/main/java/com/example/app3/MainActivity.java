package com.example.app3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    DvdView dvdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dvdView = new DvdView(this);
        setContentView(dvdView);

        //Animation animation = new TranslateAnimation(0, 500, 0, 0);
        //animation.setDuration(1000);
        //image.startAnimation(animation);
    }

        class DvdView extends SurfaceView implements Runnable{
            Thread dvdThread = null;
            ImageView dvd;
            SurfaceHolder ourHolder;
            Canvas canvas;
            Paint paint;
            RelativeLayout layout;
            float dx = 10;
            float dy = 5;
            float maxx = 500;
            float maxy = 500;
            float minx = 0;
            float miny = 0;
            float currx = 0;
            float curry = 0;
            boolean running = true;

            public DvdView(Context context){
                super(context);
                ourHolder = getHolder();
                paint = new Paint();
                layout = new RelativeLayout(context);
                dvd = new ImageView(context);
                dvd.setImageResource(R.drawable.dvd);
                dvd.setVisibility(VISIBLE);
                dvd.setScaleType(ImageView.ScaleType.FIT_XY);
                dvd.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
                layout.addView(dvd);
            }
            //REFERENCE: https://github.com/profmadden/csterdroids/blob/master/app/src/main/java/edu/binghamton/cs/csterdroids/MainActivity.java
            public void update(){
                //run through and change the values and call animation
                if (currx >= maxx || currx <= minx){
                    dx = -dx;
                }
                if (curry >= maxy || curry <= miny){
                    dy = -dy;
                }
                currx += dx;
                curry += dy;

            }
            public void draw(){
                if(ourHolder.getSurface().isValid()){
                    canvas = ourHolder.lockCanvas();
                    canvas.drawColor(Color.BLUE);

                    //DRAW IN HERE
                    currx = 100;
                    curry = 100;
                    dvd.setX(currx);
                    dvd.setY(curry);
                    dvd.draw(canvas);

                    ourHolder.unlockCanvasAndPost(canvas);
                }
            }

            @Override
            public void run(){
                while(running){
                    update();
                    draw();
                    try {
                        dvdThread.sleep(100);
                    }catch (InterruptedException e) {
                    }
                }
            }
            public void resume(){

                dvdThread = new Thread(this);
                dvdThread.start();
            }
        }
    @Override
    public void onResume(){
        super.onResume();
        dvdView.resume();
    }

}
