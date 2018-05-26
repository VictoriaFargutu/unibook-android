package com.fargutuvictoria.unibook.ui.reservation.adapter;


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

        if (position == TabConstants.MAKE_RESERVATION_TAB_POSITION) {
            return new MakeReservationFragment();
        } else {
            return new MyReservationsFragment();
        }
    }

    @Override
    public int getCount() {
        return TabConstants.RESERVATION_TABS_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case TabConstants.MAKE_RESERVATION_TAB_POSITION:
                return context.getString(R.string.makeReservation);
            case TabConstants.MY_RESERVATIONS_TAB_POSITION:
                return context.getString(R.string.myReservations);
            default:
                return null;
        }
    }
}
