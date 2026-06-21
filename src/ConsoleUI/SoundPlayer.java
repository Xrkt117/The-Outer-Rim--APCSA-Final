package ConsoleUI;

import javax.sound.sampled.*;
import java.io.File;

public class SoundPlayer {
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
}
