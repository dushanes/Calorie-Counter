package dushanes.caloriecounterub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainMenu extends AppCompatActivity {
    private RecyclerView mRecyclerViewBreak;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;
    String h[] = {"test"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mRecyclerViewBreak = findViewById(R.id.recycleBreak);

        mRecyclerViewBreak.setHasFixedSize(false);

        mLayout = new LinearLayoutManager(this);
        mRecyclerViewBreak.setLayoutManager(mLayout);

        mAdapter = new recyclerViewAdapter(h);
        mRecyclerViewBreak.setAdapter(mAdapter);
    }
}
