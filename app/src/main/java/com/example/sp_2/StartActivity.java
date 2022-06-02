package com.example.sp_2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StartActivity extends Activity implements OnClickListener {

    public static Display display = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        display = getWindowManager().getDefaultDisplay();
        //setContentView(new MenuView(this));

        setContentView(R.layout.menu);

        TextView startView = (TextView)findViewById(R.id.start);
        startView.setOnClickListener(this);

        TextView scoreView = (TextView)findViewById(R.id.score);
        scoreView.setOnClickListener(this);

        TextView quitView = (TextView)findViewById(R.id.quit);
        quitView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.start: {
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
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
