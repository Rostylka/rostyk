package org.example.springlearning;

import org.springframework.stereotype.Component;

//@Component
public class RockMusic implements Music{
    public String getSong() {
        return "Unforgiven";
    }
}
