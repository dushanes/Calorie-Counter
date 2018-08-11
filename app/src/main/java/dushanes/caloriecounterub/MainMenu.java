package dushanes.caloriecounterub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainMenu extends AppCompatActivity {
    private RecyclerView mRecyclerViewBreak;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;
    public static TextView date;
    public static ArrayList <foodItem>data = new ArrayList<>();
    user person;
    private Calendar today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        date = findViewById(R.id.dateText);
        mRecyclerViewBreak = findViewById(R.id.recycleBreak);

        person = (user) getIntent().getSerializableExtra("user");

        TextView textViewCalories = findViewById(R.id.goal_cal);
        textViewCalories.setText(Integer.toString(person.calories));
        dateTask currentDate = new dateTask();
        currentDate.execute();

        initRecyclerView();
    }

    public void initRecyclerView(){
        mAdapter = new recyclerViewAdapter(data, this);

        mRecyclerViewBreak.setHasFixedSize(false);

        mLayout = new LinearLayoutManager(this);
        mRecyclerViewBreak.setLayoutManager(mLayout);

        mRecyclerViewBreak.setAdapter(mAdapter);
    }
}
