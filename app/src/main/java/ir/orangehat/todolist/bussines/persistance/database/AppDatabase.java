package ir.orangehat.todolist.bussines.persistance.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ir.orangehat.todolist.bussines.model.Todo;
import ir.orangehat.todolist.bussines.persistance.database.dao.TodoListDao;

/**
 * AppDatabase
 */

@Database(entities = Todo.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TodoListDao getTodoListDao();
}
