package com.example.archdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.baurine.multitypeadapter.MultiTypeAdapter;
import com.example.archdemo.arch.AppDatabase;
import com.example.archdemo.arch.Todo;
import com.example.archdemo.databinding.ActivityMainBinding;
import com.example.archdemo.databinding.TodoModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MultiTypeAdapter adapter = new MultiTypeAdapter();
    private EditText etContent;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);

        etContent = binding.etContent;
        recyclerView = binding.recyclerView;
        initViews();

        showTodosFromDb();
    }

    private void initViews() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_add) {
            // addTodo();
            addTodoToDb();
        }
    }

    private void addTodo() {
        String content = etContent.getText().toString();
        adapter.addItem(new TodoModel(content).createItem(adapter));
        adapter.notifyDataSetChanged();
        etContent.setText("");
    }

    private void addTodoToDb() {
        String content = etContent.getText().toString();
        if (content.isEmpty()) return;
        etContent.setText("");

        Todo todo = new Todo(content);
        AppDatabase.getDb().todoDao().insert(todo);

        // refresh
        showTodosFromDb();
    }

    private void showTodosFromDb() {
        List<Todo> todos = AppDatabase.getDb().todoDao().getAll();
        adapter.clearItems();
        for (Todo todo : todos) {
            adapter.addItem(new TodoModel(todo).createItem(adapter));
        }
        adapter.notifyDataSetChanged();
    }
}
