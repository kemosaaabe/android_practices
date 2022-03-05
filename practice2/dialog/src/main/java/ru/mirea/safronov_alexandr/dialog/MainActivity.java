package ru.mirea.safronov_alexandr.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickShowDialog(View view) {
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void onClickShowTime(View view) {
        MyTimeDialogFragment fragment = new MyTimeDialogFragment();
        new TimePickerDialog(this, fragment.t,
                fragment.time.get(Calendar.HOUR_OF_DAY),
                fragment.time.get(Calendar.MINUTE), true)
                .show();
    }

    public void onClickShowDate(View view) {
        MyDateDialogFragment fragment = new MyDateDialogFragment();
        new DatePickerDialog(MainActivity.this, fragment.d,
                fragment.time.get(Calendar.YEAR),
                fragment.time.get(Calendar.MONTH),
                fragment.time.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    public void onClickShowProgress(View view) {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Title");
        pd.setMessage("Message");
        // добавляем кнопку
        pd.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        pd.show();
    }

    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Иду дальше\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Нет\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"На паузе\"!",
                Toast.LENGTH_LONG).show();
    }
}