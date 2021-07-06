package com.example.quizworld.utils;

import java.util.Random;

public class Color_Picker {
    String array[] = {"#9874a7", "#a29088", "#8657c5", "#00c3ff", "#e0af1f", "#ffdab9", "#5f9ea0", "#7fff00", "#cd5c5c", "#ff69b4"};
    int current_color = 0;

    public String get_color(){
        Random rand = new Random();
        current_color = rand.nextInt(array.length-1);
        return array[current_color];
    }
}
