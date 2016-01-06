package app.bicintime.wolf.tryapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wolf on 1/5/2016.
 */
//I am not using this class for now

public class NavigationDrawerFragment extends Fragment {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setUp(DrawerLayout drawerLayout, Toolbar toolbar){


    mDrawerLayout = drawerLayout;
    mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar,R.string.drawer_open, R.string.drawer_close){

        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
        }


    };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }
}
