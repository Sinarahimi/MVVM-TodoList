package ir.orangehat.todolist.application.ui.todolist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.orangehat.todolist.R;
import ir.orangehat.todolist.bussines.model.Task;
import ir.orangehat.todolist.utils.RecyclerItemTouchHelper;

public class MainActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private TextView dateTextView;
    private MainViewModel mainViewModel;
    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = getRecyclerView();

        view    ();

        floatingActionButton.setOnClickListener(FloatingActionButtonClickListener);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        observe(recyclerView);
    }

    private void view() {
        floatingActionButton = findViewById(R.id.fab);
        coordinatorLayout = findViewById(R.id.coordinatorLayoutMain);
    }

    private void observe(RecyclerView recyclerView) {
        mainViewModel.getListLiveData().observe(MainActivity.this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> toDos) {
                ArrayList<Task> taskArrayList = new ArrayList<>(toDos);
                ImageView imageView = findViewById(R.id.imageViewEmptyList);
                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(taskArrayList, MainActivity.this);
                if (recyclerViewAdapter.getItemCount() == 0) {
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                }
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        });
    }

    @NonNull
    private RecyclerView getRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        return recyclerView;
    }

    private View.OnClickListener FloatingActionButtonClickListener = view -> addItemDialog();

    public void addItemDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alert_dialog, null);
        dialogBuilder.setView(dialogView);

        final CalendarView calendarView = dialogView.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((dateView, year, month, dayOfMonth) -> {
            dateTextView = dialogView.findViewById(R.id.AlertTextDate);
            dateTextView.setText("date :" + year + "/" + (month + 1) + "/" + dayOfMonth);
        });
        AlertDialog b = dialogBuilder.create();
        Button acceptButton = dialogView.findViewById(R.id.acceptButton);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);
        acceptButton.setOnClickListener(view -> {
            EditText editText = dialogView.findViewById(R.id.editText);
            Task task = new Task();
            task.setDate(dateTextView.getText().toString());
            task.setNote(editText.getText().toString());
            mainViewModel.insertNote(task);
            b.cancel();
        });

        cancelButton.setOnClickListener(view -> b.cancel());

        dialogBuilder.setTitle("Custom dialog");
        b.show();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        //TODO: complete this part
//
//        String name = cartList.get(viewHolder.getAdapterPosition()).getName();
//
//        // backup of removed item for undo purpose
//        final Item deletedItem = cartList.get(viewHolder.getAdapterPosition());
//        final int deletedIndex = viewHolder.getAdapterPosition();
//
//        // remove the item from recycler view
//        mAdapter.removeItem(viewHolder.getAdapterPosition());
//
//        // showing snack bar with Undo option
//        Snackbar snackbar = Snackbar
//                .make(coordinatorLayout, name + " removed from cart!", Snackbar.LENGTH_LONG);
//        snackbar.setAction("UNDO", new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                // undo is selected, restore the deleted item
//                mAdapter.restoreItem(deletedItem, deletedIndex);
//            }
//        });
//        snackbar.setActionTextColor(Color.YELLOW);
//        snackbar.show();
    }
}
