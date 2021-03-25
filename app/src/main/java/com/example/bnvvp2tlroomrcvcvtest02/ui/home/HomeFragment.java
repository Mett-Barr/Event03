package com.example.bnvvp2tlroomrcvcvtest02.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bnvvp2tlroomrcvcvtest02.BottomDialogFragment;
import com.example.bnvvp2tlroomrcvcvtest02.DateAndPosition;
import com.example.bnvvp2tlroomrcvcvtest02.NewEvent;
import com.example.bnvvp2tlroomrcvcvtest02.PagerAdapterDay;
import com.example.bnvvp2tlroomrcvcvtest02.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment implements View.OnClickListener {

    BottomDialogFragment bdf = new BottomDialogFragment();

    DateAndPosition dap = new DateAndPosition();
    String week;
    ViewPager2 viewPager2;
    PagerAdapterDay mPagerAdapterDay;

    private FloatingActionButton fab1, fab2;
    private FloatingActionMenu fam;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        uiInit(root);
        setOnClickListener();

        viewPager2.setAdapter(mPagerAdapterDay);
        viewPager2.setOffscreenPageLimit(1);
        viewPager2.setCurrentItem(50, false);


        TabLayout tabLayout = root.findViewById(R.id.tabLayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

//                c = Calendar.getInstance();
//                c.add(Calendar.DAY_OF_MONTH, (position - 50));


                switch (dap.positionConvertToDate(position).get(Calendar.DAY_OF_WEEK)) {
                    case 1:
                        week = "日";
                        break;
                    case 2:
                        week = "一";
                        break;
                    case 3:
                        week = "二";
                        break;
                    case 4:
                        week = "三";
                        break;
                    case 5:
                        week = "四";
                        break;
                    case 6:
                        week = "五";
                        break;
                    case 7:
                        week = "六";
                        break;
                }
                week += "\n" + dap.positionConvertToMMdd(position);
                tab.setText(week);

            }
        }
        );
        tabLayoutMediator.attach();

        bdf = new BottomDialogFragment();

        Log.d(TAG, "onCreateView: ");
        return root;
    }

    private void uiInit(View root) {
        viewPager2 = root.findViewById(R.id.vp);

        mPagerAdapterDay = new PagerAdapterDay(this);

        fam = root.findViewById(R.id.fam);
        fam.setClosedOnTouchOutside(true);

        fab1 = root.findViewById(R.id.fab1);
        fab2 = root.findViewById(R.id.fab2);
    }

    private void setOnClickListener() {
        fam.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onClick(View root) {
        switch (root.getId()) {
            case R.id.fab1:
                fam.close(true);
//                bottomSheetDialog = new BottomSheetDialog(
//                        getContext(), R.style.BottomSheetDialogTheme
//                );
//                View bottomSheetView = LayoutInflater.from(getContext())
//                        .inflate(
//                                R.layout.test,
//                                (ViewGroup) root.findViewById(R.id.bottomSheetContainer)
//                        );
//                bottomSheetDialog.setContentView(bottomSheetView);
//                bottomSheetDialog.setDismissWithAnimation(true);
//                bottomSheetDialog.show();
//                Toast.makeText(getActivity(), "Click!", Toast.LENGTH_SHORT).show();

                bdf.mPagerAdapterDay = mPagerAdapterDay;
                bdf.position = viewPager2.getCurrentItem();
                bdf.show(getActivity().getSupportFragmentManager(), "4");
                break;

            case R.id.fab2:
                Intent intent = new Intent();
                intent.setClass(requireActivity(), NewEvent.class);
                Toast.makeText(getActivity(), "Click!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        viewPager2.setCurrentItem(50, true);
    }
}