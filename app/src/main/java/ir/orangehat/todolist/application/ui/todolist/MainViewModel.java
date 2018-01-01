package ir.orangehat.todolist.application.ui.todolist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import java.util.List;

import ir.orangehat.todolist.bussines.model.Todo;
import ir.orangehat.todolist.bussines.persistance.database.AppDatabase;
import ir.orangehat.todolist.bussines.persistance.database.TodoListDatabaseHelper;
import ir.orangehat.todolist.bussines.persistance.database.sqlAsset.AssetSQLiteOpenHelperFactory;

/**
 * MainViewModel
 */

public class MainViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public MainViewModel(@NonNull Application application) {
        super(application);

        appDatabase = Room.databaseBuilder(this.getApplication(), AppDatabase.class, "database.db").openHelperFactory(new AssetSQLiteOpenHelperFactory()).allowMainThreadQueries().build();
    }

    public LiveData<List<Todo>> getListLiveData() {
        TodoListDatabaseHelper todoListDatabaseHelper = new TodoListDatabaseHelper(appDatabase.getTodoListDao());
        return todoListDatabaseHelper.get();
    }
}
