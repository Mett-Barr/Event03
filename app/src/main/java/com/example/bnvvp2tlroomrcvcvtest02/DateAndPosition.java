package com.example.bnvvp2tlroomrcvcvtest02;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateAndPosition {

    SimpleDateFormat dfymd = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat dfmd = new SimpleDateFormat("MM/dd");
    SimpleDateFormat tf = new SimpleDateFormat("hh:mm a", Locale.US);

    Calendar calendar;

    public int dateConvertToPosition(Calendar date){
        calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH) - date.get(Calendar.DAY_OF_MONTH) + 50;
    }

    public Calendar positionConvertToDate(int position){
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, position - 50);
        return calendar;
    }

    public String positionConvertToMMdd(int position) {
        return dfmd.format(positionConvertToDate(position).getTime());
    }

    public String getDateTextYMD(Calendar c){
        return dfymd.format(c.getTime());
    }

    public String getTimeTextHMa(Calendar c){
        return tf.format(c.getTime());
    }
}
