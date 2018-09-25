package app.helper;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

public class AudioDictionaries extends Application {
    final String baseUrl = "https://translate.google.com.vn/translate_tts?ie=UTF-8&q=test&tl=en&client=tw-ob";

    public void speak(String text) {
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(
                            this.getClass().getResource("src/data/translate_tts.mp3"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void playSound(String ressource) {
        System.out.println("Try to play " + ressource);

        URL url = ClassLoader.getSystemResource(ressource);

        String path = null;

        if (url == null) {
            path = new File(ressource).toURI().toString();
        } else
            path = url.toString();

        System.out.println("path " + path);

        try {
            Media media = new Media(path);
            MediaPlayer mp = new MediaPlayer(media);
            mp.play();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        someGlobalVar.setInitialized(true);
    }
}
