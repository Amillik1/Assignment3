package com.example.app3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView image = findViewById(R.id.image);
        //Animation animation = new TranslateAnimation(0, 500, 0, 0);
        //animation.setDuration(1000);
        //image.startAnimation(animation);


        float dx = 10;
        float dy = 5;
        float maxx = 500;
        float maxy = 500;
        float minx = 0;
        float miny = 0;
        float currx = 0;
        float curry = 0;

        while (true){
            //run through and change the values and call animation
            if (currx >= maxx || currx <= minx){
                dx = -dx;
            }
            if (curry >= maxy || curry <= miny){
                dy = -dy;
            }

            Animation animation = new TranslateAnimation(currx, currx+dx, curry, curry+dy);
            animation.setDuration(100);
            currx += dx;
            curry += dy;

            image.startAnimation(animation);

        }
    }
}
