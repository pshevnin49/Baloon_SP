package com.example.sp_2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StartActivity extends Activity implements OnClickListener {

    public static Display display = null;
    MenuView menuView = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        createBackground();
    }

    @Override
    public void onPause(){
        super.onPause();
        menuView.setRunning(false);
    }

    @Override
    public void onResume(){
        super.onResume();
        createBackground();
    }

    public void createBackground(){

        display = getWindowManager().getDefaultDisplay();
        setContentView(R.layout.menu);

        menuView = new MenuView(this);
        LinearLayout menuLayout = (LinearLayout) findViewById(R.id.menuLayout);
        menuLayout.addView(menuView);

        TextView startView = (TextView)findViewById(R.id.start);
        startView.setOnClickListener(this);

        TextView scoreView = (TextView)findViewById(R.id.score);
        scoreView.setOnClickListener(this);

        TextView quitView = (TextView)findViewById(R.id.quit);
        quitView.setOnClickListener(this);

        TextView settingsView = (TextView)findViewById(R.id.settings);
        settingsView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.start: {
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
            }break;

            case R.id.score: {
                Intent intent = new Intent();
                intent.setClass(this, ScoreActivity.class);
                startActivity(intent);
            }break;
            case R.id.settings: {
                Intent intent = new Intent();
                intent.setClass(this, SettingsActivity.class);
                startActivity(intent);
            }break;

            case R.id.quit: {
                finish();
            }break;

            default:
                break;
        }
    }
}
