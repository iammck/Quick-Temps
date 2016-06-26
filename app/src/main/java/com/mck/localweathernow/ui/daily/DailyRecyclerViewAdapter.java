package com.mck.localweathernow.ui.daily;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mck.localweathernow.R;

/**
 * {@link RecyclerView.Adapter} that can display daily weather forecast.
 */
class DailyRecyclerViewAdapter extends RecyclerView.Adapter<DailyRecyclerViewAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_daily, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View mView;

        ViewHolder(View view) {
            super(view);
            mView = view;
        }
    }
}