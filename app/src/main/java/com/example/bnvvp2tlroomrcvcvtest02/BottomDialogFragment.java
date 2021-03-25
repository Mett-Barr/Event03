package com.example.bnvvp2tlroomrcvcvtest02;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.bnvvp2tlroomrcvcvtest02.room.DB_r;
import com.example.bnvvp2tlroomrcvcvtest02.room.EventEntity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Objects;

public class BottomDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    ConstraintLayout vg;
    ConstraintLayout mConstraintLayout;
    ConstraintLayout constraintLayoutText;
    TextInputEditText starting_time, starting_date, ending_time, ending_date;
    EditText event, category;
    Button save, test;
    int test_num = 1;

    TimePicker mTimePicker;
    DatePicker mDatePicker;
    int timeHeight;
    int dateHeight;
    int textHeight;

    public int position;
    DateAndPosition dap = new DateAndPosition();

    public PagerAdapterDay mPagerAdapterDay;

    Calendar start, end;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = this.getDialog().getWindow();

        assert window != null;
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

//        lp.gravity = Gravity.BOTTOM;

        lp.windowAnimations = R.style.BottomDialogAnimation;
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final View view = inflater.inflate(R.layout.new_event, null);
        uiInit(view);
        setOnClickListener();
        varInit();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.BottomSheetDialogTheme);
    }

    private void uiInit(View v) {
        vg = v.findViewById(R.id.transition_layout);
        mConstraintLayout = v.findViewById(R.id.constraintLayoutAll);
        constraintLayoutText = v.findViewById(R.id.constraintLayoutText);

        event = v.findViewById(R.id.textView);
        category = v.findViewById(R.id.textView2);
        starting_time = v.findViewById(R.id.starting_time);
        starting_date = v.findViewById(R.id.starting_date);
        ending_time = v.findViewById(R.id.ending_time);
        ending_date = v.findViewById(R.id.ending_date);
        save = v.findViewById(R.id.button2);
        test = v.findViewById(R.id.test);

        mTimePicker = v.findViewById(R.id.TimePicker);
//        mTimePicker.setVisibility(View.GONE);

        mDatePicker = v.findViewById(R.id.DatePicker);
//        mDatePicker.setVisibility(View.GONE);
    }

    private void setOnClickListener() {
        starting_time.setOnClickListener(this);
        starting_date.setOnClickListener(this);
        ending_time.setOnClickListener(this);
        ending_date.setOnClickListener(this);
        save.setOnClickListener(this);
        test.setOnClickListener(this);
    }

    private void varInit() {
        start = dap.positionConvertToDate(position);

        end = dap.positionConvertToDate(position);
        end.add(Calendar.HOUR_OF_DAY, 1);

        starting_time.setText(dap.getTimeTextHMa(start));
        starting_date.setText(dap.getDateTextYMD(start));
        ending_time.setText(dap.getTimeTextHMa(end));
        ending_date.setText(dap.getDateTextYMD(end));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.starting_time:

                pickTime();
//                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
//                        c.set(Calendar.HOUR_OF_DAY, i);
//                        c.set(Calendar.MINUTE, i1);
//                        starting_time.setText(tf.format(c.getTime()));
//                    }
//                },
//                        c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false).show();
                break;


            case R.id.starting_date:
                pickDate();
//                new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                        c.set(Calendar.YEAR, i);
//                        c.set(Calendar.MONTH, i1);
//                        c.set(Calendar.DAY_OF_MONTH, i2);
//                        starting_date.setText(df.format(c.getTime()));
//                    }
//                },
//                        c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
                break;


            case R.id.ending_time:
//                new TimePickerDialog(requireContext(), new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
//                        c.set(Calendar.HOUR_OF_DAY, i);
//                        c.set(Calendar.MINUTE, i1);
//                        ending_time.setText(tf.format(c.getTime()));
//                    }
//                },
//                        c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false).show();
                break;


            case R.id.ending_date:
//                new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                        c.set(Calendar.YEAR, i);
//                        c.set(Calendar.MONTH, i1);
//                        c.set(Calendar.DAY_OF_MONTH, i2);
//                        ending_date.setText(df.format(c.getTime()));
//                    }
//                },
//                        c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
                break;

            case R.id.button2:
                saveEvent();

                for (DailyFragment df : mPagerAdapterDay.mDailyFragments) {
                    if (df.position >= position - 2 && df.position <= position + 2) {
                        df.mAdapter.dataChange();
                    }
                }

                dismiss();
                break;

            case R.id.test:
                switch (test_num) {
                    case 1:
                        constraintLayoutText.setVisibility(View.GONE);
                        mTimePicker.setVisibility(View.VISIBLE);
                        test_num = 2;
                        break;
                    case 2:
                        mTimePicker.setVisibility(View.GONE);
                        mDatePicker.setVisibility(View.VISIBLE);
                        test_num = 3;
                        break;
                    case 3:
                        mDatePicker.setVisibility(View.GONE);
                        constraintLayoutText.setVisibility(View.VISIBLE);
                        test_num = 1;
                        break;
                }
        }
    }

    private void pickTime() {
        TransitionManager.beginDelayedTransition(vg);
        constraintLayoutText.setVisibility(View.GONE);
        TransitionManager.endTransitions(vg);
//        ObjectAnimator animator = ObjectAnimator.ofFloat(constraintLayoutText, "scaleY", 1f, 0f);
//        mTimePicker.setVisibility(View.VISIBLE);
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mTimePicker, "scaleY", 0f, 1f);
//        animator.start();
//        animator1.start();
//        constraintLayoutText.setVisibility(View.GONE);
    }

    private void pickDate() {
    }

    EventEntity oldOne;

    private void saveEvent() {
        EventEntity itemValue = new EventEntity();

        //  1. 名稱，沒有名稱則不儲存(之後可改
        String tmp = event.getText().toString();
        if (tmp == null || "".equals(tmp)) {
            Toast.makeText(getContext(), "沒有名稱！", Toast.LENGTH_SHORT).show();
            return;
        } else {
            itemValue.setEventName(event.getText().toString());
        }

        itemValue.setCategory(category.getText().toString());
        itemValue.setStaringTime(Objects.requireNonNull(starting_time.getText()).toString());
        itemValue.setStaringDate(Objects.requireNonNull(starting_date.getText()).toString());
        itemValue.setEndingTime(Objects.requireNonNull(ending_time.getText()).toString());
        itemValue.setEndingDate(Objects.requireNonNull(ending_date.getText()).toString());

        //避免重複儲存相同事件
        if (oldOne == null || !oldOne.equals(itemValue)) {
            oldOne = itemValue;
            DB_r.insert(getContext(), itemValue);
//            Toast.makeText(this, Integer.toString(DB_r.getCount(this)), Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(this, "重複事件！", Toast.LENGTH_SHORT).show();
        }

    }

}