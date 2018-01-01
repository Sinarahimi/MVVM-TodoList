package ir.orangehat.todolist.bussines.persistance.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

import ir.orangehat.todolist.bussines.model.Todo;

/**
 * data access object for todoList
 */

@Dao
public interface TodoListDao extends BaseDao {

    @Query("select * from Todo")
    LiveData<List<Todo>> select();
}
