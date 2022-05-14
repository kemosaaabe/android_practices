package ru.mirea.safronov_alexandr.simplefragmentapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Story.class}, version = 2)
abstract class AppDatabase extends RoomDatabase{
    public abstract StoryDao storyDao();
}
