package com.fargutuvictoria.unibook.ui.home.adapter.reservation.freeoptions;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fargutuvictoria.commons.model.FreeOption;
import com.fargutuvictoria.unibook.R;

import java.util.List;

public class FreeOptionsCardViewAdapter extends RecyclerView.Adapter<FreeOptionsCardViewAdapter.FreeOptionsViewHolder> {
    private List<FreeOption> freeOptions;

    public FreeOptionsCardViewAdapter(List<FreeOption> freeOptions) {
        this.freeOptions = freeOptions;
    }

    @Override
    public FreeOptionsCardViewAdapter.FreeOptionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.make_reservation_item_layout, parent, false);
        return new FreeOptionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FreeOptionsCardViewAdapter.FreeOptionsViewHolder holder, int position) {
        FreeOption freeOption = freeOptions.get(position);
        holder.bind(freeOption);
    }

    @Override
    public int getItemCount() {
        return freeOptions.size();
    }

    public class FreeOptionsViewHolder extends RecyclerView.ViewHolder {
        private final TextView freeOptionClassroom;
        private FreeOption freeOption;

        public FreeOptionsViewHolder(View itemView) {
            super(itemView);
            freeOptionClassroom = itemView.findViewById(R.id.free_reservation_classroom);
        }

        private void bind(FreeOption freeOption) {
            freeOptionClassroom.setText(freeOption.getClassroom().getName());

            this.freeOption = freeOption;
        }
    }
}
