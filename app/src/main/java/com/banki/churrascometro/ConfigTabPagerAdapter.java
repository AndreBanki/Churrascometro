package com.banki.churrascometro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;

public class ConfigTabPagerAdapter  extends FragmentPagerAdapter {
    final int PAGE_COUNT = Churrasco.nTiposConvidados;
    private String tabTitles[] = new String[] { "Homens", "Mulheres", "Crianças" };
    private Context context;

    public ConfigTabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return ConfigTabFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
