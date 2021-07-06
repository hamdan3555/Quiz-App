package com.example.quizworld.utils;

import com.example.quizworld.R;

import java.util.Random;

public class Icons_Picker {
    int array[] = {R.drawable.ic_alarm_svgrepo_com,
            R.drawable.ic_calculator_svgrepo_com,
    R.drawable.ic_education_svgrepo_com,
    R.drawable.ic_educational_book_and_a_letter_svgrepo_com,
    R.drawable.ic_school_building_with_flag_svgrepo_com,
    R.drawable.ic_messages_mails_svgrepo_com,
    R.drawable.ic_pencil_svgrepo_com,
    R.drawable.ic_notes_svgrepo_com};
    int current_icon = 0;

    public int get_icon(){
        Random rand = new Random();
        current_icon = rand.nextInt(array.length-1);

        return array[current_icon];
    }
}
