package com.example.android.onesongplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.someday);
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        Button play = findViewById(R.id.play_button);
        Button pause = findViewById(R.id.pause_button);
        Button louder = findViewById(R.id.louder_button);
        Button quieter = findViewById(R.id.quieter_button);
        Button playFromMiddle = findViewById(R.id.middle_button);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        louder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                if (currentVolume < maxVolume){
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            currentVolume + 1, AudioManager.FLAG_PLAY_SOUND);
                }
            }
        });

        quieter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                if (currentVolume > 0){
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            currentVolume - 1, AudioManager.FLAG_PLAY_SOUND);
                }
            }
        });

        playFromMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(mediaPlayer.getDuration()/2);
                mediaPlayer.start();
            }
        });
    }
}