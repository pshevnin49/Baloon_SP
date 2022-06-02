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

    private boolean direction;

    protected float size;
    private int width;
    private int height;

    private int windowWidth;
    private int windowHeight;

    private Resources resources = null;
    private Bitmap bmp;

    public Cloud (Resources resources, Context context, int windowWidth, int windowHeight, int x, int y, boolean direction){

        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        Random rand = new Random();

        this.direction = direction;

        speed = rand.nextInt(2);
        speed+=1;
        size = 300;

        Bitmap cBitmap = null;
        boolean cloudType = rand.nextBoolean();

        if(cloudType){
            cBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cloud0);
        }else{
            cBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cloud1);
        }


        this.bmp = Bitmap.createScaledBitmap(
                cBitmap, (int)(size*1.3), (int)(size), false);

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

        if(direction){
            x+=speed;
        }
        else {
            x-=speed;
        }

        if(x <= -width){
            direction = true;
        }
        else if(x >= windowWidth){
            direction = false;
        }
    }



}
