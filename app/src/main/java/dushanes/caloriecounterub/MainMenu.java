package dushanes.caloriecounterub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import dushanes.caloriecounterub.Tasks.dateTask;
import dushanes.caloriecounterub.adapter.recyclerViewAdapter;
import dushanes.caloriecounterub.model.foodItem;

public class MainMenu extends AppCompatActivity {
    private RecyclerView mRecyclerViewBreak;
    public TextView textViewCalories;
    public static TextView date;
    public Calendar today;
    public ArrayList <foodItem>data = new ArrayList<>();
    public user person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        person = (user) getIntent().getSerializableExtra("user");
        date = findViewById(R.id.dateText);
        textViewCalories = findViewById(R.id.goal_cal);
        mRecyclerViewBreak = findViewById(R.id.recycleBreak);

        textViewCalories.setText(String.format(Locale.ENGLISH, "%d", person.calories));

                    //Setting today's date
        dateTask currentDate = new dateTask();
        currentDate.execute();

        initRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.standard_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void initRecyclerView(){
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager mLayout;
        mAdapter = new recyclerViewAdapter(data, this);
        mLayout = new LinearLayoutManager(this);

        mRecyclerViewBreak.setHasFixedSize(false);
        mRecyclerViewBreak.setLayoutManager(mLayout);
        mRecyclerViewBreak.setAdapter(mAdapter);
    }

    public void addFood(View view){
        startActivity(new Intent(MainMenu.this, searchPage.class));
    }
}
