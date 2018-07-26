package dushanes.caloriecounterub;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {
    private RecyclerView mRecyclerViewBreak;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;
    String h[] = {"test"};

    user person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        person = (user) getIntent().getSerializableExtra("user");

        TextView textViewCalories = findViewById(R.id.goal_cal);
        textViewCalories.setText(Integer.toString(person.calories));


        /*    *****BUG PRESENT*****
        mRecyclerViewBreak = findViewById(R.id.recycleBreak);

        mRecyclerViewBreak.setHasFixedSize(false);

        mLayout = new LinearLayoutManager(this);
        mRecyclerViewBreak.setLayoutManager(mLayout);

        mAdapter = new recyclerViewAdapter(h);
        mRecyclerViewBreak.setAdapter(mAdapter);*/
    }
}
