package com.example.sp_2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.widget.TextView;

public class ScoreActivity extends Activity {

    public static Display display = null;
    public static final String APP_PREFERENCES = "gameScore";
    public static final String APP_PREFERENCES_SCORE = "score";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        display = getWindowManager().getDefaultDisplay();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.score);
        TextView scoreText = (TextView)findViewById(R.id.score);

        SharedPreferences scorePreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        int actuallScore = scorePreferences.getInt(APP_PREFERENCES_SCORE, 0);
        scoreText.setText("Height score: " + actuallScore);
    }
}
