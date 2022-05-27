package ru.mirea.safronov_alexandr.mireaproject.ui.Book;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String author;
}
