package app.helper;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioStream {
    final String baseUrl = "https://translate.google.com.vn/translate_tts?ie=UTF-8&q=test&tl=en&client=tw-ob";

    public void speak(String text) {
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(
                            this.getClass().getResource("<Path of relative sound file in src folder>"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
