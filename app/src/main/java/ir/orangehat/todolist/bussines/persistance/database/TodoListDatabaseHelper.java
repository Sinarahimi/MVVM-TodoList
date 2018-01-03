package ir.orangehat.todolist.bussines.persistance.database;

import android.arch.lifecycle.LiveData;

import java.util.List;

import ir.orangehat.todolist.bussines.model.Task;
import ir.orangehat.todolist.bussines.persistance.database.dao.TodoListDao;

/**
 * TodoListDatabaseHelper
 */

public class TodoListDatabaseHelper extends BaseDatabaseHelper<Task> {

    private TodoListDao todoListDao;

    public TodoListDatabaseHelper(TodoListDao todoListDao) {
        this.todoListDao = todoListDao;
    }

    @Override
    public LiveData<List<Task>> get() {
        return todoListDao.select();
    }

    @Override
    public void save(Task task) {
        todoListDao.insert(task);
    }

    @Override
    public void remove(Task task) {
        todoListDao.delete(task);
    }
}
