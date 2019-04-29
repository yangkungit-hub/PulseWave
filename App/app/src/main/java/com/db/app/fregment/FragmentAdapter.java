package com.db.app.fregment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.db.app.fregment.BlueTooth.BLE;

public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new BLE();
        } else if (position == 1) {
            return new Server();
        } else {
            return new Profile();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
