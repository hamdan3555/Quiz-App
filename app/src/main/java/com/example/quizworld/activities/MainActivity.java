package com.example.quizworld.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizworld.R;
import com.example.quizworld.adapters.quiz_adapter;
import com.example.quizworld.models.Quiz;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class MainActivity extends AppCompatActivity {
    DrawerLayout d_Layout;
    NavigationView navigationView;
    Toolbar tool_bar;
    ActionBarDrawerToggle toggle;
    FirebaseFirestore firestore;
    FloatingActionButton quiz_picker;
    ArrayList<Quiz> quiz_list = new ArrayList<Quiz>();
    quiz_adapter adapter = new quiz_adapter(this, quiz_list);

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        set_up_views();

    }

    // Function to Set Icon Menu
    @RequiresApi(api = Build.VERSION_CODES.R)
    public void set_up_views() {
        set_up_fireStore();
        set_up_drawer_layout();

        set_up_recycle_view();
       set_up_quiz_picker();
    }

    public void set_up_quiz_picker(){
        quiz_picker = (FloatingActionButton) findViewById(R.id.btn_quiz_picker);
        quiz_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Pending", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void set_up_fireStore(){
        firestore = FirebaseFirestore.getInstance();
        CollectionReference quiz = firestore.collection("Quizzes");
        Map<String, Object> data1 = new HashMap<>();
        quiz.addSnapshotListener((value, error) ->{
            if(value == null || error!=null){
                Toast.makeText(getApplicationContext(),"Error fetching the Data",Toast.LENGTH_SHORT).show();
            }
            Quiz object = new Quiz();
            Log.d(TAG, "Current data: " + value.toObjects(Quiz.class).toString());
            quiz_list.clear();
            quiz_list.addAll(value.toObjects(Quiz.class));
            adapter.notifyDataSetChanged();

        } );
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public  void dummy_data(){
        Quiz obj = new Quiz();
        obj.setTitle("Quiz1");
        obj.setId("100");
        quiz_list.add(obj);
        Quiz obj1 = new Quiz();
        obj1.setTitle("Quiz2");
        obj1.setId("101");
        quiz_list.add(obj1);

    }
    public void set_up_recycle_view(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        RecyclerView recycle_view = findViewById(R.id.quiz_recycle_view);
        recycle_view.setAdapter(adapter);
        recycle_view.setLayoutManager(gridLayoutManager);
    }

    public  void set_up_drawer_layout(){
        navigationView = findViewById(R.id.navigation_id);
        navigationView.setNavigationItemSelectedListener(item -> {
            Intent intent = new Intent(MainActivity.this, Profile.class);
            this.startActivity(intent);
            finish();
            return true;
        });
        Toolbar tb = findViewById(R.id.AppBar);
        setSupportActionBar(tb);

        DrawerLayout dr = findViewById(R.id.main_drawer);
        toggle = new ActionBarDrawerToggle(this, dr, R.string.app_name, R.string.app_name);
        toggle.syncState();

    }

}