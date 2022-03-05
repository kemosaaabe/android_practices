package ru.mirea.safronov_alexandr.dialog;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MyDateDialogFragment {
    Calendar time= Calendar.getInstance();
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            time.set(Calendar.YEAR, year);
            time.set(Calendar.MONTH, monthOfYear);
            time.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        }
    };
}
