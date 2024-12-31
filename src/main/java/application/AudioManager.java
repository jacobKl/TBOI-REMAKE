package application;

import javafx.concurrent.Task;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AudioManager {

    private static AudioManager instance;

    private final Map<String, Media> soundMap = new HashMap<>();

    private AudioManager() {
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    public void playSound(String filename) {
        Media sound = this.soundMap.get(filename);

        if (sound == null) {
            this.loadSoundAsync(filename);
        }

        this.playMedia(sound);
    }

    private void loadSoundAsync(String filename) {
        Task<Media> loadSoundTask = new Task<Media>() {
            @Override
            protected Media call() throws Exception {
                return loadSound(filename);
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                playMedia(getValue());
            }

            @Override
            protected void failed() {
                super.failed();
                System.out.println("Failed to load sound: " + filename);
            }
        };

        Thread thread = new Thread(loadSoundTask);
        thread.setDaemon(true);
        thread.start();
    }

    private void playMedia(Media sound) {
        new Thread(() -> {if (sound != null) {
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
            mediaPlayer.setOnEndOfMedia(mediaPlayer::dispose);
        }}).start();
    }

    public Media loadSound(String filename) {
        Media sound = new Media(getClass().getResource("/sounds/" + filename).toExternalForm());
        soundMap.put(filename, sound);

        return sound;
    }
}
