package ru.mirea.safronov_alexandr.mireaproject.ui.Book;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.mirea.safronov_alexandr.mireaproject.R;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.StoryViewHolder> {
    public List<Book> books;

    public BookAdapter(List<Book> books){
        this.books = books;
    }

    @Override
    public BookAdapter.StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        return new BookAdapter.StoryViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.StoryViewHolder holder, int position) {
        Book book = books.get(position);
        holder.nameField.setText(book.name);
        holder.authorField.setText(book.author);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder{
        public TextView nameField;
        public TextView authorField;
        public StoryViewHolder(View itemView) {
            super(itemView);
            nameField = itemView.findViewById(R.id.name_in_item_book);
            authorField = itemView.findViewById(R.id.author_in_item_book);
        }
    }
}
