package ir.orangehat.todolist.bussines.persistance.database;

import android.arch.lifecycle.LiveData;

import java.util.List;

import ir.orangehat.todolist.bussines.model.Todo;
import ir.orangehat.todolist.bussines.persistance.database.dao.TodoListDao;

/**
 * TodoListDatabaseHelper
 */

public class TodoListDatabaseHelper extends BaseDatabaseHelper<Todo> {

    private TodoListDao todoListDao;


    @Override
    LiveData<List<Todo>> get() {
        return todoListDao.select();
    }

    @Override
    void save(Todo todo) {
        todoListDao.insert(todo);
    }

    @Override
    void remove(Todo todo) {
        todoListDao.delete(todo);
    }
}
