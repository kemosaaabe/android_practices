package ru.mirea.safronov_alexandr.mireaproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class StoryFragment extends Fragment {
    private List<Story> stories;
    private RecyclerView recyclerView;
    private StoryDao storyDao;
    private AppDataBase appDataBase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story, container, false);

        appDataBase = App.getInstance().getDatabase();

        storyDao = appDataBase.storyDao();
        stories = storyDao.getAll();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        FloatingActionButton button = view.findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), StoryView.class);
                startActivity(intent);
            }
        });

        StoryAdapter adapter = new StoryAdapter(stories);
        recyclerView.setAdapter(adapter);

        return view;
    }
}