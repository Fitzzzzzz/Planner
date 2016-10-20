package tools;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import layout.BlankFragment;
import layout.Fragment_eating;
import layout.Fragment_meeting;
import layout.Fragment_travel;
import layout.Fragment_done;

/**
 * Created by fitzz on 16-8-15.
 */
public class MyAdapter extends FragmentStatePagerAdapter {
    private String[] mTitle = new String[]{"未分类","会议","饭局","行程","已完成"};
    public MyAdapter(FragmentManager fm) {
        super(fm);
    }
    private Fragment fragment;

    @Override
    public Fragment getItem(int position) {
        Log.d("getItem", "!!!");
        switch (position){
            case 1 : fragment = new Fragment_meeting();break;
            case 2 : fragment = new Fragment_eating();break;
            case 3 : fragment = new Fragment_travel();break;
            case 4 : fragment = new Fragment_done();break;
            case 0 : fragment = new BlankFragment();break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
