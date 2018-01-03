package ir.orangehat.todolist.bussines.persistance.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import ir.orangehat.todolist.bussines.model.Task;

/**
 * data access object for todoList
 */

@Dao
public interface TodoListDao extends BaseDao<Task> {

    @Query("select * from Task")
    LiveData<List<Task>> select();
}
