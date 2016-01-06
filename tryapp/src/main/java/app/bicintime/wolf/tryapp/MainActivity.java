package app.bicintime.wolf.tryapp;

import android.content.Intent;
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

    DrawerLayout mDrawerLayout; //my drawerlayout reference
    NavigationView mNavigationView; //my navigationview reference. Not using it for the moment

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 2 ;

    ExpandableListView expandableListView;  //my expandableListView reference, probably deprecated



    //
    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();   //this variable was useful for the adapter for registering fragments
    //but I am not following that approach to navigate through fragments so this variable is probably deprecated.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("BACK", "OnCreate de MainActivity called");


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout); //Getting the drawerlayout to java code
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view); //Getting the navigation view to java code
        Log.d("NAVDRAWER","WTF");

        if(navigationView!=null){ //if the navigationView exists, I am calling the setUpDrawerContent method, which will let me perfom actions on the nav drawer
            setupDrawerContent(navigationView);
            Log.d("NAVDRAWER", "WTF 2");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);  //in this part I am setting my toolbar
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            //ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);//tengo que averiguarlo

        }




        tabLayout = (TabLayout) findViewById(R.id.id_tabs);
        viewPager = (ViewPager) findViewById(R.id.id_viewpager);
        expandableListView = (ExpandableListView) findViewById(R.id.right_drawer);  //the right drawer, probably deprecated

        //NavigationDrawerFragment navigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer); //older approach not using it now
        //navigationDrawerFragment.setUp((DrawerLayout) findViewById(R.id.drawer_layout), toolbar );

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


        //this part was useful for expandablelistview
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

    private void selectItem(String title) { //useless function also PLaceholder useless fragment class
        // Enviar título como arguemento del fragmento
        Bundle args = new Bundle();
        args.putString(PlaceholderFragment.ARG_SECTION_TITLE, title);

        Fragment fragment = PlaceholderFragment.newInstance(title);
        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_content, fragment)
                .commit();

        mDrawerLayout.closeDrawers(); // Cerrar drawer

        setTitle(title); // Setear título actual

    }

    private void setupDrawerContent(NavigationView navigationView) { //As I told before, this method will let me navigate through the nav drawer
        navigationView.setNavigationItemSelectedListener(//item click listener
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        Log.d("NAVDRAWER","Clicando item: " + menuItem.toString());
                        Log.d("NAVDRAWER","Clicando item con ID: " + menuItem.getItemId());

                        int itemDrawer = menuItem.getItemId(); //getting the id of the item clicked

                        if(itemDrawer == R.id.nav_facturas){ //an example of what can I do

                            Log.d("NAVDRAWER","Clicando item con ID dentro del if: " + menuItem.getItemId());

                            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class); //getApplicationContext resolved my problem of passing this not being valid !
                            mDrawerLayout.closeDrawers(); //need to close the drawer in case of turning back to the previous screen

                            startActivity(intent);//start the next activity

                            Log.d("NAVDRAWER", "SE lee algo aquí?");

                        }
                        // Marcar item presionado
                        menuItem.setChecked(true); //After checking it, dunno what exactly does
                        // Crear nuevo fragmento
                        //String title = menuItem.getTitle().toString();
                        // selectItem(title);
                        return true;
                    }
                }
        );
    }


    @Override
    public void respond(String data) { //sobra ?? //useless method

        FragmentManager fragmentManager = getSupportFragmentManager();

        YellowFragment yellowFragment = new YellowFragment();

        yellowFragment = (YellowFragment) fragmentManager.findFragmentByTag(YellowFragment.class.getName());

        yellowFragment.changeData(data);

        //YellowFragment = fragmentManager.findFragmentById()

    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) { //overrrided method but I don't find the need of using it
        return false;
    }

    class MyAdapter extends FragmentPagerAdapter { //the viewpager adapter which gives form to the main content on the screen

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
            switch (position){ //I start with this fragments for tab 0 and 1
                //case 0 : return MapFragmentUnused = MapFragmentUnused.newInstance();
                case 0 : return new BlackFragment();
                //case 0: return new PrimaryFragment();
                case 1 : return new PlanRouteFragment();

            }
            return null;
        }

        @Override
        public int getCount() { //only want two tabs displayed

            return 2;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) { //those are my titles

            switch (position){
                case 0 :
                    return "Map";
                case 1 :
                    return "Plan a Route";

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
        public Object instantiateItem(ViewGroup container, int position) { //not called method
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
        public void destroyItem(ViewGroup container, int position, Object object) { //not called method
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
        }


        /**
         * Get the Fragment by position
         *
         * @param position tab position of the fragment
         * @return
         */
        public Fragment getRegisteredFragment(int position) { //not called method
            return registeredFragments.get(position);
        }

    }

    @Override
    public void onBackPressed() { //with this method I tried to handle the back button but it is unnecessary now because the fragments handle the back button by themselves
        Log.d("BACK", "instantiateItem #1");
            super.onBackPressed();

    }
}
