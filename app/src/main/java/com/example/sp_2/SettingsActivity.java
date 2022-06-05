package com.example.sp_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

public class SettingsActivity extends Activity implements View.OnClickListener {

    TextView settingsView = null;
    public static Display display = null;
    public static final String APP_PREFERENCES = "gameSettings";
    public static final String APP_PREFERENCES_SETTINGS = "settings";
    private SharedPreferences scorePreferences;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        display = getWindowManager().getDefaultDisplay();

        scorePreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        setContentView(R.layout.settings);

        SharedPreferences scorePreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        boolean actuallPosition = scorePreferences.getBoolean(APP_PREFERENCES_SETTINGS, true);

        settingsView = (TextView)findViewById(R.id.settings);
        settingsView.setOnClickListener(this);

        TextView settingsQuitView = (TextView)findViewById(R.id.settingsQuit);
        settingsQuitView.setOnClickListener(this);

        if(actuallPosition){
            settingsView.setText("Volume ON");
        }else{
            settingsView.setText("Volume OFF");
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.settings: {
                SharedPreferences.Editor editor = scorePreferences.edit();
                boolean settingsPosition = scorePreferences.getBoolean(APP_PREFERENCES_SETTINGS, true);
                editor.putBoolean(APP_PREFERENCES_SETTINGS, !settingsPosition);

                if(settingsPosition){
                    settingsView.setText("Volume ON");
                }else{
                    settingsView.setText("Volume OFF");
                }

                editor.apply();
            }break;

            case R.id.settingsQuit: {
                finish();
            }break;

            default:
                break;
        }
    }
}
