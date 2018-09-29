package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../graphical/graphical_dictionary.fxml"));
        primaryStage.setTitle("Dictionary BKL");
        primaryStage.setScene(new Scene(root, 720, 480));
        primaryStage.show();

//        String path = getClass().getResource("../data/musics/translate_tts.mp3").toString();
//        Media media = new Media(path);
////        Media media = new Media("https://translate.google.com/translate_tts?ie=UTF-8&tl=en&client=tw-ob&q=pronounce");
//        MediaPlayer player = new MediaPlayer(media);
//        player.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}