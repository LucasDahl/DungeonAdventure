package View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public class MusicPlayer implements Runnable{
    private String myMusicLocation;
    private transient Thread myMusicThread;


//    public MusicPlayer(String theMusicPath) {
//        myMusicLocation = theMusicPath;
//    }
    public MusicPlayer() {
        // yes it's empty constructor
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

            } else {
                System.out.println("Cannot find file");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void playVictoryMusic() {
        String filepath = "DungeonAdventure/music/Victory.wav";
        myMusicLocation = filepath;
        playMusic(myMusicLocation);
        run();
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
//        String filepath = "DungeonAdventure/music/Victory.wav";
//        MusicPlayer musicPlayer = new MusicPlayer(filepath);
//        musicPlayer.run();
        MusicPlayer musicPlayer = new MusicPlayer(); // I only need one!
        musicPlayer.playVictoryMusic();
    }
}
