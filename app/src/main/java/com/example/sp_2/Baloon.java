package com.example.sp_2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;


public class Baloon {

    private int x = 0;
    private int y = 0;
    private double speed;
    private int xSize;
    private int ySize;
    protected float size;
    private int width;
    private int height;

    private int windowWidth;
    private int windowHeight;

    private Resources resources = null;
    private Bitmap bmp;


    public Baloon(Resources resources, Context context, int windowWidth, int windowHeight){

        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        speed = 5;
        size = 250;

        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.baloon3);
        this.bmp = Bitmap.createScaledBitmap(
                cBitmap, (int)(size), (int)(size), false);

        this.width = bmp.getWidth();
        this.height = bmp.getHeight();

        x = (windowWidth/2) - bmp.getWidth()/2;
        y = windowHeight - height * 2;

    }

    public void update(){
        if(MainActivity.leftPressed && x >= 0){
            x-=speed;
        }
        else if(MainActivity.rightPressed && x <= windowWidth - width){
            x+=speed;
        }
    }

    public void onDraw(Canvas canvas){

        update();

        //Rect src = new Rect(x, y, x + width, y + height);

        canvas.drawBitmap(bmp, x, y, null );

    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }




}
