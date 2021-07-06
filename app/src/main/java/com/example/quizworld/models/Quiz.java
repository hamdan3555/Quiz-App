package com.example.quizworld.models;


import android.os.Build;

import androidx.annotation.RequiresApi;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.R)
public class Quiz {


    public String id;
    public String title;
    public Map<String, Questions> questions= Map.of();

    public Quiz() {
    }

    public Quiz(String id, String title) {
        this.id = id;
        this.title = title;
    }


    public Map<String, Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<String, Questions> questions) {
        this.questions = questions;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", questions=" + questions +
                '}';
    }
}