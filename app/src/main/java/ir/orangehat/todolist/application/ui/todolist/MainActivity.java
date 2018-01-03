package ir.orangehat.todolist.application.ui.todolist;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ir.orangehat.todolist.R;
import ir.orangehat.todolist.bussines.model.Task;
import ir.orangehat.todolist.utils.RecyclerItemTouchHelper;

public class MainActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private MainViewModel mainViewModel;
    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton floatingActionButton;
    private View dialogView;
    private AlertDialog alertDialog;
    private ArrayList<Task> taskArrayList;
    private RecyclerViewAdapter recyclerViewAdapter;
    private String date;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = getRecyclerView();

        view();

        floatingActionButton.setOnClickListener(FloatingActionButtonClickListener);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        observe(recyclerView);
    }

    // Swiped from left to right for removing item
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        // backup of removed item for undo purpose
        final Task deletedItem = taskArrayList.get(viewHolder.getAdapterPosition());
        final int deletedIndex = viewHolder.getAdapterPosition();

        // remove the item from recycler view
        recyclerViewAdapter.removeItem(viewHolder.getAdapterPosition());
        mainViewModel.deleteNote(deletedItem);
        // showing snack bar with Undo option
        snackbar = Snackbar.make(coordinatorLayout, " It's removed ", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", view -> {

            // undo is selected, restore the deleted item
            recyclerViewAdapter.restoreItem(deletedItem, deletedIndex);
        });
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();
    }

    private void view() {
        floatingActionButton = findViewById(R.id.fab);
        coordinatorLayout = findViewById(R.id.coordinatorLayoutMain);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Todo");
    }

    private void observe(RecyclerView recyclerView) {
        mainViewModel.getListLiveData().observe(MainActivity.this, toDos -> {
            taskArrayList = new ArrayList<>(toDos);
            ImageView imageViewEmptyList = findViewById(R.id.imageViewEmptyList);
            TextView textViewEmptyList = findViewById(R.id.textViewEmptyList);
            recyclerViewAdapter = new RecyclerViewAdapter(taskArrayList, MainActivity.this);
            if (recyclerViewAdapter.getItemCount() == 0) {
                imageViewEmptyList.setVisibility(View.VISIBLE);
                textViewEmptyList.setVisibility(View.VISIBLE);
            } else {
                imageViewEmptyList.setVisibility(View.GONE);
                textViewEmptyList.setVisibility(View.GONE);
            }
            recyclerView.setAdapter(recyclerViewAdapter);
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
        dialogView = inflater.inflate(R.layout.alert_dialog, null);
        dialogBuilder.setView(dialogView);

        final CalendarView calendarView = dialogView.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((dateView, year, month, dayOfMonth) -> date = "date :" + year + "/" + (month + 1) + "/" + dayOfMonth);

        alertDialog = dialogBuilder.create();
        Button acceptButton = dialogView.findViewById(R.id.acceptButton);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);
        acceptButton.setOnClickListener(onAcceptButtonClickListener);

        cancelButton.setOnClickListener(view -> alertDialog.cancel());
        alertDialog.show();
    }

    private View.OnClickListener onAcceptButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText editText = dialogView.findViewById(R.id.editText);

            if (editText.getText().toString().trim().length() == 0 ) {
                snackbar = Snackbar.make(dialogView, "Write task!", Snackbar.LENGTH_SHORT);
                snackbar.show();
            } else {
                if (date == null) {
                    snackbar = Snackbar.make(dialogView, "select date from calendar!", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    Task task = new Task();
                    task.setDate(date);
                    task.setNote(editText.getText().toString());
                    mainViewModel.insertNote(task);
                    alertDialog.cancel();
                }
            }
        }
    };
}
