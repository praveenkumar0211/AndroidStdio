package com.example.sound_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.media.AudioManager;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import static android.widget.SeekBar.*;


public class MainActivity extends AppCompatActivity {
private MediaPlayer mediaPlayer;
    MediaPlayer mplayer;
    AudioManager audioManager;
public void PlayAudio(View view) {

    mplayer.start();
}
public void PauseAudio(View view) {
    mplayer.pause();
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mplayer=MediaPlayer.create(this,R.raw.vaathi);
        audioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int maxVolume= audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume= audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volumeControl=(SeekBar)findViewById(R.id.seekBar);
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curVolume);
        volumeControl.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }
        });
final SeekBar scruber = (SeekBar)findViewById(R.id.scuber);
scruber.setMax(mplayer.getDuration());
new Timer().scheduleAtFixedRate(new TimerTask() {
    @Override
    public void run() {
        scruber.setProgress(mplayer.getCurrentPosition());

    }
},0,100);
scruber.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.i("Scruber value",Integer.toString(progress));
        mplayer.seekTo(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
});
    }
}
