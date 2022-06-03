package com.example.sp_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class MenuView extends SurfaceView implements Runnable {

    static final int FPS = 50;

    private final static int MAX_FRAME_SKIPS = 5;
    private final static int FRAME_PERIOD = 1000/FPS;

    private boolean running = false;
    private Canvas canvas;
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

        long startTime;
        long timeDiff;
        long sleepTime;
        int skippedFrames;

        sleepTime=0;

        while (running) {

            canvas = null;

            try {

                canvas = this.getHolder().lockCanvas();
                synchronized (this.getHolder()) {
                    startTime = System.currentTimeMillis();
                    skippedFrames = 0;

                    if (canvas != null) {
                        this.update();
                        this.onDraw(canvas);
                    }
                    timeDiff = System.currentTimeMillis() - startTime;
                    sleepTime = (int) (FRAME_PERIOD - timeDiff);

                    if (sleepTime > 0){
                        try {
                            menuThread.sleep(sleepTime);
                        } catch (Exception e) {

                        }
                    }

                    while(sleepTime < 0 && skippedFrames < MAX_FRAME_SKIPS){
                        this.update();
                        sleepTime += FRAME_PERIOD;
                        skippedFrames++;
                    }
                }

            } finally {

                if (canvas != null) {
                    this.getHolder().unlockCanvasAndPost(canvas);
                }

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

    public void update(){
        for(Cloud cloud: clouds){
            cloud.update();
        }
    }
}
