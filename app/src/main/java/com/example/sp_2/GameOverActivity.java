package com.example.sp_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        TextView restartView = (TextView)findViewById(R.id.restart);
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
                System.out.println("gameover Finish");
                finish();
            }break;

            case R.id.gameOverQuit: {
                finish();
            }break;

            default:
                break;
        }
    }
}
