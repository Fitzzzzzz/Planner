package tools;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import layout.BlankFragment;

/**
 * Created by fitzz on 16-8-15.
 */
public class MyAdapter extends FragmentStatePagerAdapter {
    private String[] mTitle = new String[]{"未分类","会议","饭局","行程","已完成"};
    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("getItem", "!!!");
        return new BlankFragment();
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
