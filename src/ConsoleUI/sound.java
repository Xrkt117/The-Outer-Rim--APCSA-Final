package ConsoleUI;

import javax.sound.sampled.*;
import java.io.File;

public class sound {
    public static void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            if (!soundFile.exists()) {
                soundFile = new File("src/" + filePath);
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
        }
    }

    public static void sfxPrint() {
        playSound("Sounds/printSound.wav");
    }
    
    public static void sfxContinue() {
        playSound("Sounds/continue.wav");
    }

    public static void sfxError() {
        playSound("Sounds/error.wav");
    }

    public static void sfxReveal() {
        playSound("Sounds/reveal.wav");
    }

    public static void sfxFlush() {
        playSound("Sounds/flush.wav");
    }

}
