package ru.mirea.safronov_alexandr.mireaproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Story {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String surname;
    public String age;
}