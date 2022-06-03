package com.example.sp_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameView extends SurfaceView implements Runnable {

    static final long FPS = 60;
    private boolean running = false;
    private final int PLATFORM_INTERVAL = 90;
    private int platform_time;

    private Thread gameThread = null;
    private Baloon baloon;
    private Queue<Platform> platforms = new LinkedList<>();
    private Context context;


    int widthWindow = MainActivity.display.getWidth();
    int heightWindow = MainActivity.display.getHeight();


    public GameView(Context context) {
        super(context);
        this.context = context;

        gameThread = new Thread(this);
        getHolder().addCallback(new SurfaceHolder.Callback() {

            public void surfaceDestroyed(SurfaceHolder holder) {

                boolean retry = true;

                setRunning(false);

                while (retry) {

                    try {

                        gameThread.join();
                        retry = false;

                    } catch (InterruptedException e) {

                    }

                }

            }
            public void surfaceCreated(SurfaceHolder holder) {
                baloon = new Baloon(getResources(), context, widthWindow, heightWindow);
                setRunning(true);
                //gameThread.start();
            }

            public void surfaceChanged(SurfaceHolder holder, int format,

                                       int width, int height) {
            }

        });

    }

    public void setRunning(boolean running) {
        this.running = running;

        if(running){
            gameThread = new Thread(this);
            gameThread.start();
        }
    }



    public void startFromPause(){
        this.running = true;
        gameThread.start();
    }

    @Override
    public void run() {

        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;

        while (running) {

            Canvas c = null;
            startTime = System.currentTimeMillis();

            try {
                c = this.getHolder().lockCanvas();
                synchronized (this.getHolder()) {
                    // Pridana kontrola, aby nehazelo chybu pri tlacitku BACK
                    if (c != null) {
                        this.onDraw(c);
                        this.isCollision();
                        this.newPlatformGeneration();
                    }
                }

            } finally {
                if (c != null) {
                     this.getHolder().unlockCanvasAndPost(c);
                }
            }

            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);

            try {
                if (sleepTime > 0)
                    gameThread.sleep(sleepTime);
                else
                    gameThread.sleep(10);
            } catch (Exception e) {

            }
        }

    }

    public void newPlatformGeneration(){
        if(platform_time >= PLATFORM_INTERVAL){
            Platform platform = new Platform(getResources(), context, widthWindow, heightWindow);
            platforms.offer(platform);
            platform_time = 0;
        }else{
            platform_time++;
        }
        if(platforms.size() > 25){
            platforms.remove();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#9ba7cf"));
        for(Platform platform: platforms){
            platform.onDraw(canvas);
        }
        baloon.onDraw(canvas);
    }

    private boolean isCollision(){
        for(Platform platform: platforms){
            if(platform.isCollision(baloon.getX(), baloon.getY(), baloon.getWidth(), baloon.getHeight())){
                return true;
            }
        }
        return false;
    }
}
