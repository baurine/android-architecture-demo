package com.example.archdemo;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.baurine.multitypeadapter.MultiTypeAdapter;
import com.example.archdemo.arch.AppDatabase;
import com.example.archdemo.arch.MusicPlayer;
import com.example.archdemo.arch.Todo;
import com.example.archdemo.databinding.ActivityMainBinding;
import com.example.archdemo.databinding.TodoModel;

import java.util.Date;
import java.util.List;

public class MainActivity extends LifecycleActivity {

    private MultiTypeAdapter adapter = new MultiTypeAdapter();
    private EditText etContent;
    private RecyclerView recyclerView;

    private MutableLiveData<String> contentLiveData = new MutableLiveData<>();

    private Date now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);

        etContent = binding.etContent;
        recyclerView = binding.recyclerView;
        initViews();

        showTodosFromDb(AppDatabase.getDb().todoDao().getAll());

        observeLiveData();

        observeLiveTodos();

        getLifecycle().addObserver(new MusicPlayer());
        // getLifecycle().addObserver(new OtherObservers());

        now = new Date();
        binding.tvDate.setText(now.toString());
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

        // contentLiveData.setValue(content);
        contentLiveData.setValue(now.toString());

        Todo todo = new Todo(content);
        AppDatabase.getDb().todoDao().insert(todo);

        // refresh
        // showTodosFromDb(AppDatabase.getDb().todoDao().getAll());
    }

    private void showTodosFromDb(List<Todo> todos) {
        // List<Todo> todos = AppDatabase.getDb().todoDao().getAll();
        adapter.clearItems();
        for (Todo todo : todos) {
            adapter.addItem(new TodoModel(todo).createItem(adapter));
        }
        adapter.notifyDataSetChanged();
    }

    private void observeLiveData() {
        contentLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void observeLiveTodos() {
        AppDatabase.getDb().todoDao().getLiveTodos()
                .observe(this, new Observer<List<Todo>>() {
                    @Override
                    public void onChanged(@Nullable List<Todo> todos) {
                        showTodosFromDb(todos);
                    }
                });
    }
}
