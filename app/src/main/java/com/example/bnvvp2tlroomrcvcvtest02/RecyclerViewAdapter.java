package com.example.bnvvp2tlroomrcvcvtest02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bnvvp2tlroomrcvcvtest02.room.DB_r;
import com.example.bnvvp2tlroomrcvcvtest02.room.EventEntity;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Calendar;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private ArrayList<EventEntity> dailyEvents = new ArrayList<>();

    DateAndPosition dap = new DateAndPosition();
    Calendar thisDay;
    private Context context;
    private int mPosition;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public MaterialCardView cardView;
        public TextView textView;
        public Button button;

        public MyViewHolder(MaterialCardView v) {
            super(v);
            cardView = v;
            cardView.setOnClickListener(this);

            textView = (TextView) v.findViewById(R.id.text);
            textView.setOnClickListener(this);

            button = (Button) v.findViewById(R.id.done);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            switch (view.getId()) {
                case R.id.card_view:
                    Intent intent = new Intent(context, EditEvent.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", dailyEvents.get(position).getId());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                    break;
                case R.id.text:
                    notifyItemChanged(position);
                    break;
                case R.id.done:
                    DB_r.delete(context, dailyEvents.get(position));
                    dailyEvents.remove(position);
                    notifyItemRemoved(position);
                    break;
            }
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(Context c, Calendar thisDay, int position) {
        context = c;
        this.thisDay = thisDay;
        mPosition = position;
        dailyEvents.addAll(DB_r.getDailyEvents(c, dap.positionConvertToDate(position)));
//        dailyEvents.addAll(DB_r.getDailyEvents(c, thisDay));
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        MaterialCardView v = (MaterialCardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.textView.setText(dailyEvents.get(position).getEventName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dailyEvents.size();
    }

    public void itemListChange(Context c, Calendar thisDay) {
        dailyEvents.clear();
        dailyEvents.addAll(DB_r.getDailyEvents(c, thisDay));
        notifyDataSetChanged();
    }

    public void dataChange() {
        dailyEvents.clear();
        dailyEvents.addAll(DB_r.getDailyEvents(context, thisDay));
        notifyDataSetChanged();
    }

}
