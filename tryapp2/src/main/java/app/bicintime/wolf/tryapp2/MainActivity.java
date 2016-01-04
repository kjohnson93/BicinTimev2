package app.bicintime.wolf.tryapp2;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 2 ;

    //
    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
    private CarouselFragment carouselFragment;

    private ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar); //this has to done from activity
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }




        tabLayout = (TabLayout) findViewById(R.id.id_tabs);

        carouselFragment = new CarouselFragment();
/*Creo que esta solución no es valida, porque al final carouselfragment es un fragmento y lo encuentro un paso inncesario, ya que mi objetivo era cambiar solo el contenido
de viewpager, lo puedo hacer directamente con cualquiera de los fragmentos, si intento hacer esto, desde carouselfragment no le puedo dar la vista que yo quisiera , o lo puedo probar
LA COSA esta en que a el no se le va la barra al hacer el replace, a mi si se me va todo (no lo he probado pero seguramente si) si lo reemplazo.
ADEMAS una vez reemplazado YA debería tener la vista definida, y no reemplazarla
 */



        viewPager = (ViewPager) findViewById(R.id.id_viewpager);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), getResources())); //ESTA es la razón por la cuál deba hacer esto en una activity y no en un fragmento como antes...
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

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.id_viewpager, carouselFragment)
                .commit();

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
    public void onBackPressed() {
/*
        Log.d("BACK", "onBackPressed #1");
        if (getSupportFragmentManager().getBackStackEntryCount() > 0 ){ //este fragmentmanager no tiene nada que ver con el child fragment manager, por eso no sirve esta solución
            Log.d("BACK", "onBackPressed #3: IF");
            getSupportFragmentManager().popBackStack();
        } else {
            Log.d("BACK", "onBackPressed #2: else");
            super.onBackPressed();
        }*/


        // We retrieve the fragment manager of the activity
        FragmentManager frgmtManager = getSupportFragmentManager();

        //OnBackPressListener currentFragment = (OnBackPressListener) adapter.getRegisteredFragment(pager.getCurrentItem());

        //Fragment fragmentadapt = viewPager.getAdapter().getRegisteredFragment(viewPager.getCurrentItem());

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
