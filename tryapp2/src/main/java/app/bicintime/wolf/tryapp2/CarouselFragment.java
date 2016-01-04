package app.bicintime.wolf.tryapp2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wolf on 1/3/2016.
 */
public class CarouselFragment extends android.support.v4.app.Fragment {

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 2 ;



    public CarouselFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_carousel, container, false);




        return rootView;


    }
}
