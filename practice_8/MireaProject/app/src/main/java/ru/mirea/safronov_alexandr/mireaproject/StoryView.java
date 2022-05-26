package ru.mirea.safronov_alexandr.mireaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class StoryView extends AppCompatActivity {
    public EditText nameField;
    public EditText surnameField;
    public EditText ageField;
    public AppDataBase appDataBase;
    public StoryDao storyDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_view);
        appDataBase = App.getInstance().getDatabase();
        storyDao = appDataBase.storyDao();

        nameField = findViewById(R.id.editTextName);
        surnameField = findViewById(R.id.editTextSurname);
        ageField = findViewById(R.id.editTextAge);
    }

    public void onSave(View view) {
        Story story = new Story();
        story.name = nameField.getText().toString();
        story.surname = surnameField.getText().toString();
        story.age = ageField.getText().toString();
        storyDao.insert(story);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}