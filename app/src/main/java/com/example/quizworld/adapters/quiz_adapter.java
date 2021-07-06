package com.example.quizworld.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizworld.R;
import com.example.quizworld.activities.MainActivity;
import com.example.quizworld.activities.Question_Activity;
import com.example.quizworld.activities.login;
import com.example.quizworld.models.Quiz;
import com.example.quizworld.utils.Color_Picker;
import com.example.quizworld.utils.Icons_Picker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.graphics.Color.parseColor;
import static androidx.constraintlayout.motion.utils.Oscillator.TAG;
import static androidx.core.content.ContextCompat.startActivity;

public  class quiz_adapter extends RecyclerView.Adapter<quiz_adapter.ViewHolder> {
        Context context;
        private ArrayList<Quiz> quizList=new ArrayList<Quiz>();

      public quiz_adapter(Context context, ArrayList<Quiz> quizes){
            this.context = context;
            this.quizList = quizes;
    }



    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quiz_item,parent,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void onBindViewHolder(@NonNull quiz_adapter.ViewHolder holder, int position) {
        String quiz = quizList.get(position).title;
        Log.i("quiz_pos", "onBindViewHolder: "+position);
        holder.quiz_t.setText(quiz);
        Color_Picker select_color = new Color_Picker();
        Icons_Picker select_icon = new Icons_Picker();
        holder.card_container.setCardBackgroundColor(Color.parseColor(select_color.get_color()));
        holder.quiz_icon.setImageResource(select_icon.get_icon());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, quizList.get(position).title, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, Question_Activity.class);
                intent.putExtra("Name", quizList.get(position).title);
                context.startActivity(intent);
            }
        });

    }

    public int getItemCount() {
        return quizList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
         public TextView quiz_t;
         ImageView quiz_icon;
         CardView card_container;

        public ViewHolder(View itemView) {
            super(itemView);
            quiz_t = itemView.findViewById(R.id.quiz_title);
            quiz_icon = itemView.findViewById(R.id.quiz_icon);
            card_container = itemView.findViewById(R.id.card_container);

        }

    }


}
