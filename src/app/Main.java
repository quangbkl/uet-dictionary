package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../graphical/panes/container.fxml"));
        primaryStage.setTitle("Dictionary BKL");
        primaryStage.setScene(new Scene(root, 720, 480));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}