package com.example.audiodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer  = MediaPlayer.create(this, R.raw.we_are_not_gonna_take_it);
        SeekBar volumeControl =  (SeekBar) findViewById(R.id.seekBarVolume);
        SeekBar scrubber = (SeekBar) findViewById(R.id.seekBarScrubber);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);


        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        Log.i("Max volume", Integer.toString(maxVolume));
        int currentVolum = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);


        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(currentVolum);

        scrubber.setMax(mediaPlayer.getDuration());

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("onProgressChanged: ",Integer.toString(i));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mediaPlayer.seekTo(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
    public void play(View view){

      mediaPlayer.start();
    }
    public void pause(View view){
        mediaPlayer.pause();

    }
}