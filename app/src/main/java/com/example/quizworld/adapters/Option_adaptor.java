package com.example.quizworld.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizworld.R;
import com.example.quizworld.models.Questions;
import com.example.quizworld.models.Quiz;

import java.util.ArrayList;

import static android.app.ProgressDialog.show;
import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class Option_adaptor extends RecyclerView.Adapter<Option_adaptor.OptionViewHolder> {
    Context context;
    Questions questions = new Questions();
    public ArrayList<String> options = new ArrayList<>();

    public Option_adaptor(Context context, Questions questions) {
        this.context = context;
        this.questions = questions;
        this.options.add(questions.option1);
        this.options.add(questions.option2);
        this.options.add(questions.option3);
        this.options.add(questions.option4);
        Log.i("options", "Option_adaptor: " + options);

    }

    public void setList(ArrayList<String> list) {
        this.options = list;
    }

    public ArrayList<String> getList() {
        return options;
    }

    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.option_item, parent, false);
        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {
        String var = options.get(position);
        Log.i("var", "onBindViewHolder: " +var);
        Log.i("position", "onBindViewHolder: " + position);

        holder.option_view.setText(var);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questions.setUser_answer(var);
                notifyDataSetChanged();
                Log.i("Click", "onClick: "+questions.getUser_answer());
            }
        });

        if (questions.getUser_answer() == var) {
            Toast.makeText(context,var,Toast.LENGTH_SHORT).show();
            Log.i("if", "onif: " +questions.getUser_answer()+"   "+var);
            holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg);
        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg);
        }

    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public class OptionViewHolder extends RecyclerView.ViewHolder {

        public TextView option_view;
        public OptionViewHolder(View itemView) {
            super(itemView);
            option_view = itemView.findViewById(R.id.option);

        }

    }

}
