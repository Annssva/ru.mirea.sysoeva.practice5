package ru.mirea.sysoeva.controltask;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;

public class AudioPlayer extends Thread{
    private Context context;
    private int musicResId;
    private String filePath = null;


    public AudioPlayer(Context context, Integer musicResId){
        this.musicResId = musicResId;
        this.context = context;
    }

    public AudioPlayer(Context context, String filePath) {
        this.filePath = filePath;
        this.context = context;
    }

    @Override
    public void run() {
        MediaPlayer mediaPlayer = null;
        if (filePath != null){
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(filePath);
                mediaPlayer.setOnPreparedListener(MediaPlayer::start);
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mediaPlayer = MediaPlayer.create(context, musicResId);
            mediaPlayer.start();
        }
        mediaPlayer.setLooping(true);
        while (true){
            if (isInterrupted()) break;
        }
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
    }
}
