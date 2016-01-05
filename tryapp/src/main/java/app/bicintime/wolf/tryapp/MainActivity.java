package app.bicintime.wolf.tryapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Communicator, ExpandableListView.OnChildClickListener {

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 2 ;

    ExpandableListView expandableListView;



    //
    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("BACK", "OnCreate de MainActivity called");


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }




        tabLayout = (TabLayout) findViewById(R.id.id_tabs);
        viewPager = (ViewPager) findViewById(R.id.id_viewpager);
        expandableListView = (ExpandableListView) findViewById(R.id.right_drawer);

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager())); //ESTA es la razón por la cuál deba hacer esto en una activity y no en un fragmento como antes...
                                                                            //este metodo solo se puede llamar desde una clase que hereda de FragmentActivity, en este caso se puede
                                                                            //porque AppCompatActivity reune todas esas clases de activity...

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        //rootFragment = new RootFragment();

       // rootFragment = (RootFragment) getSupportFragmentManager().getFragments().get(0);

        ArrayList<String> groupItem = new ArrayList<String>();  // a partir de aquí empieza lo que he descubierto para el right navigation drawer
        ArrayList<Object> childItem = new ArrayList<Object>();

        groupItem.add("TechNology");                            //hay que darle datos al right nav drawer sino dará errores nullpointer
        groupItem.add("Mobile");

        ArrayList<String> child = new ArrayList<String>();
        child.add("Java");
        child.add("Drupal");
        child.add(".Net Framework");
        child.add("PHP");
        childItem.add(child);

        child = new ArrayList<String>();
        child.add("Android");
        child.add("Window Mobile");
        child.add("iPHone");
        child.add("Blackberry");
        childItem.add(child);

        expandableListView.setAdapter(new NewAdapter(this, groupItem, childItem));  //aquí podré diseñar toda la vista de la lista, it's gonna take some time

        expandableListView.setOnChildClickListener(this);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void respond(String data) { //sobra ??

        FragmentManager fragmentManager = getSupportFragmentManager();

        YellowFragment yellowFragment = new YellowFragment();

        yellowFragment = (YellowFragment) fragmentManager.findFragmentByTag(YellowFragment.class.getName());

        yellowFragment.changeData(data);

        //YellowFragment = fragmentManager.findFragmentById()

    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        return false;
    }

    class MyAdapter extends FragmentPagerAdapter {

        // private Context context;

        public MyAdapter(FragmentManager fm) {
            super(fm);
            // this.context = context;
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

            Log.d("BACK", "instantiateItem #1");
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

    @Override
    public void onBackPressed() {
        Log.d("BACK", "instantiateItem #1");
        // We retrieve the fragment manager of the activity
        FragmentManager frgmtManager = getSupportFragmentManager();

        // We retrieve the fragment container showed right now
        // The viewpager assigns tags to fragment automatically like this
        // mPager is our ViewPager instance
        Fragment fragment = frgmtManager.findFragmentByTag("android:switcher:" + viewPager.getId() + ":" + viewPager.getCurrentItem());

        // And thanks to the fragment container, we retrieve its child fragment manager
        // holding our fragment in the back stack
        FragmentManager childFragmentManager = fragment.getChildFragmentManager();

        // And here we go, if the back stack is empty, we let the back button doing its job
        // Otherwise, we show the last entry in the back stack (our FragmentToShow)
        if(childFragmentManager.getBackStackEntryCount() == 0){
            super.onBackPressed();
        } else {
            childFragmentManager.popBackStack();
        }
    }
}
