package com.example.bnvvp2tlroomrcvcvtest02;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.bnvvp2tlroomrcvcvtest02.room.Converters;
import com.example.bnvvp2tlroomrcvcvtest02.room.EventEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Dao
public interface EventDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(EventEntity item);

    @Update
    public void update(EventEntity item);

    @Delete
    public void delete(EventEntity item);

    //由ID取得資料
    @Query("SELECT * FROM event WHERE id = :id")
    public EventEntity getByID(int id);

    //獲得總筆數
    @Query("SELECT COUNT(*) FROM event")
    public int getCount();

    //取得所有資料，大多用來驗證程式碼有無錯誤
    @Query("SELECT * FROM event")
    public Cursor getAll();

    //取得今天的所有資料
    //Calendar的資料要轉換成字串
    @Query("SELECT * FROM event WHERE staringDate = :today")
    @TypeConverters({Converters.class})
    public List<EventEntity> getDailyEvents(Calendar today);
//    public Cursor getDailyData(Calendar c);





    //取出ArrayList裡的ID用的
    @Query("SELECT id FROM event")
    public int getId();

    //取出ArrayList裡的eventName用的
    @Query("SELECT eventName FROM event WHERE id = :position")
    public String getEventName(int position);

    //取出ArrayList裡的category用的
    @Query("SELECT category FROM event")
    public String getCateGory();

    //取出ArrayList裡的staringTime用的
    @Query("SELECT staringTime FROM event")
    public String getStaringTime();

    //取出ArrayList裡的staringDate用的
    @Query("SELECT staringDate FROM event")
    public String getStaringDate();

    //取出ArrayList裡的endingTime用的
    @Query("SELECT endingTime FROM event")
    public String getEndingTime();

    //取出ArrayList裡的endingDate用的
    @Query("SELECT endingDate FROM event")
    public String getEndingDate();

    //取出ArrayList裡的detail用的
    @Query("SELECT detail FROM event")
    public String getDetail();





}
