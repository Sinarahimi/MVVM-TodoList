package ir.orangehat.todolist.application.ui.todolist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.orangehat.todolist.R;
import ir.orangehat.todolist.bussines.model.Todo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(view -> {
            addItemDialog();
        });

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mainViewModel.getListLiveData().observe(MainActivity.this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(@Nullable List<Todo> toDos) {
                ArrayList<Todo> todoArrayList = new ArrayList<>(toDos);
                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(todoArrayList, MainActivity.this);
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        });
    }

    public void addItemDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alert_dialog, null);
        dialogBuilder.setView(dialogView);

        final CalendarView calendarView = dialogView.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                TextView dateTextView = dialogView.findViewById(R.id.AlertTextDate);
                dateTextView.setText("date :" + year + "/" + (month + 1) + "/" + dayOfMonth);
            }
        });
        calendarView.getDate();

        long date = calendarView.getDate();

        dialogBuilder.setTitle("Custom dialog");
        dialogBuilder.setMessage("Enter text below");

        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}
