package com.example.archdemo.databinding;

import com.baurine.multitypeadapter.MultiTypeAdapter;

/**
 * Created by baurine on 8/2/17.
 */

public class TodoModel {
    public BaseItem createItem(MultiTypeAdapter adapter) {
        return new TodoItem(this, adapter);
    }

    public TodoModel(String content) {
        this.content = content;
        this.completed = false;
    }

    public String content;
    public boolean completed;
}
