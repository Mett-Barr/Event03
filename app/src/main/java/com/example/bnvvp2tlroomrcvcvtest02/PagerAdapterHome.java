package com.example.bnvvp2tlroomrcvcvtest02;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bnvvp2tlroomrcvcvtest02.ui.dashboard.DashboardFragment;
import com.example.bnvvp2tlroomrcvcvtest02.ui.home.HomeFragment;
import com.example.bnvvp2tlroomrcvcvtest02.ui.notifications.NotificationsFragment;

public class PagerAdapterHome extends FragmentStateAdapter {
    public PagerAdapterHome(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new DashboardFragment();
            case 1:
                return new HomeFragment();
            case 2:
                return new NotificationsFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
