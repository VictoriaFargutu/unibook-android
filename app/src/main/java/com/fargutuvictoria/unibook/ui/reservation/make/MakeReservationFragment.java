package com.fargutuvictoria.unibook.ui.reservation.make;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fargutuvictoria.commons.model.Classroom;
import com.fargutuvictoria.commons.model.Filter;
import com.fargutuvictoria.commons.model.FreeOption;
import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.UnibookApplication;
import com.fargutuvictoria.unibook.commons.ToFilterFrom;
import com.fargutuvictoria.unibook.ui.filter.ReservationFilterActivity;
import com.fargutuvictoria.unibook.ui.free_option.FreeOptionActivity;
import com.fargutuvictoria.unibook.ui.reservation.adapter.freeoptions.FreeOptionsCardViewAdapter;

import java.util.List;

public class MakeReservationFragment extends Fragment implements MakeReservationContract.Fragment {
    private MakeReservationPresenter makeReservationPresenter;
    private RecyclerView recyclerView;

    private TextView noFreeOptionsText;
    private String fromFilter;
    private Filter filter;

    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        makeReservationPresenter = new MakeReservationPresenter(this);
        recyclerView = view.findViewById(R.id.recycler_view);
        noFreeOptionsText = view.findViewById(R.id.no_free_options_text_view);
        noFreeOptionsText.setVisibility(View.GONE);

        Intent intent = getActivity().getIntent();
        Classroom classroom = (Classroom) intent.getSerializableExtra("classroom");
        filter = (Filter) intent.getSerializableExtra("filter");
        fromFilter = (String) intent.getSerializableExtra("toFilterFrom");
        if (classroom != null && !fromFilter.equals(ToFilterFrom.FROM_HOME) && filter == null) {
            makeReservationPresenter.loadFreeOptionsByClassroom(classroom);
        } else if (filter != null) {
            makeReservationPresenter.loadFreeOptionsByFilter(filter);
        } else {
            makeReservationPresenter.loadFreeOptions();
        }
    }

    @Override
    public void openFreeOptionActivity(FreeOption freeOption) {
        Intent intent = new Intent(getActivity(), FreeOptionActivity.class);
        intent.putExtra("toFilterFrom", fromFilter);
        intent.putExtra("freeOption", freeOption);
        intent.putExtra("filter", filter);
        startActivity(intent);
    }

    @Override
    public void openFilterActivity(FreeOption freeOption) {
        Intent intent = new Intent(getActivity(), ReservationFilterActivity.class);
        intent.putExtra("classroom", freeOption.getClassroom());
        intent.putExtra("toFilterFrom", fromFilter);
        intent.putExtra("warning", "You must select the date!");
        startActivity(intent);
    }

    @Override
    public void showOptionsLoaded(List<FreeOption> freeOptions) {
        RecyclerView.Adapter recylerAdapter = new FreeOptionsCardViewAdapter(freeOptions, makeReservationPresenter);
        recyclerView.setAdapter(recylerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(UnibookApplication.getInstance()));
        if (freeOptions.isEmpty()) {
            noFreeOptionsText.setVisibility(View.VISIBLE);
        }
    }
}
