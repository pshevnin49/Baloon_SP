package com.example.sp_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class MenuView extends SurfaceView implements Runnable {

    static final long FPS = 50;
    private boolean running = false;

    private Thread menuThread = null;
    private Context context;


    List<Cloud> clouds = new ArrayList<>();

    int widthWindow = StartActivity.display.getWidth();
    int heightWindow = StartActivity.display.getHeight();


    public MenuView(Context context) {
        super(context);

        menuThread = new Thread(this);
        this.context = context;

        getHolder().addCallback(new SurfaceHolder.Callback() {

            public void surfaceDestroyed(SurfaceHolder holder) {

                boolean retry = true;

                setRunning(false);

                while (retry) {

                    try {

                        menuThread.join();

                        retry = false;

                    } catch (InterruptedException e) {
                    }

                }

            }

            public void surfaceCreated(SurfaceHolder holder) {

                createClouds();
                setRunning(true);

                menuThread.start();

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
                    menuThread.sleep(sleepTime);
                else
                    menuThread.sleep(10);
            } catch (Exception e) {

            }

        }

    }



    private void createClouds(){
        clouds.add(new Cloud(getResources(), context, widthWindow, heightWindow, 60, 50, true));
        clouds.add(new Cloud(getResources(), context, widthWindow, heightWindow, 200, 200, false));
        clouds.add(new Cloud(getResources(), context, widthWindow, heightWindow, 400, 300, true));
        clouds.add(new Cloud(getResources(), context, widthWindow, heightWindow, 500, 400, false));
        clouds.add(new Cloud(getResources(), context, widthWindow, heightWindow, 100, 500, false));
        clouds.add(new Cloud(getResources(), context, widthWindow, heightWindow, 800, 700, true));
        clouds.add(new Cloud(getResources(), context, widthWindow, heightWindow, 500, 800, false));
        clouds.add(new Cloud(getResources(), context, widthWindow, heightWindow, 60, 900, false));
        clouds.add(new Cloud(getResources(), context, widthWindow, heightWindow, 800, 1000, true));
        clouds.add(new Cloud(getResources(), context, widthWindow, heightWindow, 0, 1200, false));
        clouds.add(new Cloud(getResources(), context, widthWindow, heightWindow, 600, 1300, true));
        clouds.add(new Cloud(getResources(), context, widthWindow, heightWindow, 0, 1500, false));
        clouds.add(new Cloud(getResources(), context, widthWindow, heightWindow, 600, 1800, true));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.parseColor("#9ba7cf"));

        for(Cloud cloud: clouds){
            cloud.onDraw(canvas);
        }


    }
}
