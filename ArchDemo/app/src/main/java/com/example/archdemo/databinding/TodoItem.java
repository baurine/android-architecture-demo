package com.example.archdemo.databinding;

import android.view.View;

import com.baurine.multitypeadapter.MultiTypeAdapter;
import com.example.archdemo.R;

/**
 * Created by baurine on 8/2/17.
 */

public class TodoItem extends BaseItem {
    @Override
    public int getLayout() {
        return R.layout.item_todo;
    }

    public TodoItem(TodoModel todoModel, final MultiTypeAdapter adapter) {
        this.todoModel = todoModel;
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.cb_complete:
                        toggleCompleted();
                        adapter.notifyItemChanged(adapter.findPos(TodoItem.this));
                        break;
                    case R.id.btn_del:
                        adapter.notifyItemRemoved(adapter.removeItem(TodoItem.this));
                        break;
                }
            }
        });
    }

    private TodoModel todoModel;

    public String getContent() {
        return todoModel.content;
    }

    public boolean isCompleted() {
        return todoModel.completed;
    }

    private void toggleCompleted() {
        todoModel.completed = !todoModel.completed;
    }
}
