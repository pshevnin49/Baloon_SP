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
    private boolean gameOver = false;

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

        speed = 7;
        size = 200;

        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.baloon);
        this.bmp = Bitmap.createScaledBitmap(
                cBitmap, (int)(size), (int)(size*1.59), false);

        this.width = bmp.getWidth();
        this.height = bmp.getHeight();

        x = (windowWidth/2) - bmp.getWidth()/2;
        y = windowHeight - (int)(height * 1.5);

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

        if(!gameOver){
            update();
            canvas.drawBitmap(bmp, x, y, null );
        }

    }

    public void speedIncrement(){
        speed += 0.09;
    }

    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
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
