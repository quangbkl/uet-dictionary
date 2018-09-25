package app.helper;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.FileInputStream;
import java.io.InputStream;

public class AudioMedia extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //
    }

    public synchronized void playSound(final String audioFileName) {
        try {
            Clip clip = AudioSystem.getClip();
            InputStream inputStream = new FileInputStream(audioFileName);
            if (inputStream != null) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
                clip.open(audioInputStream);
                clip.start();
            } else {
                System.out.println("Input stream not valid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
