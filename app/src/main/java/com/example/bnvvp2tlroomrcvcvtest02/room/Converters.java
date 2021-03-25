package com.example.bnvvp2tlroomrcvcvtest02.room;

import androidx.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Converters {

    static SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
    @TypeConverter
    public static String calendarToToday(Calendar c) {

        //當c為空值回傳空值，否則就回傳經過型別轉換後的字串
        return c == null ? null : df.format(c.getTime());
    }

//    @TypeConverter
//    public static Date fromTimestamp(Long value) {
//        return value == null ? null : new Date(value);
//    }
}
