package org.example.springlearning;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ClassicalMusic implements Music{
        List<String> songs = Arrays.asList(new String[] {"Moonlight Sonata", "Adagoi For Strings", "Hungarian Rapsody"});

    public List<String> getSong() {
        return songs;
    }
}
