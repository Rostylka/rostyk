package org.example.springlearning;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RockMusic implements Music{
    List<String> songs = Arrays.asList(new String[] {"Unforgiven I", "Unforgiven II", "Unforgiven III"});

    public List<String> getSong() {
        return songs;
    }
}
