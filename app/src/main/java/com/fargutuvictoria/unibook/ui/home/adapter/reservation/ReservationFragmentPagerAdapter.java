package com.fargutuvictoria.unibook.ui.home.adapter.reservation;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fargutuvictoria.unibook.R;
import com.fargutuvictoria.unibook.commons.TabConstants;
import com.fargutuvictoria.unibook.ui.reservation.make.MakeReservationFragment;
import com.fargutuvictoria.unibook.ui.reservation.show.MyReservationsFragment;

public class ReservationFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context context;

    public ReservationFragmentPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == TabConstants.MY_RESERVATIONS_TAB_POSITION) {
            return new MyReservationsFragment();
        } else {
            return new MakeReservationFragment();
        }
    }

    @Override
    public int getCount() {
        return TabConstants.RESERVATION_TABS_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case TabConstants.MY_RESERVATIONS_TAB_POSITION:
                return context.getString(R.string.myReservations);
            case TabConstants.MAKE_RESERVATION_TAB_POSITION:
                return context.getString(R.string.makeReservation);
            default:
                return null;
        }
    }
}
