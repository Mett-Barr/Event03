package com.example.bnvvp2tlroomrcvcvtest02;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.example.bnvvp2tlroomrcvcvtest02.ui.home.HomeFragment;

import java.util.ArrayList;

public class PagerAdapterDay extends FragmentStateAdapter {

    public ArrayList<DailyFragment> mDailyFragments = new ArrayList<>();

    DateAndPosition dap = new DateAndPosition();

    public PagerAdapterDay(@NonNull HomeFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        DailyFragment dailyFragment = new DailyFragment();
        dailyFragment.position = position;
        dailyFragment.mPagerAdapterDay = this;
        return dailyFragment;
    }


    @Override
    public int getItemCount() {
        return 101;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull FragmentViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
    }
}
