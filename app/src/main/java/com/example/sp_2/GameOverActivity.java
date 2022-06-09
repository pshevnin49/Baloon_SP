package com.example.sp_2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends Activity implements OnClickListener {
    public static int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.game_over);
        TextView restartView = (TextView)findViewById(R.id.restart);
        restartView.setOnClickListener(this);

        TextView gameOverScoreView = (TextView)findViewById(R.id.gameOverScore);
        gameOverScoreView.setText("Score: " + score);
        restartView.setOnClickListener(this);

        TextView quitView = (TextView)findViewById(R.id.gameOverQuit);
        quitView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.restart: {
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                finish();
                score = 0;
            }break;

            case R.id.gameOverQuit: {
                finish();
                score = 0;
            }break;

            default:
                break;
        }
    }


}
