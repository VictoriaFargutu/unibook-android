package com.fargutuvictoria.unibook.ui.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.unibook.R;

import java.util.List;

/**
 * Created by fargutuvictoria on 05/05/2018.
 */

public class ClassroomListViewAdapter extends RecyclerView.Adapter<ClassroomListViewAdapter.ClassroomViewHolder> {
    private List<Classroom> classrooms;
    private ActionHandler actionHandler;

    public ClassroomListViewAdapter(List<Classroom> classrooms, ActionHandler actionHandler) {
        this.classrooms = classrooms;
        this.actionHandler = actionHandler;
    }

    @Override
    public ClassroomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_item_layout, parent, false);
        return new ClassroomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassroomViewHolder holder, int position) {
        Classroom classroom = classrooms.get(position);
        holder.bind(classroom);
    }

    @Override
    public int getItemCount() {
        return classrooms.size();
    }

    public class ClassroomViewHolder extends RecyclerView.ViewHolder {
        private final TextView classroomName;
        private final TextView classroomCapacityNr;
        private final TextView classroomHasProjector;
        private final ImageView goToReservation;
        private Classroom classroom;

        public ClassroomViewHolder(final View itemView) {
            super(itemView);
            classroomName = itemView.findViewById(R.id.room_name);
            classroomCapacityNr = itemView.findViewById(R.id.room_capacity_nr);
            classroomHasProjector = itemView.findViewById(R.id.projector_bool);
            goToReservation = itemView.findViewById(R.id.go_to_reservation);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "Under development", Toast.LENGTH_SHORT).show();
                }
            });

            goToReservation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    actionHandler.onArrowClick(classroom);
                }
            });
        }

        private void bind(Classroom classroom) {
//            classroomName.setText(classroom.getName());
            this.classroom = classroom;
        }
    }
}
