package ru.turbogoose;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.turbogoose.music.Music;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class MusicPlayer {
    @Value("${player.name}")
    private String name;
    @Value("${player.volume}")
    private int volume;
    private final List<Music> playlist = new ArrayList<>();

    public MusicPlayer(List<Music> playlist) {
        this.playlist.addAll(playlist);
    }

    public void playMusic() {
        int randomIndex = new Random().nextInt(playlist.size());
        String randomSong = playlist.get(randomIndex).getSong();
        System.out.println("Playing: " + randomSong);
    }

    @Override
    public String toString() {
        return "MusicPlayer{" +
                "name='" + name + '\'' +
                ", volume=" + volume +
                '}';
    }
}
