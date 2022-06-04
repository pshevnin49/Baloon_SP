package com.example.sp_2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.widget.TextView;

public class ScoreActivity extends Activity {

    public static Display display = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        display = getWindowManager().getDefaultDisplay();
        setContentView(R.layout.score);
        TextView scoreText = (TextView)findViewById(R.id.score);
        //scoreText.setText("Height score: " + Score.getHightScore());
    }
}
