package com.fargutuvictoria.unibook.ui.reservation.make;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.commons.model.FreeOption;
import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.ui.home.adapter.reservation.freeoptions.FreeOptionsCardViewAdapter;

import java.util.List;

public class MakeReservationFragment extends Fragment implements MakeReservationContract.Fragment {
    private MakeReservationPresenter makeReservationPresenter;
    private RecyclerView recyclerView;

    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        makeReservationPresenter = new MakeReservationPresenter(this);
        recyclerView = view.findViewById(R.id.recycler_view);
        Intent intent = getActivity().getIntent();
        Classroom classroom = (Classroom) intent.getSerializableExtra("classroom");
        if (classroom != null) {
            makeReservationPresenter.loadFreeOptions(classroom);
        }
    }

    @Override
    public void showOptionsLoaded(List<FreeOption> freeOptions) {
        RecyclerView.Adapter recylerAdapter = new FreeOptionsCardViewAdapter(freeOptions);
        recyclerView.setAdapter(recylerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(UnibookApplication.getInstance()));
    }
}
