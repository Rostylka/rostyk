package org.example.springlearning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MusicPlayer {
    @Autowired
    @Qualifier("rockMusic")
    private Music music1;
    @Autowired
    @Qualifier("classicalMusic")
    private Music music2;
    private int volume;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MusicPlayer() {
    }

    public MusicPlayer(Music music1, Music music2) {
        this.music1 = music1;
        this.music2 = music2;
    }

    public String playMusic(Genres genre) {
        if (genre.equals(Genres.ROCKMUSIC)) {
            return "Playing: " + music1.getSong().get(new Random().nextInt(3));
        }
        else {
            return "Playing: " + music2.getSong().get(new Random().nextInt(3));
        }
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
