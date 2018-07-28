package dushanes.caloriecounterub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {
    private RecyclerView mRecyclerViewBreak;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;

    user person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        person = (user) getIntent().getSerializableExtra("user");

        TextView textViewCalories = findViewById(R.id.goal_cal);
        textViewCalories.setText(Integer.toString(person.calories));

        initRecyclerView();
    }

    private void initRecyclerView(){
        String h[] = {"test", "hi", "good", "help"};

        mRecyclerViewBreak = findViewById(R.id.recycleBreak);
        mAdapter = new recyclerViewAdapter(h, this);

        mRecyclerViewBreak.setHasFixedSize(false);

        mLayout = new LinearLayoutManager(this);
        mRecyclerViewBreak.setLayoutManager(mLayout);

        mRecyclerViewBreak.setAdapter(mAdapter);//
    }
}
