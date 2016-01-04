package app.bicintime.wolf.tryapp2;

/**
 * Created by wolf on 1/3/2016.
 */
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

/**
 * Created by shahabuddin on 6/6/14.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final Resources resources;

    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    /**
     * Create pager adapter
     *
     * @param fm
     * @param resources
     */
    public ViewPagerAdapter(FragmentManager fm, Resources resources) {
        super(fm);
        // this.context = context;
        this.resources = resources;
    }

    /**
     * Return fragment with respect to Position .
     */



    @Override
    public Fragment getItem(int position)
    {
        switch (position){
            //case 0 : return MapFragmentUnused = MapFragmentUnused.newInstance();
            case 0 : return new BlackFragment();
            //case 0: return new PrimaryFragment();
            case 1 : return new PlanRouteFragment();

        }
        return null;
    }

    @Override
    public int getCount() {

        return 2;

    }

    /**
     * This method returns the title of the tab according to the position.
     */

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0 :
                return "Primary";
            case 1 :
                return "Social";

        }
        return null;
    }
    /**
     * On each Fragment instantiation we are saving the reference of that Fragment in a Map
     * It will help us to retrieve the Fragment by position
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    /**
     * Remove the saved reference from our Map on the Fragment destroy
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }


    /**
     * Get the Fragment by position
     *
     * @param position tab position of the fragment
     * @return
     */
    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}