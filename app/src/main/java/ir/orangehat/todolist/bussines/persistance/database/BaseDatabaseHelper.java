package ir.orangehat.todolist.bussines.persistance.database;

import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import ir.orangehat.todolist.bussines.model.Persistable;
import ir.orangehat.todolist.bussines.model.Todo;

/**
 * BaseDatabaseHelper
 */

abstract class BaseDatabaseHelper<T extends Persistable> {

    abstract LiveData<List<Todo>> get();

    abstract void save(T t);

    abstract void remove(T t);
}
