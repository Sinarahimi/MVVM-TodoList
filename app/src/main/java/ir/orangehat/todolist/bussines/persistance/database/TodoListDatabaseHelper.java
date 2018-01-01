package ir.orangehat.todolist.bussines.persistance.database;

import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import ir.orangehat.todolist.bussines.model.Todo;
import ir.orangehat.todolist.bussines.persistance.database.dao.TodoListDao;

/**
 * TodoListDatabaseHelper
 */

public class TodoListDatabaseHelper extends BaseDatabaseHelper<Todo> {

    private TodoListDao todoListDao;

    public TodoListDatabaseHelper(TodoListDao todoListDao) {
        this.todoListDao = todoListDao;
    }

    @Override
    public LiveData<List<Todo>> get() {
        return todoListDao.select();
    }

    @Override
    public void save(Todo todo) {
        todoListDao.insert(todo);
    }

    @Override
    public void remove(Todo todo) {
        todoListDao.delete(todo);
    }
}
