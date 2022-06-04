package com.example.sp_2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

public class Platform {

    private int x;
    private int y;
    private double speed;
    protected float sizeW;
    protected float sizeH;

    private int width;
    private int height;

    private int windowWidth;
    private int windowHeight;

    private Resources resources = null;
    private Bitmap bmp;

    public Platform (Resources resources, Context context, int windowWidth, int windowHeight){

        sizeW = 252;
        sizeH = 85;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        Random rand = new Random();

        x = rand.nextInt(windowWidth);
        //Aby platforma mohla o pul byt mimo displaje
        x = (int) (x-sizeW/2);
        y = -80;
        speed = 8;

        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.platform_x4);
        this.bmp = Bitmap.createScaledBitmap(
                cBitmap, (int)(sizeW), (int)(sizeH), false);


    }

    public void update(){
        y+=speed;
    }

    public void onDraw(Canvas canvas){
        update();
        canvas.drawBitmap(bmp, x, y, null );
    }

    public boolean isCollision(int baloonX, int baloonY, int baloonWidth, int baloonHeight){
        //System.out.println(!(((x+sizeW) < baloonX)||(x > (baloonX + baloonWidth))||((y + sizeH) < baloonY)||(y > (baloonY + baloonHeight))));
        return !(((x+sizeW) < baloonX)||(x > (baloonX + baloonWidth))||((y + sizeH) < baloonY)||(y > (baloonY + baloonHeight)));
    }


}
