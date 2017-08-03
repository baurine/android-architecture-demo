package com.example.archdemo.databinding;

import com.baurine.multitypeadapter.MultiTypeAdapter;
import com.example.archdemo.arch.Todo;

/**
 * Created by baurine on 8/2/17.
 */

public class TodoModel {
    public BaseItem createItem(MultiTypeAdapter adapter) {
        return new TodoItem(this, adapter);
    }

    public TodoModel(String content) {
        // this.content = content;
        // this.completed = false;
    }

    public TodoModel(Todo todo) {
        // this.id = todo.id;
        // this.content = todo.content;
        // this.completed = todo.completed;
        this.todo = todo;
    }

    // public int id;
    // public String content;
    // public boolean completed;
    public Todo todo;
}
