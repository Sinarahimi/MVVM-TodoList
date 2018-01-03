package ir.orangehat.todolist.bussines.model;

import android.arch.persistence.room.Entity;

/**
 * TodoListDao model
 */

@Entity
public class Task extends Persistable {

    private String note;
    private String date;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
