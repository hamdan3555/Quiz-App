package com.example.quizworld.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.quizworld.R;
import com.example.quizworld.models.Questions;
import com.example.quizworld.models.Quiz;
import com.google.gson.Gson;

import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.R)
public class Result extends AppCompatActivity {
    Quiz quiz = new Quiz();
    Map<String, Questions> questions = Map.of();
    TextView text_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        text_score=findViewById(R.id.txtScore);

        setUpViews();
    }
    private void setUpViews() {
        String quiz_Data= getIntent().getStringExtra("Quiz");
        Gson gson = new Gson();
        Quiz[] quiz=gson.fromJson(quiz_Data,Quiz[].class);
        questions=quiz[0].questions;
        set_answer_view();
        calculateScore();

    }
    private void set_answer_view() {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;
        TextView Ans;
        Ans = findViewById(R.id.txtAnswer);
        for (int i = 0; i < questions.size(); i++) {
            Questions questionss = questions.get("question" + index);
            index++;
            stringBuilder.append("<font color '#18206f'><b>Question<br/>" + questionss.getDescription() + "</b></font><br/><br/>");
            stringBuilder.append("<font color '#18206f'>Answer<br/>" + questionss.answer + "</font><br/><br/><br/>");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Ans.setText(Html.fromHtml(stringBuilder.toString(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            Ans.setText(Html.fromHtml(stringBuilder.toString()));
        }

    }
        // Calculating Score
    private void calculateScore() {

        int score=0;
        int index=1;
        for(int i=0; i<questions.size(); i++)
        {
            Questions question = questions.get("question"+index);
            index++;
            if(question.getAnswer().equals(question.getUser_answer() )){
                score= score+20;
            }
        }
        text_score.setText("Your Score: "+score);
    }
}
