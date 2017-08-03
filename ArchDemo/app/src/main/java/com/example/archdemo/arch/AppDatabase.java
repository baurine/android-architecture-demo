package com.example.archdemo.arch;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.example.archdemo.ArchDemoApp;

/**
 * Created by baurine on 8/3/17.
 */

@Database(entities = {Todo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract TodoDao todoDao();

    public static AppDatabase getDb() {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(ArchDemoApp.getInstance(),
                                            AppDatabase.class, "todo-db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
