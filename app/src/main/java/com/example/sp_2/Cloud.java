package com.example.sp_2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

public class Cloud {

    private int x = 0;
    private int y = 0;
    private double speed;

    protected float size;
    private int width;
    private int height;

    private int windowWidth;
    private int windowHeight;

    private Resources resources = null;
    private Bitmap bmp;

    public Cloud (Resources resources, Context context, int windowWidth, int windowHeight, int x, int y){

        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        Random rand = new Random();

        speed = rand.nextInt(3);
        size = 150;

        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cloud);
        this.bmp = Bitmap.createScaledBitmap(
                cBitmap, (int)(size), (int)(size), false);

        this.width = bmp.getWidth();
        this.height = bmp.getHeight();

        this.x = x;
        this.y = y;

    }

    public void onDraw(Canvas canvas){
        update();
        canvas.drawBitmap(bmp, x, y, null );
    }

    public void update(){
        if(x >= -width*2){
            x-=speed;
        }
        else if(MainActivity.rightPressed && x <= windowWidth - width){
            x+=speed;
        }
    }



}
