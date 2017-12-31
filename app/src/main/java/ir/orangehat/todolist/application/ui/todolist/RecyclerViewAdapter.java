package ir.orangehat.todolist.application.ui.todolist;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ir.orangehat.todolist.R;
import ir.orangehat.todolist.bussines.model.Todo;

/**
 * RecyclerViewAdapter
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Todo> todoArrayList;
    private Context context;

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_todo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        Todo todo = todoArrayList.get(position);
        holder.textViewDate.setText(todo.getDate());
        holder.textViewTitle.setText(todo.getNote());
    }

    @Override
    public int getItemCount() {
        return (todoArrayList == null || todoArrayList.isEmpty()) ? 0 : todoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewDate;
        public CardView fourground;
        public RelativeLayout background;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            fourground = itemView.findViewById(R.id.cardViewFourground);
            background = itemView.findViewById(R.id.relativeLayoutBackground);
        }
    }

    public void removeItem(int position) {
        todoArrayList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }


}
