package com.example.bnvvp2tlroomrcvcvtest02.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "event")
public class EventEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String eventName;
    private String category;

    private String staringTime;
    private String endingTime;
    private String staringDate;
    private String endingDate;

    private String detail;
    private String remind;

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public String getStaringDate() {
        return staringDate;
    }

    public void setStaringDate(String staringDate) {
        this.staringDate = staringDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStaringTime() {
        return staringTime;
    }

    public void setStaringTime(String staringTime) {
        this.staringTime = staringTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean equals(Object arg) {
        if (((arg != null) && (arg instanceof EventEntity))
                && (this.eventName.equals(((EventEntity) arg).getEventName()))
                && (this.category.equals(((EventEntity) arg).getCategory()))
                && (this.staringTime.equals(((EventEntity) arg).getStaringTime()))
                && (this.staringDate.equals(((EventEntity) arg).getStaringDate()))
                && (this.endingTime.equals(((EventEntity) arg).getEndingTime()))
                && (this.endingDate.equals(((EventEntity) arg).getEndingDate()))
        ) {
            return true;
        }
        return false;
    }

}
