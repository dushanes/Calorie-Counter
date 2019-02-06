package dushanes.caloriecounterub;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import dushanes.caloriecounterub.Tasks.encodeTask;
import dushanes.caloriecounterub.adapter.recyclerViewAdapter;
import dushanes.caloriecounterub.model.foodItem;

public class searchPage extends AppCompatActivity {
    private RequestQueue requestQueue;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;
    private JsonObjectRequest jsonObject;
    private SearchView search;

    public static ArrayList<foodItem> data = new ArrayList<>();
    public static String target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);

        setContentView(R.layout.activity_search_page);
        search = findViewById(R.id.searchBar);
        mRecyclerView = findViewById(R.id.recycleResults);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final Context context = this;
        int width = display.widthPixels;
        int height = display.heightPixels;

        getWindow().setLayout((int)(width * .8), (int)(height * .6));

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getJSON();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        initRecyclerView();

    }

    public void getJSON(){
        encodeTask encodeTask = new encodeTask();
        String url = "";
        try {
            url = encodeTask.execute(search.getQuery().toString()).get();
        } catch (InterruptedException i) {
            i.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        jsonObject = new JsonObjectRequest(Request.Method.GET, url, null,  new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray hits = response.getJSONArray("hints");
                    JSONObject o = null;
                    JSONObject foodObject = null;
                    JSONArray measureArray = null;
                    JSONObject nutrientsObject = null;
                    foodItem foodItem = null;
                    String brand = "";

                    for (int i = 0; i < hits.length(); ++i){
                        o = hits.getJSONObject(i);
                        foodObject = o.getJSONObject("food");
                        measureArray = o.getJSONArray("measures");
                        nutrientsObject = foodObject.getJSONObject("nutrients");

                        if(foodObject.has(brand)){
                            brand = foodObject.getString("brand");
                        }

                        foodItem = new foodItem(
                                foodObject.getString("label"),
                                brand,
                                nutrientsObject.getInt("ENERC_KCAL"),
                                nutrientsObject.getInt("CHOCDF"),
                                nutrientsObject.getInt("PROCNT"),
                                nutrientsObject.getInt("FAT")
                        );

                        data.add(foodItem);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                updateRecyclerView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                 error.printStackTrace();
            }
        });

        requestQueue = Volley.newRequestQueue(searchPage.this);
        requestQueue.add(jsonObject);
    }

    public void initRecyclerView(){
        mAdapter = new recyclerViewAdapter(data, this);

        mRecyclerView.setHasFixedSize(false);

        mLayout = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayout);

        mRecyclerView.setAdapter(mAdapter);
    }

    public void updateRecyclerView(){
        mAdapter = new recyclerViewAdapter(data, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    public static void setTarget(String target) {
        searchPage.target = target;
    }
}
