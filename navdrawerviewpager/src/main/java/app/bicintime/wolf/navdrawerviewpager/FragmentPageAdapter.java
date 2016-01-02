package app.bicintime.wolf.navdrawerviewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wolf on 1/2/2016.
 */
public class FragmentPageAdapter extends FragmentPagerAdapter{
public List<String> fragmentsA;

    Context context;

        public FragmentPageAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;

        }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment =null;
        switch (position) {
            case 0:
                fragment = Fragment.instantiate(context, WhiteFragment.class.getName());
                break;
            case 1:
                fragment = Fragment.instantiate(context, BlackFragment.class.getName());
                break;
                    }
        return fragment;
    }

        @Override
        public CharSequence getPageTitle(int position) {
            //return CONTENT[position % CONTENT.length].toUpperCase();

            switch (position){

                case 0:
                    return "blue";

                case 1:
                    return "black";



            }

            return null;
        }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 2;
    }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
}