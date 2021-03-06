package ru.mirea.safronov_alexandr.livedata;

import android.icu.text.SimpleDateFormat;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.Calendar;
import java.util.Date;

public class TimeLiveData {
    private static final MutableLiveData<Long> data = new MutableLiveData<Long>();
    //sets latest time to LiveData
    static LiveData<Long> getTime(){
        data.setValue(new Date().getTime());
        return data;
    }
    static void setTime(){
        data.setValue(new Date().getTime());
    }

    private static LiveData getStringTime = Transformations.map(data, new Function<Long, String>() {
        @Override
        public String apply(Long input) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.format(calendar.getTime());
        }
    });

    static LiveData<String> getDate(){
        return getStringTime;
    }

}
