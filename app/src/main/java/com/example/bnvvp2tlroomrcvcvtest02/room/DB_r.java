package com.example.bnvvp2tlroomrcvcvtest02.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bnvvp2tlroomrcvcvtest02.EventDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Database(entities = {EventEntity.class}, version = 1)
public abstract class DB_r extends RoomDatabase {
    public abstract EventDAO eventDao();

    //用字串定義資料庫的名稱
    private final static String databaseName = "event.db";

    //建立DB建立 DB_r 類別的 instance
    private static volatile DB_r instance;

    //通用模式單例程式碼
    private static DB_r getDatabase(final Context context) {

        //首先檢查instance是否存在
        if (instance == null){
            synchronized (DB_r.class){
                //若不存在則建議一個資料庫
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            DB_r.class, databaseName).allowMainThreadQueries()
                            .build();
                }
            }
        }
        //若存在則回傳instance
        return instance;
    }

    public static void insert(Context c, EventEntity e) {
        getDatabase(c).eventDao().insert(e);
    }

    public static void update(Context c, EventEntity e) {
        getDatabase(c).eventDao().update(e);
    }

    public static void delete(Context c, EventEntity e) {
        getDatabase(c).eventDao().delete(e);
    }

    public static int getCount(Context c) {
         return getDatabase(c).eventDao().getCount();
    }

    public static EventEntity getById(Context c, int position){
        return getDatabase(c).eventDao().getByID(position);
    }

    public static int getId(Context c){
        return getDatabase(c).eventDao().getId();
    }

    //獲取當日事件
    public static List<EventEntity> getDailyEvents(Context c, Calendar today){
        return getDatabase(c).eventDao().getDailyEvents(today);
    }

    public static String getEventName(Context c, int position) {
        return getDatabase(c).eventDao().getEventName(position);
    }

    public static void insert2 (final Context c, final EventEntity e){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run () {
                //Insert Data
                //新增資一筆資料的工作放到背景去執行
                DB_r.getDatabase(c).eventDao().insert(e);
            }
        });
    }
}