package ru.mirea.safronov_alexandr.mireaproject.ui.Book;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface BookDao {
    @Query("SELECT * FROM book")
    List<Book> getAll();
    @Query("SELECT * FROM book WHERE id = :id")
    Book getById(long id);
    @Insert
    void insert(Book book);
    @Update
    void update(Book book);
    @Delete
    void delete(Book book);
}
