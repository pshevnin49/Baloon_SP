package com.example.sp_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    static final long FPS = 50;
    private boolean running = false;

    private Thread gameThread = null;
    private Baloon baloon;


    int widthWindow = MainActivity.display.getWidth();
    int heightWindow = MainActivity.display.getHeight();


    public GameView(Context context) {
        super(context);

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

                setRunning(true);

                baloon = new Baloon(getResources(), context, widthWindow, heightWindow);

                gameThread.start();

            }

            public void surfaceChanged(SurfaceHolder holder, int format,

                                       int width, int height) {
            }

        });

    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {

        System.out.println("runing");

        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;

        while (running) {
            System.out.println("runing 2");
            Canvas c = null;
            startTime = System.currentTimeMillis();

            try {
                c = this.getHolder().lockCanvas();
                synchronized (this.getHolder()) {
                    // Pridana kontrola, aby nehazelo chybu pri tlacitku BACK
                    if (c != null) {
                        this.onDraw(c);
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

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#9ba7cf"));
        baloon.onDraw(canvas);
    }
}
