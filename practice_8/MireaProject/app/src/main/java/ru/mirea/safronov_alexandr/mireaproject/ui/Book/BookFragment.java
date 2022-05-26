package ru.mirea.safronov_alexandr.mireaproject.ui.Book;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ru.mirea.safronov_alexandr.mireaproject.App;
import ru.mirea.safronov_alexandr.mireaproject.AppDataBase;
import ru.mirea.safronov_alexandr.mireaproject.R;

public class BookFragment extends Fragment {
    private List<Book> books;
    private RecyclerView recyclerView;
    private BookDao bookDao;
    private AppDataBase appDataBase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);

        appDataBase = App.getInstance().getDatabase();

        bookDao = appDataBase.bookDao();
        books = bookDao.getAll();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView = view.findViewById(R.id.recyclerViewBook);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        FloatingActionButton button = view.findViewById(R.id.floatingActionButtonBook);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), BookView.class);
                startActivity(intent);
            }
        });

        BookAdapter adapter = new BookAdapter(books);
        recyclerView.setAdapter(adapter);

        return view;
    }
}