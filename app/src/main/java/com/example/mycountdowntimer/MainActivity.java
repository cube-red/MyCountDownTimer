package com.example.mycountdowntimer;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    int num;
    EditText number;
    MediaPlayer mediaPlayer;
    private Button startButton;
    private Button resetButton;
    private Button pauseButton;
    private CountDownTimer countDownTimer;
    private boolean isTimerRunning;
    private TextView done;
    private int timeLeft;
    public void myFun(View view){
        number = (EditText)findViewById(R.id.number);
        num = Integer.valueOf(number.getText().toString()) * 1000;//timer time
        done = findViewById(R.id.done);
        timeLeft = num;
        countDownTimer = new CountDownTimer(num,1000){
            public void onTick(long timetogetdone){
                timetogetdone = timeLeft;
                updateCountDownText();
                //number.setText("Time Left "+ String.valueOf(timetogetdone/1000));
            }
            public void onFinish(){
                done.setText("DONE");
                mediaPlayer.start();
                isTimerRunning = false;
            }
        }.start();
        isTimerRunning = true;
    }
    public void stop(View view){
        if(mediaPlayer.isPlaying())
            mediaPlayer.stop();
        //number = (EditText)findViewById(R.id.number);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this,R.raw.bell);
        pauseButton = findViewById(R.id.pauseButton);
        resetButton = findViewById(R.id.resetButton);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTimerRunning)
                    pauseTimer();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        //updateCountDownText();
    }
    private void pauseTimer(){
        countDownTimer.cancel();
        isTimerRunning = false;
    }

    private void resetTimer(){
        timeLeft = num;
        updateCountDownText();
    }

    private void updateCountDownText(){
        int minutes =   (int)(timeLeft/1000)/60;
        int seconds = (int)(timeLeft/1000)%60;
        String timeLeftFformatted = String.format(Locale.getDefault(),"%02d%02d",minutes,seconds);
        done.setText(timeLeftFformatted);
    }
}
