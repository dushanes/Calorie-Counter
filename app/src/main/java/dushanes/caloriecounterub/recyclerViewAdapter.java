package dushanes.caloriecounterub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.viewHolder>{

    private static final String TAG = "recyclerViewAdapter";
    private String[] dataset;
    private Context context;

    public static class viewHolder extends RecyclerView.ViewHolder {
        public TextView foodNameText;
        public TextView caloriesText;
        RelativeLayout layout;
        public viewHolder(View v){
            super(v);
            foodNameText = v.findViewById(R.id.foodName);
            caloriesText = v.findViewById(R.id.foodCalories);
            layout = v.findViewById(R.id.parent_layout);
        }
    }

    public recyclerViewAdapter(String[] data, Context context) {
        this.dataset = data;
        this.context = context;
    }

    @Override
    public recyclerViewAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int type){
        Log.d(TAG, "onCreateViewHolder has been called");
        View v =  LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recycler_view_main_menu, parent, false);

        viewHolder vh = new viewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder has been called");
        holder.foodNameText.setText(dataset[position]);
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }
}
