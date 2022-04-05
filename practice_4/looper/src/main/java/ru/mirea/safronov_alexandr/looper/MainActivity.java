package ru.mirea.safronov_alexandr.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    int counter;
    MyLooper myLooper = new MyLooper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLooper.start();
    }

    public void onClick(View view) throws InterruptedException {
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("KEY", "Я студент");
        TimeUnit.SECONDS.sleep(20);
        msg.setData(bundle);
        if (myLooper != null) {
            myLooper.handler.sendMessage(msg);
        }

    }
}