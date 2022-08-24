package View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class PlayMusic implements Runnable{
    private String myMusicLocation;
    private transient Thread myMusicThread;


    public PlayMusic(String theMusicPath) {
        myMusicLocation = theMusicPath;
    }
    public void playMusic (String theFilePath) {
        try {
            File musicPath = new File(theFilePath);
            if(musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip(); // plays music
                clip.open(audioInput);
                clip.start();
                while (myMusicThread != null) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                System.out.println("Done looping music");
                //JOptionPane.showMessageDialog(null,"Press ok to stop playing");
            } else {
                System.out.println("Cannot find file");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void playVictoryMusic() {
        String filepath = "DungeonAdventure/music/Victory.wav";
        PlayMusic musicPlayer = new PlayMusic(filepath);
        musicPlayer.run();
    }

    @Override
    public void run() {
        if(myMusicThread == null) {
            myMusicThread = new Thread(this);
        }
        playMusic(myMusicLocation);
    }

    // testing main
    public static void main(String[] args) {
        String filepath = "DungeonAdventure/music/Victory.wav";
        PlayMusic musicPlayer = new PlayMusic(filepath);
        musicPlayer.run();
    }
}
