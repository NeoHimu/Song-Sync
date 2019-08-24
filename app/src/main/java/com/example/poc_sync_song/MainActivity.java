package com.example.poc_sync_song;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    EditText etHour, etMinute, etSecond, etMillisecond, etDate;
    String date_input;
    String hour, minute, second, millisecond;
    Button setTime, pauseSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etHour = findViewById(R.id.idHour);
        etMinute = findViewById(R.id.idMinute);
        etSecond = findViewById(R.id.idSecond);
        etMillisecond = findViewById(R.id.idMillisecond);
        etDate = findViewById(R.id.idDate);
        setTime = findViewById(R.id.idSetTime);
        pauseSong = findViewById(R.id.idPause);

        /*
        * Calendar cal = Calendar.getInstance();
          int millisecond = cal.get(Calendar.MILLISECOND);
          int second = cal.get(Calendar.SECOND);
          int minute = cal.get(Calendar.MINUTE);
                //12 hour format
          //int hour = cal.get(Calendar.HOUR);
                //24 hour format
          int hourofday = cal.get(Calendar.HOUR_OF_DAY);
        * */

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hour = etHour.getText().toString();
                minute = etMinute.getText().toString();
                second = etSecond.getText().toString();
                millisecond = etMillisecond.getText().toString();
                date_input = etDate.getText().toString();
                //the Date and time at which you want to execute
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                Toast.makeText(MainActivity.this, date_input+" "+hour+":"+minute+":"+second+"."+millisecond, Toast.LENGTH_SHORT).show();
                Date date = new Date();

                try {
                    date = dateFormatter.parse(date_input+" "+hour+":"+minute+":"+second+"."+millisecond);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                //Now create the time and schedule it
                Timer timer = new Timer();

                //Use this if you want to execute it once
                timer.schedule(new MyTimeTask(), date);
            }
        });

        pauseSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp!=null)
                {
                    mp.pause();
                }
            }
        });

    }

    //The task which you want to execute
    private class MyTimeTask extends TimerTask
    {

        public void run()
        {
            //write your code here
            mp = new MediaPlayer();
            mp.reset();// stops any current playing song
            mp = MediaPlayer.create(getApplicationContext(), R.raw.song);
            mp.start(); // starting mediaplayer
        }
    }
}
