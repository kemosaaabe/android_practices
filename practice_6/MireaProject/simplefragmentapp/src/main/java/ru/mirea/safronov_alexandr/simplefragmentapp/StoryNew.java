package ru.mirea.safronov_alexandr.simplefragmentapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StoryNew extends RecyclerView.Adapter<StoryNew.StoryViewHolder>{
    private List<Story> stories;

    public StoryNew(List<Story> stories){
        this.stories = stories;
    }

    public void addStoryToList(Story story){
        stories.add(story);
    }

    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stories, parent, false);
        return new StoryViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        Story story = stories.get(position);
        holder.nameField.setText(story.name);
        holder.ageField.setText(story.age);
        holder.surnameField.setText(story.surname);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder{
        public TextView surnameField;
        public TextView nameField;
        public TextView ageField;
        public StoryViewHolder(View itemView) {
            super(itemView);
            nameField = itemView.findViewById(R.id.name);
            ageField =  itemView.findViewById(R.id.age);
            surnameField = itemView.findViewById(R.id.surname);
        }
    }
}
