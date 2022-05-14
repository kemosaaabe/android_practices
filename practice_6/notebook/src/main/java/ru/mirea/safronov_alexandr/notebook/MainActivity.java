package ru.mirea.safronov_alexandr.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText fileNameInput;
    private EditText textInput;
    private SharedPreferences preferences;
    private final String FILE_NAME = "file_name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileNameInput = (EditText) findViewById(R.id.name_surname);
        textInput = (EditText) findViewById(R.id.notebook);
        preferences = getPreferences(MODE_PRIVATE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        String fileName = preferences.getString(FILE_NAME, null);
        if (fileName != null){
            try(FileInputStream fileInputStream = openFileInput(fileName)){
                byte[] bytes = new byte[fileInputStream.available()];
                fileInputStream.read(bytes);
                String content = new String(bytes);
                fileNameInput.setText(fileName);
                textInput.setText(content);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        String fileName = fileNameInput.getText().toString();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(FILE_NAME, fileName);
        editor.apply();
        try(FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE)){
            fos.write(textInput.getText().toString().getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}