package com.example.bnvvp2tlroomrcvcvtest02;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bnvvp2tlroomrcvcvtest02.room.DB_r;
import com.example.bnvvp2tlroomrcvcvtest02.room.EventEntity;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditEvent extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText event, category, starting_time, starting_date, ending_time, ending_date;
    Button bt;
    Toolbar tb;

    EventEntity eventEntity = new EventEntity();

    //設定日期格式
    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());

    //設定時間格式
    SimpleDateFormat tf = new SimpleDateFormat("hh:mm a", Locale.US);

    Calendar c;
    Calendar ctmp;
    static int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);

        Bundle bundle = getIntent().getExtras();
        position = bundle.getInt("position");

        uiInit();
        varInit();
        registerListener();


    }


    private void uiInit() {
        tb = findViewById(R.id.toolbar);
        tb.setTitle("Edit Event");
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        bt = findViewById(R.id.done);

        event = findViewById(R.id.event);
        event.setText(DB_r.getEventName(this, position));

        category = findViewById(R.id.category);
        starting_time = findViewById(R.id.starting_time);
        starting_date = findViewById(R.id.starting_date);
        ending_time = findViewById(R.id.ending_time);
        ending_date = findViewById(R.id.ending_date);


    }

    private void varInit() {
        c = Calendar.getInstance();
        ctmp = Calendar.getInstance();
        ctmp.add(Calendar.HOUR_OF_DAY, 1);

        starting_time.setText(tf.format(c.getTime()));
        ending_time.setText(tf.format(ctmp.getTime()));

        starting_date.setText(df.format(c.getTime()));
        ending_date.setText(df.format(ctmp.getTime()));
    }


    private void registerListener() {
        bt.setOnClickListener(this);
        starting_time.setOnClickListener(this);
        starting_date.setOnClickListener(this);
        ending_time.setOnClickListener(this);
        ending_date.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.done:
//                改成upDateEvent
//                saveEvent();
                break;

            case R.id.starting_time:
                new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        c.set(Calendar.HOUR_OF_DAY, i);
                        c.set(Calendar.MINUTE, i1);
                        starting_time.setText(tf.format(c.getTime()));
                    }
                },
                        c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false).show();
                break;


            case R.id.starting_date:
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        c.set(Calendar.YEAR, i);
                        c.set(Calendar.MONTH, i1);
                        c.set(Calendar.DAY_OF_MONTH, i2);
                        starting_date.setText(df.format(c.getTime()));
                    }
                },
                        c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
                break;


            case R.id.ending_time:
                new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        c.set(Calendar.HOUR_OF_DAY, i);
                        c.set(Calendar.MINUTE, i1);
                        ending_time.setText(tf.format(c.getTime()));
                    }
                },
                        c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false).show();
                break;


            case R.id.ending_date:
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        c.set(Calendar.YEAR, i);
                        c.set(Calendar.MONTH, i1);
                        c.set(Calendar.DAY_OF_MONTH, i2);
                        ending_date.setText(df.format(c.getTime()));
                    }
                },
                        c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
                break;


        }
    }


    int qwe = 3;

    //資料庫

    EventEntity oldOne;

    private boolean saveEvent() {
        EventEntity itemValue = new EventEntity();

        //  1. 名稱，沒有名稱則不儲存(之後可改
        String tmp = event.getText().toString();
        if (tmp == null || "".equals(tmp)){
            Toast.makeText(this, "沒有名稱！", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            itemValue.setEventName(event.getText().toString());
        }

        itemValue.setCategory(category.getText().toString());
        itemValue.setStaringTime(starting_time.getText().toString());
        itemValue.setStaringDate(starting_date.getText().toString());
        itemValue.setEndingTime(ending_time.getText().toString());
        itemValue.setEndingDate(ending_date.getText().toString());

        //避免重複儲存相同事件
        if (oldOne == null || !oldOne.equals(itemValue)) {
            oldOne = itemValue;
            DB_r.insert(this, itemValue);
//            Toast.makeText(this, Integer.toString(DB_r.getCount(this)), Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(this, "重複事件！", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    //    點擊空白處收起鍵盤
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (EditEvent.this.getCurrentFocus() != null) {
                if (EditEvent.this.getCurrentFocus().getWindowToken() != null) {
                    imm.hideSoftInputFromWindow(EditEvent.this.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
        return super.onTouchEvent(event);
    }

}
