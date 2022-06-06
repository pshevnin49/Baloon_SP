package com.example.sp_2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Queue;

public class GameView extends SurfaceView implements Runnable {

    static final long FPS = 35;
    private double speed = 6;
    private int platform_interval = 90;

    private final int SCORE_INTERVAL = 20;
    private final int SPEED_INTERVAL = 90;

    private boolean running = false;

    private int platform_time = 0;
    private int score_time = 0;
    private  int speed_time = 0;

    private long ticksPS = 1000/FPS;
    private int score = 0;

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

    @Override
    public void run() {

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
                        platform_interval = (int)(630/speed);
                        this.scoreIncrement();
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
        if(platform_time >= platform_interval){
            baloon.speedIncrement();
            Platform platform = new Platform(getResources(), context, widthWindow, heightWindow);
            platforms.offer(platform);
            platform_time = 0;

        }else{
            platform_time++;
        }
        if(platforms.size() > 5){
            platforms.remove();

        }
    }

    public void scoreIncrement(){
        if(score_time >= SCORE_INTERVAL){
            score++;
            score_time = 0;
        }
        else{
            score_time++;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#9ba7cf"));
        speed += 0.01;

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(80);
        String scoreString = Integer.toString(score);

        canvas.drawText(scoreString, widthWindow - (scoreString.length()*60), 100, paint);

        for(Platform platform: platforms){
            platform.onDraw(canvas);
            platform.setSpeed(speed);
        }
        baloon.onDraw(canvas);
    }

    private boolean isCollision(){
        for(Platform platform: platforms){
            if(platform.isCollision(baloon.getX() + 20, baloon.getY() + 10, baloon.getWidth() - 20, baloon.getHeight() - 20)){
                this.gameOver();
                return true;
            }
        }
        return false;
    }

    private void gameOver(){
        MainActivity.getInstance().gameOver(score);
        baloon.setGameOver(true);
        running = false;
    }


}
