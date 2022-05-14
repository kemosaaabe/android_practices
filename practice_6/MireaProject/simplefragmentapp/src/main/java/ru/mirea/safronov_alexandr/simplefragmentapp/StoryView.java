package ru.mirea.safronov_alexandr.simplefragmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class StoryView extends AppCompatActivity {
    private EditText surnameField, nameField, ageField;
    private AppDatabase appDatabase;
    private StoryDao storyDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_view);
        appDatabase = App.getInstance().getDatabase();
        storyDao = appDatabase.storyDao();

        nameField = findViewById(R.id.name_story);
        ageField = findViewById(R.id.age_story);
        surnameField = findViewById(R.id.surname_story);
    }

    public void onSave(View view) {
        Story story =new Story();
        story.name = nameField.getText().toString();
        story.age = ageField.getText().toString();
        story.surname = surnameField.getText().toString();
        storyDao.insert(story);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
