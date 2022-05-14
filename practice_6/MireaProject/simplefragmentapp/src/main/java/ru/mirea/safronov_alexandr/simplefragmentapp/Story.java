package ru.mirea.safronov_alexandr.simplefragmentapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Story {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String surname;
    public String name;
    public String age;
}
