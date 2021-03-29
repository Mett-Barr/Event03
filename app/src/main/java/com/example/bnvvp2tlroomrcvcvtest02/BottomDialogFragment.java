package com.example.bnvvp2tlroomrcvcvtest02;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.bnvvp2tlroomrcvcvtest02.room.DB_r;
import com.example.bnvvp2tlroomrcvcvtest02.room.EventEntity;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class BottomDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    Window window;
    WindowManager.LayoutParams layoutParams;
    Dialog dialog;

    private BottomSheetBehavior<View> behavior;
    private int topOffset = 0;

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

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        if (getContext() == null) {
//            return super.onCreateDialog(savedInstanceState);
//        }
//        return new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        // 设置软键盘不自动弹出
//        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
//        FrameLayout bottomSheet = dialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
//        if (bottomSheet != null) {
//            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomSheet.getLayoutParams();
//            layoutParams.height = getHeight();
//            behavior = BottomSheetBehavior.from(bottomSheet);
//            // 初始为展开状态
//            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//        }
//    }
//
//    private int getHeight() {
//        int height = 1920;
//        if (getContext() != null) {
//            WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
//            Point point = new Point();
//            if (wm != null) {
//                // 使用Point已经减去了状态栏高度
//                wm.getDefaultDisplay().getSize(point);
//                height = point.y - getTopOffset();
//            }
//        }
//        return height;
//    }
//
//    public int getTopOffset() {
//        return topOffset;
//    }
//    public void setTopOffset(int topOffset) {
//        this.topOffset = topOffset;
//    }
//    public BottomSheetBehavior<FrameLayout> getBehavior() {
//        return behavior;
//    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.BottomSheetDialogTheme);
//        behavior=BottomSheetBehavior.from(getDialog().findViewById(R.id.design_bottom_sheet));
//        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        Log.d(TAG, "onCreate: ");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog: ");
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public Dialog getDialog() {
        Log.d(TAG, "getDialog: ");
        return super.getDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_event, null);

        dialog = this.getDialog();

//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        assert dialog != null;
        window = dialog.getWindow();

        assert window != null;
        window.getDecorView().setPadding(16, 0, 16, 0);
        layoutParams = window.getAttributes();

//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//
//        layoutParams.gravity = Gravity.BOTTOM;

        layoutParams.windowAnimations = R.style.BottomDialogAnimation;
        window.setAttributes(layoutParams);

        //style裡面設定過所以先註解掉
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Log.d(TAG, "onCreateView: time");
        init(view);
        Log.d(TAG, "onCreateView: return view");

        view.post(new Runnable() {
            @Override
            public void run() {
                //R.id.design_bottom_sheet基本是固定的,不用担心后面API的更改
                behavior=BottomSheetBehavior.from(getDialog().findViewById(R.id.design_bottom_sheet));
                behavior.setHideable(false);//此处设置表示禁止BottomSheetBehavior的执行
            }
        });

        test_num = 1;
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    private void init(View v) {
        uiInit(v);
        setOnClickListener();
        varInit();
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

//                        test();
                        break;
                    case 2:
                        mTimePicker.setVisibility(View.GONE);
                        mDatePicker.setVisibility(View.VISIBLE);
                        test_num = 3;

//                        test();
                        break;
                    case 3:
                        mDatePicker.setVisibility(View.GONE);
                        constraintLayoutText.setVisibility(View.VISIBLE);
                        test_num = 1;

//                        test();
                        break;
                }
        }
    }

    void test() {
//        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        window.setAttributes(layoutParams);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        window.getDecorView().setPadding(16, 0, 16, 0);
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