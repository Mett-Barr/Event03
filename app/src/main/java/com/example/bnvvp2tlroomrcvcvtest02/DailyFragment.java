package com.example.bnvvp2tlroomrcvcvtest02;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DailyFragment extends Fragment implements View.OnClickListener{


    DateAndPosition dap = new DateAndPosition();
    public int position;


    public PagerAdapterDay mPagerAdapterDay;

    public RecyclerViewAdapter mAdapter;

    private RecyclerView recyclerView;

    public DailyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_blank, container, false);

        uiInit(root);
        setOnClickListener();

        recyclerView = root.findViewById(R.id.recyclerview);

        //設定RecyclerView裡的item的監聽事件
//        recyclerView.addOnItemTouchListener(new SingleItemClickListener(recyclerView, new SingleItemClickListener.OnItem);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


//        這行是把這個Fragment裡RecyclierView會用到的Data丟進Adapter裡面
//        不過資料都在DB_r裡，所以只要把Context和Date丟進去即可
        mAdapter = new RecyclerViewAdapter(getContext(), dap.positionConvertToDate(position), position);

        recyclerView.setAdapter(mAdapter);

//----------------------------------------------------------------------------
    return root;

    }

    private void uiInit(View root) {
        recyclerView = root.findViewById(R.id.recyclerview);
    }

    private void setOnClickListener() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPagerAdapterDay.mDailyFragments.add(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.dataChange();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPagerAdapterDay.mDailyFragments.remove(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        Toast.makeText(getActivity(), dap.positionConvertToMMdd(position), Toast.LENGTH_SHORT).show();
    }
}