package ConsoleUI;

import javax.sound.sampled.*;
import java.io.File;


public class sound {
    private static Clip musicClip;
    private static boolean soundDisabled;
    private static boolean warnedOnce;

    public static void playSound(String filePath) {
        if (soundDisabled) {
            return;
        }
        try {
            File soundFile = getSoundFile(filePath);
            if (soundFile == null) {
                return;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            disableSound(e);
        }
    }

    public static void playLoop(String filePath) {
        if (soundDisabled) {
            return;
        }
        try {
            File soundFile = getSoundFile(filePath);
            if (soundFile == null) {
                return;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
            if (musicClip != null && musicClip.isOpen()) { musicClip.stop(); musicClip.close(); }
            musicClip = AudioSystem.getClip();
            musicClip.open(audio);
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
            musicClip.start();
        } catch (Exception e) {
            disableSound(e);
        }
    }

    private static File getSoundFile(String filePath) {
        File soundFile = new File(filePath);
        if (!soundFile.exists()) {
            soundFile = new File("src/" + filePath);
        }
        if (!soundFile.exists()) {
            return null;
        }
        return soundFile;
    }

    private static void disableSound(Exception e) {
        soundDisabled = true;
        if (!warnedOnce) {
            System.err.println("Audio disabled: " + e.getMessage());
            warnedOnce = true;
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

    public static void sfxTransaction() {
        playSound("Sounds/transaction.wav");
    }

}
