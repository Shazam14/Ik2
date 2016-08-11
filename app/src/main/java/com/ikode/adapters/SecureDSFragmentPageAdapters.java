package com.ikode.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ikode.fragments.ArchiveFragment;
import com.ikode.fragments.InboxFragment;

/**
 * Created by Jem on 12/27/15.
 */
public class SecureDSFragmentPageAdapters extends FragmentStatePagerAdapter{

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created



    public SecureDSFragmentPageAdapters(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }



    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                InboxFragment tab4 = new InboxFragment();
                return tab4;
            case 1:
                ArchiveFragment tab5 = new ArchiveFragment();
                return tab5;
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return NumbOfTabs;

    }
}
