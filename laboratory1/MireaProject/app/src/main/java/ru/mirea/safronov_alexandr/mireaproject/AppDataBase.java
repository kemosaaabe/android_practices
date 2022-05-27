package ru.mirea.safronov_alexandr.mireaproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.mirea.safronov_alexandr.mireaproject.ui.Book.Book;
import ru.mirea.safronov_alexandr.mireaproject.ui.Book.BookDao;

@Database(entities = {Story.class, Book.class}, version = 3, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase{
    public abstract StoryDao storyDao();
    public abstract BookDao bookDao();
}
