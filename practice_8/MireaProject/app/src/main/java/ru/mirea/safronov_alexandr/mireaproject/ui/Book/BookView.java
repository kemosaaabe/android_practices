package ru.mirea.safronov_alexandr.mireaproject.ui.Book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ru.mirea.safronov_alexandr.mireaproject.App;
import ru.mirea.safronov_alexandr.mireaproject.AppDataBase;
import ru.mirea.safronov_alexandr.mireaproject.MainActivity;
import ru.mirea.safronov_alexandr.mireaproject.R;

public class BookView extends AppCompatActivity {
    public EditText nameField;
    public EditText authorField;
    public AppDataBase appDataBase;
    public BookDao bookDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);
        appDataBase = App.getInstance().getDatabase();
        bookDao = appDataBase.bookDao();

        nameField = findViewById(R.id.editTextNameBook);
        authorField = findViewById(R.id.editTextAuthor);
    }

    public void onSave(View view) {
        Book book = new Book();
        book.name = nameField.getText().toString();
        book.author = authorField.getText().toString();
        bookDao.insert(book);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}