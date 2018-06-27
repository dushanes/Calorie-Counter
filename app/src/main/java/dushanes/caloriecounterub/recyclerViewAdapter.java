package dushanes.caloriecounterub;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.viewHolder>{

    private String[] dataset;

    public static class viewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public viewHolder(TextView v){
            super(v);
            textView = v;
        }
    }

    public recyclerViewAdapter(String[] dataset) {
        dataset = dataset;
    }

    @Override
    public recyclerViewAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int type){
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_menu, parent, false);

        viewHolder vh = new viewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        holder.textView.setText(dataset[position]);
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }
}
