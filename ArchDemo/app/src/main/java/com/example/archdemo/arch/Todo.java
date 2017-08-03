package com.example.archdemo.arch;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by baurine on 8/3/17.
 */

@Entity(tableName = "todos")
public class Todo {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public String content;

    @ColumnInfo
    public boolean completed;

    public Todo(String content) {
        this.content = content;
        this.completed = false;
    }
}
