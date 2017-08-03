package com.example.archdemo.arch;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by baurine on 8/3/17.
 */

@Dao
public interface TodoDao {
    @Query("SELECT * FROM todos")
    List<Todo> getAll();

    @Update
    void update(Todo todo);

    @Insert
    void insert(Todo todo);

    @Delete
    void delete(Todo todo);
}
