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
public class PlanRouteFragment extends Fragment implements RecyclerPlanRouteAdapter.ClickListener, View.OnKeyListener {


    RecyclerPlanRouteAdapter adapter;
    RecyclerView recyclerView;


    //public empty constrctor may solve the problem??
    public PlanRouteFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.plan_route, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_id);
        adapter = new RecyclerPlanRouteAdapter(getActivity(), getData());
        recyclerView.setHasFixedSize(true);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        //remember layout manager...

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        Button b = (Button) rootView.findViewById(R.id.buttonPlanRoute);

        b.setText("Changed hahahaha");

        Log.d("BUTTON", "ENTERING #1");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("BUTTON", "ENTERING #2");

                //SentFragment sentFragment = new SentFragment();
                PlanRouteFragmentStartA planRouteFragmentStartA = new PlanRouteFragmentStartA();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(PlanRouteFragment.class.getName());
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

        if( keyCode == KeyEvent.KEYCODE_BACK ) {

            getActivity().finish(); // to exit app when pressing this fragment, becuase sometimes can travel to the not intended screen when pressing back button
            System.exit(0);


        }

        return false;
    }
}
