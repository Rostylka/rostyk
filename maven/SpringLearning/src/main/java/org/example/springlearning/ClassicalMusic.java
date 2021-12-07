package org.example.springlearning;

import org.springframework.stereotype.Component;

@Component
public class ClassicalMusic implements Music{

    public String getSong() {
        return "Adagio For Strings";
    }
}
