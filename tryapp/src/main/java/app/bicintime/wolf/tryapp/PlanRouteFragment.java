package app.bicintime.wolf.tryapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wolf on 12/26/2015.
 */

//Fragment class that shows the main plan route section of the app, it uses several things as a recycler view and sharedpreferences to read and save things

public class PlanRouteFragment extends Fragment implements RecyclerPlanRouteAdapter.ClickListener, View.OnKeyListener {


    RecyclerPlanRouteAdapter adapter; //this adapter is necessary because I must create a layout for the recycler view with the help of an recyclerview adapter
    RecyclerView recyclerView;


    //public empty constrctor may solve the problem??
    public PlanRouteFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.plan_route, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_id); //getting the recyclerview into java code
        adapter = new RecyclerPlanRouteAdapter(getActivity(), getData()); //passing the context and some resources(or data) to the adapter
        recyclerView.setHasFixedSize(true); //I have to check what exactly does this
        adapter.setClickListener(this);  //means that this activity will handle the clicks on the recycler
        recyclerView.setAdapter(adapter); //This step is mandatory
        //remember layout manager...

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); //this step is also mandatory



        Button b = (Button) rootView.findViewById(R.id.buttonPlanRoute);  //using this button test to navigate to another fragment

        b.setText("Changed hahahaha");

        Log.d("BUTTON", "ENTERING #1");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("BUTTON", "ENTERING #2");

                //SentFragment sentFragment = new SentFragment();
                PlanRouteFragmentStartA planRouteFragmentStartA = new PlanRouteFragmentStartA();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager(); //note the frag manager is coming from the activity, so I am in the world of the same
                //manager of the attached activity

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(PlanRouteFragment.class.getName());//I added it to the backstack with a TAG otherwise will not be saved at the backstack
                Log.d("BACK", "Entrando a PlanRouteFragmentt");

                YellowFragment yellowFragment = new YellowFragment();
                BlackFragment blackFragment = new BlackFragment();


                //fragmentTransaction.replace(R.id.rootB_framelayout, sentFragment).commit();
                fragmentTransaction.replace(R.id.root_framelayout, planRouteFragmentStartA).commit();
                fragmentManager.executePendingTransactions();


            }
        });
            /*
        Button b = (Button) getActivity().findViewById(R.id.buttonPlanRoute);
        //El problema esta en que intento a acceder antes de que se cree, aun cuando la actividad se finaliza, solo finaliza el primer fragmento
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */
        return rootView;
    }

    public static List<Information> getData() {
        //With this method I am giving content to the recyclerview with the help of Information class

        Log.d("RECYCLER", "Entering at getData()");

        List<Information> data = new ArrayList<>();

        int[] icons = {R.drawable.categorias, R.drawable.cuenta, R.drawable.cuenta, R.drawable.configuracion};
        //int[] icons2 = {R.drawable.drawer_toggle, R.drawable.drawer_toggle, R.drawable.drawer_toggle, R.drawable.drawer_toggle};
        int[] icons2 = {R.drawable.categorias, R.drawable.cuenta, R.drawable.cuenta, R.drawable.configuracion};

        String[] titles = {"Current Location", "Choose Destination", "test3", "test4"};
        String[] titles2 = {"Start", "Destination", "test3", "test4"};

        for (int i = 0; i < titles.length && i < icons.length; i++) {

            Information current = new Information();
            current.title = titles[i];
            current.title2 = titles2[i];
            current.iconid1 = icons[i];
            current.iconid2 = icons2[i];

            data.add(current);


        }

        return  data;


    }

    //BACK BUTTON MAKE APP TO DESTROY /CLOSE

    @Override
    public void itemClicked(View view, int position) {

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if( keyCode == KeyEvent.KEYCODE_BACK ) { //In this case I want to avoid returning to a previous fragment, this screen should close the app on tapping back button

            getActivity().finish(); // to exit app when pressing this fragment, becuase sometimes can travel to the not intended screen when pressing back button
            System.exit(0);


        }

        return false;
    }
}
