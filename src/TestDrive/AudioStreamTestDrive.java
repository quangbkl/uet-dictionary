package TestDrive;

import app.helper.AudioMedia;

public class AudioStreamTestDrive {
    public static void main(String[] args) throws Exception {
        AudioMedia am = new AudioMedia();
        am.playSound("src/data/translate_tts.mp3");
    }
}
