package objects3D.models;

import org.lwjgl.Sys;

import javax.sound.sampled.*;
import java.io.File;

public class AudioPlayer implements Runnable{
    private final String path;
    public boolean isPlayed;
    public static int LOOP_MODE = 1;
    public static int DEFAULT_MODE = 0;
    private final int playMode;
    private Clip clip;
    private boolean audioStop;
    public AudioPlayer(String path , int playMode){
        this.path = path;
        this.isPlayed = false;
        this.playMode = playMode;
        clip = null;
        audioStop = false;
    }
    private void play(){
        try{
            File audioFile = new File("res/audio/" + path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

            //creat edit object
            clip = AudioSystem.getClip();

            clip.open(audioInputStream);

            clip.start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void checkStop(){
        if(clip != null){
            /*clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {

                        *//*clip.setFramePosition(0);
                        clip.start();
                        *//*
                    }
                }
            });*/
            if(!clip.isRunning() && playMode == LOOP_MODE && !audioStop){
                clip.setFramePosition(0);
                clip.start();
                //isPlayed = true;
            } else {
                return;
            }
            //System.out.println(clip.isRunning());
        }

    }
    public void stop(){
        if(clip!=null){
            if(!audioStop){
                //only say it once
                System.out.println(path + " audio stop");
            }
            clip.stop();
            audioStop = true;
        }
    }
    public void reset(){
        clip.setFramePosition(0);
    }
    public void start(){
        clip.start();
    }

    //multiThread to avoid block in main thread
    @Override
    public void run() {
        while(true){
            if(this.isPlayed){
                checkStop();
            }else {
                this.isPlayed = true;
                System.out.println("PlayAudio: " + path);
                play();
                //System.out.println(this.isPlayed);
            }
        }
    }
}
