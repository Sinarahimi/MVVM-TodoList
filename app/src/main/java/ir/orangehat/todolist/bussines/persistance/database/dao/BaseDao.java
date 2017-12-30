package ir.orangehat.todolist.bussines.persistance.database.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import ir.orangehat.todolist.bussines.model.Persistable;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * This is the Generic class for all the Dao
 */

public interface BaseDao<T extends Persistable> {

    @Insert(onConflict = REPLACE)
    void insert(T t);

    @Update
    void update(T t);

    @Delete
    void delete(T t);
}
