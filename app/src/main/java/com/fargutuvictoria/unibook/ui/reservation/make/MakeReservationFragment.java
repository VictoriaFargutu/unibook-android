package com.fargutuvictoria.unibook.ui.reservation.make;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fargutuvictoria.unibook.R;

public class MakeReservationFragment extends Fragment implements MakeReservationContract.Fragment{
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.make_reservation_layout, container, false);
    }
}
