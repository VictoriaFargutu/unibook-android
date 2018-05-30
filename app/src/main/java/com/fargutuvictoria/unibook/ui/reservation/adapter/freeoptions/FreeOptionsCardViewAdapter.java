package com.fargutuvictoria.unibook.ui.reservation.adapter.freeoptions;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fargutuvictoria.commons.model.FreeOption;
import com.fargutuvictoria.unibook.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class FreeOptionsCardViewAdapter extends RecyclerView.Adapter<FreeOptionsCardViewAdapter.FreeOptionsViewHolder> {
    private List<FreeOption> freeOptions;
    private ActionHandler actionHandler;

    public FreeOptionsCardViewAdapter(List<FreeOption> freeOptions, ActionHandler actionHandler) {
        this.freeOptions = freeOptions;
        this.actionHandler = actionHandler;
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
        private final TextView classroomType;
        private final TextView date;
        private final TextView weekType;
        private final TextView day;
        private final TextView hour;
        private final TextView studentsGroup;

        private FreeOption freeOption;

        public FreeOptionsViewHolder(View itemView) {
            super(itemView);
            freeOptionClassroom = itemView.findViewById(R.id.free_reservation_classroom);
            classroomType = itemView.findViewById(R.id.free_reservation_classroom_type);
            date = itemView.findViewById(R.id.free_reservation_date);
            weekType = itemView.findViewById(R.id.free_reservation_week_type);
            day = itemView.findViewById(R.id.free_reservation_day);
            hour = itemView.findViewById(R.id.free_reservation_hour);
            studentsGroup = itemView.findViewById(R.id.free_reservation_students_group);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actionHandler.onArrowClick(freeOption);
                }
            });
        }

        private void bind(FreeOption freeOption) {
            freeOptionClassroom.setText(freeOption.getClassroom().getName());
            classroomType.setText(freeOption.getClassroom().getType());

            if (freeOption.getDate() != null) {
                String myFormat = "dd/MM/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                date.setText(sdf.format(freeOption.getDate()));
            }
            weekType.setText(freeOption.getWeekType().name());
            day.setText(freeOption.getDay().name());
            hour.setText(freeOption.getHour());
            if (freeOption.getStudentsGroup() != null) {
                studentsGroup.setText(freeOption.getStudentsGroup().getName());
            } else {
                studentsGroup.setText("-");
            }
            this.freeOption = freeOption;
        }
    }
}
