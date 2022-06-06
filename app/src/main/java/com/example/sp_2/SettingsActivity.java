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
    SharedPreferences.Editor editor = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        display = getWindowManager().getDefaultDisplay();

        scorePreferences = getSharedPreferences(APP_PREFERENCES, 0);

        setContentView(R.layout.settings);

        SharedPreferences scorePreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        boolean actuallPosition = scorePreferences.getBoolean(APP_PREFERENCES_SETTINGS, true);

        settingsView = (TextView)findViewById(R.id.settings);
        TextView settingsQuitView = (TextView)findViewById(R.id.settingsQuit);
        settingsQuitView.setOnClickListener(this);

        if(actuallPosition){
            settingsView.setText("Volume ON");
        }else{
            settingsView.setText("Volume OFF");
        }
        settingsView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.settings: {

                editor = scorePreferences.edit();
                boolean settingsPosition = scorePreferences.getBoolean(APP_PREFERENCES_SETTINGS, false);
                editor.putBoolean(APP_PREFERENCES_SETTINGS, !settingsPosition);
                editor.apply();
                settingsPosition = scorePreferences.getBoolean(APP_PREFERENCES_SETTINGS, false);

                if(settingsPosition){
                    System.out.println("ON");
                    settingsView.setText("Volume ON");
                }else{
                    System.out.println("OFF");
                    settingsView.setText("Volume OFF");
                }

            }break;

            case R.id.settingsQuit: {
                finish();
            }break;

            default:
                break;
        }
    }
}
