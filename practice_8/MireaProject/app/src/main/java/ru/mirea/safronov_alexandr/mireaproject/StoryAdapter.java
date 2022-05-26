package ru.mirea.safronov_alexandr.mireaproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder>{

    public List<Story> stories;

    public StoryAdapter(List<Story> stories){
        this.stories = stories;
    }

    public void addStoryToList(Story story){
        stories.add(story);
    }

    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view, parent, false);
        return new StoryViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        Story story = stories.get(position);
        holder.nameField.setText(story.name);
        holder.surnameField.setText(story.surname);
        holder.ageField.setText(story.age);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder{
        public TextView nameField;
        public TextView surnameField;
        public TextView ageField;
        public StoryViewHolder(View itemView) {
            super(itemView);
            nameField = itemView.findViewById(R.id.name_in_item);
            ageField =  itemView.findViewById(R.id.age_in_item);
            surnameField = itemView.findViewById(R.id.surname_in_item);
        }
    }
}
