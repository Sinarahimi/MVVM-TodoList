package ir.orangehat.todolist.bussines.model;

import android.arch.persistence.room.PrimaryKey;

/**
 * Parent class of models
 */

public abstract class Persistable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
