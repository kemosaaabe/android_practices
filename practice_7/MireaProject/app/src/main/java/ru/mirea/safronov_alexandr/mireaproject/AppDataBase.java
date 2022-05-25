package ru.mirea.safronov_alexandr.mireaproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Story.class}, version = 2, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase{
    public abstract StoryDao storyDao();
}
