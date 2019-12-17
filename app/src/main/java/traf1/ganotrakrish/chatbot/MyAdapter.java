package traf1.ganotrakrish.chatbot;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<List<String>> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView sender;
        public TextView text;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            sender = v.findViewById(R.id.name);
            text = v.findViewById(R.id.text);
        }
    }

    public void add(int position, List<String> item) {
        values.add(position, item);
//        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
//        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<List<String>> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        List<String> input = values.get(position);
        String name = input.get(0);
        String text = input.get(1);
        holder.sender.setText(name);
        holder.sender.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });

        holder.text.setText(text);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}