package dushanes.caloriecounterub.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import dushanes.caloriecounterub.R;
import dushanes.caloriecounterub.model.foodItem;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.viewHolder>{

    private static final String TAG = "recyclerViewAdapter";
    private ArrayList<foodItem> dataSet;
    private Context context;

    public static class viewHolder extends RecyclerView.ViewHolder {
        public TextView foodNameText;
        public TextView caloriesText;
        public TextView brandText;
        RelativeLayout layout;
        public viewHolder(View v){
            super(v);
            foodNameText = v.findViewById(R.id.foodName);
            caloriesText = v.findViewById(R.id.foodCalories);
            brandText = v.findViewById(R.id.foodBrand);
            layout = v.findViewById(R.id.parent_layout);
        }
    }

    public recyclerViewAdapter(ArrayList<foodItem> data, Context context) {
        this.dataSet = data;
        this.context = context;
    }

    @Override
    public recyclerViewAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int type){
        //Log.d(TAG, "onCreateViewHolder has been called");
        View v =  LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recycler_view_search_page, parent, false);

        viewHolder vh = new viewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        //Log.d(TAG, "onBindViewHolder has been called");
        holder.foodNameText.setText(dataSet.get(position).getName());
        holder.caloriesText.setText(String.format(Integer.toString(dataSet.get(position).getCalories())));
        String brand = dataSet.get(position).getBrand();

        if (dataSet.get(position).getBrand() != null) {
            holder.brandText.setText(brand);
        }else{
            holder.brandText.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
